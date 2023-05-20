package ro.robert.epidemicrelief.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.robert.epidemicrelief.model.Lot;
import ro.robert.epidemicrelief.model.Subscription;
import ro.robert.epidemicrelief.repository.LotRepository;
import ro.robert.epidemicrelief.repository.SubscriptionRepository;
import ro.robert.epidemicrelief.service.JobService;
import ro.robert.epidemicrelief.service.PackageService;
import ro.robert.epidemicrelief.utils.DateFormatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class DefaultJobService implements JobService {
    private final LotRepository lotRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PackageService packageService;
    //TODO
    //private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(DefaultJobService.class);

    public DefaultJobService(LotRepository lotRepository, SubscriptionRepository subscriptionRepository, PackageService packageService) {
        this.lotRepository = lotRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.packageService = packageService;
    }

    @Override
    @Scheduled(cron = "0 0 * * * *", zone = "GMT+3")
    //For testing @Scheduled(cron = "*/10 * * * * *", zone = "GMT+3")
    public void jobUpdateLots() {
        logger.info("Cronjob started: Updating product lots");
        Date date = new Date();
        List<Lot> lots = lotRepository.findLotsByExpirationDateLessThan(date);
        lotRepository.deleteAll(lots);
        logger.info("LOTS DELETED:" + lots);
    }

    @Override
    //@Scheduled(cron = "0 0 0 * * *", zone = "GMT+3")
    @Scheduled(cron = "*/10 * * * * *", zone = "GMT+3")
    public void jobSubscription() throws ParseException {
        logger.info("Cronjob started: Subscription");
        LocalDate currentDate = LocalDate.now();
        Date date = DateFormatter.dateFormatter(currentDate.toString());
        logger.info("Local date: " + date);
        List<Subscription> subscriptions = this.subscriptionRepository.findAllByDate(date);

        if (!subscriptions.isEmpty()) {
            for(Subscription sub: subscriptions){
                sub.setIsSubscribed(true);
                sub.setSent(true);
                //TODO sa se trimita email persoanei respective
                //din sub, am user id, sa iau emailul din acetsa si sa trimit un emial
                //Pe baza subscriptiei, sa se reactualizeze stock-ul
            }
        }
    }

}
