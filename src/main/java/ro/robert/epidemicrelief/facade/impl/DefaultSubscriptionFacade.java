package ro.robert.epidemicrelief.facade.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.converter.SubscriptionConverter;
import ro.robert.epidemicrelief.dto.SubscriptionDTO;
import ro.robert.epidemicrelief.facade.SubscriptionFacade;
import ro.robert.epidemicrelief.model.Subscription;
import ro.robert.epidemicrelief.service.SubscriptionService;

@Component(value = "subscriptionFacade")
@AllArgsConstructor
public class DefaultSubscriptionFacade implements SubscriptionFacade {
    private final SubscriptionService service;
    private final SubscriptionConverter converter;

    @Override
    public void addSubscription(SubscriptionDTO subscriptionDto) {
        Subscription subscription = converter.to(subscriptionDto);
        service.addSubscription(subscription);
    }
}
