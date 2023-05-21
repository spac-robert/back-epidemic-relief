package ro.robert.epidemicrelief.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderDTO {
    private Integer quantity;
    private Integer idProduct;
}
