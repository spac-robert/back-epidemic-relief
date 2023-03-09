package ro.robert.epidemicrelief.facade.impl;

import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.converter.OrderConverter;
import ro.robert.epidemicrelief.dto.OrderDTO;
import ro.robert.epidemicrelief.dto.ProductOrder;
import ro.robert.epidemicrelief.facade.OrderFacade;
import ro.robert.epidemicrelief.model.Order;
import ro.robert.epidemicrelief.model.OrderItem;
import ro.robert.epidemicrelief.model.Product;
import ro.robert.epidemicrelief.service.OrderService;
import ro.robert.epidemicrelief.service.ProductService;

@Component(value = "orderFacade")
public class DefaultOrderFacade implements OrderFacade {
    private final OrderConverter orderConverter;
    private final OrderService orderService;
    private final ProductService productService;

    public DefaultOrderFacade(OrderConverter orderConverter, OrderService orderService, ProductService productService) {
        this.orderConverter = orderConverter;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public Order addOrder(OrderDTO orderDTO) {
        Order orderModel = orderConverter.to(orderDTO);
        Order order = orderService.addOrder(orderModel);
        for (ProductOrder productOrder : orderDTO.getProducts()) {
            Product product = productService.getById(productOrder.getIdProduct());
            if (product != null && productOrder.getQuantity() > 0) {
                order.getItems().add(new OrderItem(product, productOrder.getQuantity(), order));
            }
        }
        return orderService.addOrder(orderModel);
    }
}
