package ro.robert.epidemicrelief.service.impl;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.dto.ProductOrderDTO;
import ro.robert.epidemicrelief.model.*;
import ro.robert.epidemicrelief.repository.LotRepository;
import ro.robert.epidemicrelief.repository.SubscriptionRepository;
import ro.robert.epidemicrelief.service.*;
import ro.robert.epidemicrelief.utils.DateFormatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class DefaultJobService implements JobService {
    private final LotService lotService;
    private final SubscriptionRepository subscriptionRepository;
    private final EmailService emailService;
    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(DefaultJobService.class);

    public DefaultJobService(LotService lotService, SubscriptionRepository subscriptionRepository,
                             EmailService emailService, OrderService orderService) {
        this.lotService = lotService;
        this.subscriptionRepository = subscriptionRepository;
        this.emailService = emailService;
        this.orderService = orderService;
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
                Account account = sub.getAccount();

                sub.setIsSubscribed(true);
                Subscription newSubscription = new Subscription(sub);
                newSubscription.setSent(false);
                newSubscription.setDateForNextWeek();
                sub.setSent(true);
                this.subscriptionRepository.save(sub);

                Order order = createOrder(account.getHousehold());
                orderService.addOrder(order);
                try {
                    emailService.sendEmail(order.getEmail(), "Weekly Subscription", convertTo(order.getItems()), order.getAddress());
                } catch (MessagingException e) {
                    logger.warn(e.getMessage());
                }
                //TODO Imi face update, nu-mi adauga altul dc?

                //TODO sa ma ganddesc cum fac in cazul in care nu exista lot pentru acel produs

                // this.subscriptionRepository.save(newSubscription);

                //TODO sa se trimita email persoanei respective
                //din sub, am user id, sa iau emailul din acetsa si sa trimit un emial
                //Pe baza subscriptiei, sa se reactualizeze stock-ul
            }
        }
    }

    private Order createOrder(Household household) {
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0D;
        Order order = new Order();
        if (household.getPackages().size() > 0) {
            PackageEntity lastPackage = household.getPackages().get(household.getPackages().size() - 1);
            for (PackageItem item : lastPackage.getPackageItems()) {
                OrderItem orderItem = new OrderItem(item.getProduct(), item.getQuantity());
                orderItems.add(orderItem);
                order.setAddress(household.getContactAddress());
                order.setEmail(household.getEmail());
                order.setItems(orderItems);
                totalPrice = totalPrice + item.getProduct().getPrice() * item.getQuantity();
                updateLots(item);
            }
            order.setTotalPrice(totalPrice);
        }
        return order;
    }

    private void updateLots(PackageItem item) {
        ProductOrderDTO productOrderDTO = new ProductOrderDTO(item.getQuantity(), item.getProduct().getId());
        this.lotService.updateLot(productOrderDTO);
    }

    private List<ProductOrderDTO> convertTo(List<OrderItem> orderItems) {
        List<ProductOrderDTO> productOrderDTOS = new ArrayList<>();
        for (OrderItem item : orderItems) {
            ProductOrderDTO orderDTO = new ProductOrderDTO(item.getQuantity(), item.getProduct().getId());
            productOrderDTOS.add(orderDTO);
        }
        return productOrderDTOS;
    }

}
