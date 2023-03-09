package ro.robert.epidemicrelief.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.robert.epidemicrelief.enums.PaymentMethod;

@Getter
@Setter
@AllArgsConstructor
public class PaymentInfo {
    private PaymentMethod paymentMethod;
    private String cardNumber;
    private String cardName;
}
