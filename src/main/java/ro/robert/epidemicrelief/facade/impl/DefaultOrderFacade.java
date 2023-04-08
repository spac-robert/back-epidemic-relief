package ro.robert.epidemicrelief.facade.impl;

import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.converter.OrderConverter;
import ro.robert.epidemicrelief.dto.OrderDTO;
import ro.robert.epidemicrelief.dto.ProductOrderDTO;
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
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order orderModel = orderConverter.to(orderDTO);
        Order order = orderService.addOrder(orderModel);
        for (ProductOrderDTO productOrderDTO : orderDTO.getProducts()) {
            Product product = productService.getById(productOrderDTO.getIdProduct());
            if (product != null && productOrderDTO.getQuantity() > 0) {
                OrderItem orderItem = new OrderItem(product, productOrderDTO.getQuantity());
                orderItem.setOrder(order);
                order.getItems().add(orderItem);
            }
        }
        return orderConverter.from(order);
    }

}
