package ro.robert.epidemicrelief.service.impl;

import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.model.Subscription;
import ro.robert.epidemicrelief.repository.SubscriptionRepository;
import ro.robert.epidemicrelief.service.SubscriptionService;

@Service
public class DefaultSubscriptionService implements SubscriptionService {

    private final SubscriptionRepository repository;

    public DefaultSubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addSubscription(Subscription subscription) {
        repository.save(subscription);
    }
}
