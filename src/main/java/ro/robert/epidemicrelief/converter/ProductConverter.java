package ro.robert.epidemicrelief.converter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.model.Product;

@Component
public class ProductConverter {

    @NonNull
    public ProductDTO from(@NonNull Product source) {

        ProductDTO target = new ProductDTO();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setExpirationDate(source.getExpirationDate());
        target.setDescription(source.getDescription());
        target.setManufacturer(source.getManufacturer());
        return target;
    }

    @NonNull
    public Product to(@NonNull ProductDTO source) {
        Product target = new Product();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setExpirationDate(source.getExpirationDate());
        target.setDescription(source.getDescription());
        target.setManufacturer(source.getManufacturer());

        return target;
    }
}