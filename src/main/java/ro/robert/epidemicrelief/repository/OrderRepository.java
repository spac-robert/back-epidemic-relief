package ro.robert.epidemicrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.epidemicrelief.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
