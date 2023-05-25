package ro.robert.epidemicrelief.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.robert.epidemicrelief.enums.Role;
import ro.robert.epidemicrelief.model.*;
import ro.robert.epidemicrelief.repository.*;
import ro.robert.epidemicrelief.utils.PersonCategory;

import java.util.Date;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    CommandLineRunner commandLineRunner(HouseholdRepository repository, AccountRepository accountRepository,
                                        NecessityRepository necessityRepository, ProductRepository productRepository,
                                        LotRepository lotRepository) {
        return args -> {
            Household household = new Household("Robert", 4L, "075xxxxxx", 1L, 2L, 2L, "robert_spac@yahoo.com", "aaa","Suceava","Suceava");
            Account account = new Account("Rob", "robert_spac@yahoo.com", "$2a$10$GdcFqRuINLLykgjdq/n.de7lbk.PfWDTdKOiUKAngK9CmHr3xFDGu", household, Role.ADMIN);
            Account account1 = new Account("customer", "robert1_spac@yahoo.com", "$2a$10$GdcFqRuINLLykgjdq/n.de7lbk.PfWDTdKOiUKAngK9CmHr3xFDGu", household, Role.CUSTOMER);

            Product vitaminJuice = new Product("vitaminJuice", 3F, "asd", "asd");
            Product chocolate = new Product("chocolate", 3F, "asd", "asd");
            Product cannedVegetables = new Product("cannedVegetables", 3F, "asd", "asd");
            Product water = new Product("water", 3F, "asd", "asd");
            Product meat = new Product("meat", 3F, "asd", "asd");

            Necessity child1 = new Necessity(PersonCategory.CHILD, 1L, vitaminJuice);
            Necessity child2 = new Necessity(PersonCategory.CHILD, 1L, chocolate);
            Necessity veg = new Necessity(PersonCategory.VEGAN, 1L, cannedVegetables);
            Necessity family = new Necessity(PersonCategory.FAMILY, 1L, water);
            Necessity nonVeg = new Necessity(PersonCategory.NON_VEGAN, 1L, meat);

            vitaminJuice.setNecessity(child1);
            chocolate.setNecessity(child2);
            cannedVegetables.setNecessity(veg);
            water.setNecessity(family);
            meat.setNecessity(nonVeg);

            necessityRepository.saveAll(List.of(child1, child2, veg, family, nonVeg));

            Lot vitaminJuiceLot = new Lot("1", 300, new Date(), vitaminJuice);
            Lot chocolateLot = new Lot("2", 300, new Date(), chocolate);
            Lot cannedVegetablesLot = new Lot("3", 300, new Date(), cannedVegetables);
            Lot waterLot = new Lot("4", 300, new Date(), water);
            Lot meatLot = new Lot("5", 300, new Date(), meat);

            lotRepository.saveAll(List.of(vitaminJuiceLot, chocolateLot, cannedVegetablesLot, waterLot, meatLot));

            productRepository.saveAll(List.of(water, vitaminJuice, cannedVegetables, chocolate, meat));

            //TODO sa vad daca pot sa imi fac account fara sa imi fac un household
            //Sau cand fac register, primesc un household gol
            repository.save(household);
            accountRepository.save(account);
            accountRepository.save(account1);
        };
    }
}