package ro.robert.epidemicrelief.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductPage {
    private List<ProductDTO> products;
    private Integer pageNr;
    private Integer size;
    private Integer totalEl;
}
