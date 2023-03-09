package ro.robert.epidemicrelief.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.robert.epidemicrelief.dto.OrderDTO;
import ro.robert.epidemicrelief.service.EmailService;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private final EmailService emailService;
    //private OrderFacade orderFacade;

    @PostMapping("")
    public void placeOrder(@RequestBody OrderDTO order) {
        //public void placeOrder(){
        //save order and retrieve order id;
        String to = order.getEmail();
        String subject = "Order Confirmation";
        String text = "Order Confirmation number:...\n\n\n Products: " + order.getProducts() + "\n\n\n Payment method: "
                + order.getPaymentInfo().getPaymentMethod() + "\n Card Name: " + order.getPaymentInfo().getCardName()
                + "\n Card number: " + order.getPaymentInfo().getCardNumber() + "\n\n\n Thank you for placing your order. Your order has been confirmed.";
        emailService.sendEmail(to, subject, text);
    }
}
