package ro.robert.epidemicrelief.service.impl;

import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.model.Order;
import ro.robert.epidemicrelief.repository.OrderRepository;
import ro.robert.epidemicrelief.service.OrderService;

@Service
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;

    public DefaultOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }
}
