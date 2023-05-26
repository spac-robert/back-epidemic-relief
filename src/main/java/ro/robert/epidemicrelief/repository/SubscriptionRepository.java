package ro.robert.epidemicrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.epidemicrelief.model.Subscription;

import java.util.Date;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    List<Subscription> findAllByDate(Date date);
}
