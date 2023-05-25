package ro.robert.epidemicrelief.service.impl;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.robert.epidemicrelief.dto.OrderDTO;
import ro.robert.epidemicrelief.model.Account;
import ro.robert.epidemicrelief.model.Lot;
import ro.robert.epidemicrelief.model.Subscription;
import ro.robert.epidemicrelief.repository.SubscriptionRepository;
import ro.robert.epidemicrelief.service.EmailService;
import ro.robert.epidemicrelief.service.JobService;
import ro.robert.epidemicrelief.service.LotService;
import ro.robert.epidemicrelief.service.PackageService;
import ro.robert.epidemicrelief.utils.DateFormatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class DefaultJobService implements JobService {
    private final LotService lotService;
    private final SubscriptionRepository subscriptionRepository;
    private final EmailService emailService;
    private final PackageService packageService;
    private static final Logger logger = LoggerFactory.getLogger(DefaultJobService.class);

    public DefaultJobService(LotService lotService, SubscriptionRepository subscriptionRepository,
                             EmailService emailService, PackageService packageService) {
        this.lotService = lotService;
        this.subscriptionRepository = subscriptionRepository;
        this.emailService = emailService;
        this.packageService = packageService;
    }

    @Override
    @Scheduled(cron = "0 0 * * * *", zone = "GMT+3")
    //For testing @Scheduled(cron = "*/10 * * * * *", zone = "GMT+3")
    public void jobUpdateLots() {
        logger.info("Cronjob started: Updating product lots");
        Date date = new Date();
        List<Lot> lots = lotService.findLotsByExpirationDateLessThan(date);
        lotService.deleteAll(lots);
        logger.info("LOTS DELETED:" + lots);
    }


    @Override
    @Transactional
    //@Scheduled(cron = "0 0 0 * * *", zone = "GMT+3")
    @Scheduled(cron = "*/10 * * * * *", zone = "GMT+3")
    public void jobSubscription() throws ParseException {
        logger.info("Cronjob started: Subscription");
        LocalDate currentDate = LocalDate.now();
        Date date = DateFormatter.dateFormatter(currentDate.toString());
        logger.info("Local date: " + date);
        List<Subscription> subscriptions = this.subscriptionRepository.findAllByDate(date);
        if (!subscriptions.isEmpty()) {
            for (Subscription sub : subscriptions) {
                if (!sub.getSent() && sub.getDate().compareTo(date) == 0) {
                    Account account = sub.getAccount();

                    sub.setIsSubscribed(true);
                    sub.setSent(true);
                    this.subscriptionRepository.save(sub);

                    OrderDTO order = packageService.subscription(account.getId());
                    String subject = "Weekly Subscription";
                    try {
                        emailService.sendEmail(order.getEmail(), subject, order.getProducts(), order.getTotalPrice(), deliveryAddress(order.getAddress()));
                    } catch (MessagingException e) {
                        logger.warn(e.getMessage());
                    }

                    //TODO sa ma gandesc cum fac in cazul in care nu exista lot pentru acel produs
                    Subscription newSubscription = new Subscription(sub);
                    newSubscription.setSent(false);
                    newSubscription.setDateForNextWeek();
                    this.subscriptionRepository.save(newSubscription);
                }
            }
        }
    }

    private String deliveryAddress(String address) {
        return "<p>Delivery address: " + address + "<p><br> Thank you for placing your order!";
    }

}
