package ro.robert.epidemicrelief.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LotDTO {
    private String id;
    private Integer productId;
    private Integer quantity;
    private String expirationDate;
}
