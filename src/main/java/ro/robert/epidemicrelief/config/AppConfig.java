package ro.robert.epidemicrelief.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.robert.epidemicrelief.model.Household;
import ro.robert.epidemicrelief.model.Product;
import ro.robert.epidemicrelief.repository.HouseholdRepository;
import ro.robert.epidemicrelief.repository.MediaRepository;
import ro.robert.epidemicrelief.repository.ProductRepository;

import java.sql.Date;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner commandLineRunner(HouseholdRepository repository) {
        return args -> {
            Household household = new Household("Robert", 4L, "075xxxxxx", 1L, 2L, 2L, "robert_spac@yahoo.com", "aaa");

            repository.save(household);
        };
    }
}