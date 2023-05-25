package ro.robert.epidemicrelief.controller;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.robert.epidemicrelief.dto.OrderDTO;
import ro.robert.epidemicrelief.dto.SubscriptionDTO;
import ro.robert.epidemicrelief.facade.SubscriptionFacade;
import ro.robert.epidemicrelief.service.EmailService;
import ro.robert.epidemicrelief.service.PackageService;

@RestController
@AllArgsConstructor
@RequestMapping("/package")
@CrossOrigin(origins = "http://localhost:4200")
public class PackageController {

    private final SubscriptionFacade subscriptionFacade;

    private final PackageService packageService;
    private final EmailService emailService;

    @PostMapping(value = "/subscription")
    public ResponseEntity<String> subscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionFacade.addSubscription(subscriptionDTO);
        OrderDTO order = packageService.subscription(subscriptionDTO.getUserId());
        try {
            emailService.sendEmailSubscription(order.getEmail(), subscriptionDTO.getDate());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("User with id: " + subscriptionDTO.getUserId() + " subscribed on date: " + subscriptionDTO.getDate());
    }

}
