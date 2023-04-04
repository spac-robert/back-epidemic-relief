package ro.robert.epidemicrelief.converter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.dto.OrderDTO;
import ro.robert.epidemicrelief.dto.PaymentInfo;
import ro.robert.epidemicrelief.dto.ProductOrderDTO;
import ro.robert.epidemicrelief.model.Order;
import ro.robert.epidemicrelief.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {

    @NonNull
    public OrderDTO from(@NonNull Order source) {
        OrderDTO target = new OrderDTO();
        target.setOrderId(source.getId());
        target.setEmail(source.getEmail());
        target.setPrice(source.getTotalPrice());
        target.setAddress(source.getAddress());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setProducts(convertToAListOfProductOrderDTO(source));

        return target;
    }

    @NonNull
    public Order to(@NonNull OrderDTO source) {
        Order target = new Order();
        target.setEmail(source.getEmail());
        target.setTotalPrice(source.getPrice());
        target.setAddress(source.getAddress());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());

        return target;
    }

    private List<ProductOrderDTO> convertToAListOfProductOrderDTO(Order source) {
        List<ProductOrderDTO> orderDTOList = new ArrayList<>();
        for (OrderItem item : source.getItems()) {
            ProductOrderDTO productOrderDTO = new ProductOrderDTO();
            productOrderDTO.setIdProduct(item.getProduct().getId());
            productOrderDTO.setQuantity(item.getQuantity());
            orderDTOList.add(productOrderDTO);
        }
        return orderDTOList;
    }
}
