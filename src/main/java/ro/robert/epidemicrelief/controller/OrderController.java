package ro.robert.epidemicrelief.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.robert.epidemicrelief.dto.OrderDTO;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.dto.ProductOrderDTO;
import ro.robert.epidemicrelief.facade.LotFacade;
import ro.robert.epidemicrelief.facade.OrderFacade;
import ro.robert.epidemicrelief.facade.ProductFacade;
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
    private ProductFacade productFacade;
    private LotFacade lotFacade;

    @PostMapping("")
    public ResponseEntity<String> placeOrder(@RequestBody OrderDTO order) {
        OrderDTO orderDTO = orderFacade.addOrder(order);
        String to = order.getEmail();
        String subject = "Order Confirmation";
        String text = "Order Confirmation number: " + orderDTO.getOrderId() + "\n\n\n Products: "
                + transformProductForMail(orderDTO.getProducts()) + "\n\n\n Cash"
                + deliveryAddress(orderDTO.getAddress())
                + "\n Total Price: " + orderDTO.getPrice()
                + "\n\n\n Thank you for placing your order";
        emailService.sendEmail(to, subject, text);
        if (orderDTO.getOrderId() != null) {
            lotFacade.updateLot(order.getProducts());
            return new ResponseEntity<>("The confirmation email has been sent", HttpStatus.OK);
        }
        return new ResponseEntity<>("The confirmation email has been sent", HttpStatus.BAD_REQUEST);
    }

    private String deliveryAddress(String address) {
        return "\nDelivery address: " + address + "\n";
    }

    private String transformProductForMail(List<ProductOrderDTO> items) {
        StringBuilder emailProducts = new StringBuilder();
        for (ProductOrderDTO item : items) {

            ProductDTO product = productFacade.getById(item.getIdProduct());
            emailProducts.append("\n").append(product.getName()).append(" ")
                    .append(product.getDescription()).append(" | ")
                    .append("quantity: ").append(item.getQuantity());
        }
        return emailProducts.toString();
    }

}
