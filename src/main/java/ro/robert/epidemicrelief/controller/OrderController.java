package ro.robert.epidemicrelief.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.robert.epidemicrelief.dto.OrderDTO;
import ro.robert.epidemicrelief.enums.PaymentMethod;
import ro.robert.epidemicrelief.facade.LotFacade;
import ro.robert.epidemicrelief.facade.OrderFacade;
import ro.robert.epidemicrelief.model.Order;
import ro.robert.epidemicrelief.model.OrderItem;
import ro.robert.epidemicrelief.model.Product;
import ro.robert.epidemicrelief.service.EmailService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private final EmailService emailService;
    private OrderFacade orderFacade;
    private LotFacade lotFacade;

    @PostMapping("")
    public ResponseEntity<String> placeOrder(@RequestBody OrderDTO order) {
        Order orderModel = orderFacade.addOrder(order);
        String to = order.getEmail();
        String subject = "Order Confirmation";
        String text = "Order Confirmation number: " + orderModel.getId() + "\n\n\n Products: "
                + transformProductForMail(orderModel.getItems()) + "\n\n\n" + paymentMethodEmail(orderModel)
                + deliveryAddress(orderModel.getAddress())
                + "\n Total Price: " + orderModel.getTotalPrice()
                + "\n\n\n Thank you for placing your order";
        emailService.sendEmail(to, subject, text);
        if (orderModel.getId() != null) {
            lotFacade.updateLot(order.getProducts());
            return new ResponseEntity<>("The confirmation email has been sent", HttpStatus.OK);
        }
        return new ResponseEntity<>("The confirmation email has been sent", HttpStatus.BAD_REQUEST);
    }

    private String deliveryAddress(String address) {
        return "\nDelivery address: " + address + "\n";
    }

    private String transformProductForMail(List<OrderItem> items) {
        StringBuilder emailProducts = new StringBuilder();
        for (OrderItem item : items) {
            Product product = item.getProduct();
            emailProducts.append("\n").append(product.getName()).append(" ")
                    .append(product.getDescription()).append(" | ")
                    .append("quantity: ").append(item.getQuantity());
        }
        return emailProducts.toString();
    }

    private String paymentMethodEmail(Order orderModel) {
        StringBuilder paymentInfo = new StringBuilder();
        paymentInfo.append("Payment method: ").append(orderModel.getPaymentMethod());
        if (orderModel.getPaymentMethod().equals(PaymentMethod.CARD)) {
            paymentInfo.append("\n Card Name: ").append(orderModel.getCardName())
                    .append("\n Card number: ")
                    .append(orderModel.getCardNumber());
        }
        return paymentInfo.toString();
    }
}
