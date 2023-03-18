package ro.robert.epidemicrelief.converter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.dto.OrderDTO;
import ro.robert.epidemicrelief.dto.PaymentInfo;
import ro.robert.epidemicrelief.model.Order;

@Component
public class OrderConverter {

    @NonNull
    public OrderDTO from(@NonNull Order source) {

        OrderDTO target = new OrderDTO();
        target.setEmail(source.getEmail());
        target.setPrice(source.getTotalPrice());
        target.setAddress(source.getAddress());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setPaymentInfo(new PaymentInfo(source.getPaymentMethod(), source.getCardNumber(), source.getCardName()));
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
        target.setPaymentMethod(source.getPaymentInfo().getPaymentMethod());
        target.setCardName(source.getPaymentInfo().getCardName());
        target.setCardNumber(source.getPaymentInfo().getCardNumber());

        return target;
    }
}
