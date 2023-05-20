package ro.robert.epidemicrelief.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.robert.epidemicrelief.dto.SubscriptionDTO;
import ro.robert.epidemicrelief.facade.SubscriptionFacade;
import ro.robert.epidemicrelief.model.PackageEntity;
import ro.robert.epidemicrelief.service.PackageService;

@RestController
@AllArgsConstructor
@RequestMapping("/package")
@CrossOrigin(origins = "http://localhost:4200")
public class PackageController {

    private final SubscriptionFacade subscriptionFacade;
    private final PackageService packageService;

    @PostMapping(value = "/subscription")
    public ResponseEntity<String> subscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionFacade.addSubscription(subscriptionDTO);
        //sa iau User user = getUser(JWTData.idAcconunt) ceva de genul trebuie sa fac
        //TODO a service to verify if the user exist and if the id is for the current user logged
        PackageEntity myPackage = packageService.fillPackage(subscriptionDTO.getUserId()); //todo de facut un service in care sa fac convert
        //TODO sa continui flowul si sa pun ResponseEntity la toate endpointurile
        return ResponseEntity.ok("User with id: " + subscriptionDTO.getUserId() + " subscribed on date: " + subscriptionDTO.getDate());
    }
}
