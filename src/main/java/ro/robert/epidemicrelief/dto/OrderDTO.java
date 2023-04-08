package ro.robert.epidemicrelief.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Integer orderId;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Double price;
    private List<ProductOrderDTO> products;
    private String city;
    private String state;
    private String zip;

}
