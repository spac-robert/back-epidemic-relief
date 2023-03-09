package ro.robert.epidemicrelief.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInfo {
    private String paymentMethod;
    private String cardNumber;
    private String cardName;
}
