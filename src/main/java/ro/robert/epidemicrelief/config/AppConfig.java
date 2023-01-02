package ro.robert.epidemicrelief.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.robert.epidemicrelief.model.Product;
import ro.robert.epidemicrelief.repository.MediaRepository;
import ro.robert.epidemicrelief.repository.ProductRepository;

import java.sql.Date;
import java.util.List;

@Configuration
public class AppConfig {

//    @Bean
//    CommandLineRunner commandLineRunner(ProductRepository repository, MediaRepository mediaRepository) {
//        return args -> {
//            Product product = new Product("cascaval", 25L, Date.valueOf("2021-11-26"), "ceva", "Proxi");
//            Product product1 = new Product("a", 25L, Date.valueOf("2021-11-26"), "ceva", "Proxi");
//            Product product2 = new Product("s", 25L, Date.valueOf("2021-11-26"), "ceva", "Proxi");
//            Product product3 = new Product("d", 25L, Date.valueOf("2021-11-26"), "ceva", "Proxi");
//            Product product4 = new Product("r", 25L, Date.valueOf("2021-11-26"), "ceva", "Proxi");
//            Product product5 = new Product("qwe", 25L, Date.valueOf("2021-11-26"), "ceva", "Proxi");
//            Product product6 = new Product("r123", 25L, Date.valueOf("2021-11-26"), "ceva", "Proxi");
//            Product product7 = new Product("213r", 25L, Date.valueOf("2021-11-26"), "ceva", "Proxi");
//            Product product8 = new Product("1r23", 25L, Date.valueOf("2021-11-26"), "ceva", "Proxi");
//            Product product9 = new Product("4444r3231", 25L, Date.valueOf("2021-11-26"), "ceva", "Proxi");
//
//            repository.saveAll(List.of(product, product1, product2, product3, product4, product5, product6, product7, product8, product9));
//
//        };
//    }
}