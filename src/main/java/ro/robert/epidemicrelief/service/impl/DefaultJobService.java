package ro.robert.epidemicrelief.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.robert.epidemicrelief.model.Lot;
import ro.robert.epidemicrelief.repository.LotRepository;
import ro.robert.epidemicrelief.service.JobService;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class DefaultJobService implements JobService {
    private final LotRepository lotRepository;
    private static final Logger logger = LoggerFactory.getLogger(DefaultJobService.class);

    public DefaultJobService(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    @Override
    //@Scheduled(cron = "0 0 * * *", zone = "GMT+3")
    @Scheduled(cron = "*/10 * * * * *", zone = "GMT+3")
    public void jobUpdateLots() {
        logger.info("Cronjob started: Updating product lots");
        Date date = new Date();
        List<Lot> lots = lotRepository.findLotsByExpirationDateLessThan(date);
        lotRepository.deleteAll(lots);
        logger.info("LOTS DELETED:" + lots);
    }
}
