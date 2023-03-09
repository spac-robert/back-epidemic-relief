package ro.robert.epidemicrelief.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private Double price;
    private List<ProductOrder> products;
    private PaymentInfo paymentInfo;

}
