package ro.robert.epidemicrelief.converter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.model.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ProductConverter {
    //TODO de adaugat logs

    @NonNull
    public ProductDTO from(@NonNull Product source) {

        ProductDTO target = new ProductDTO();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPrice(source.getPrice());
        target.setExpirationDate(String.valueOf(source.getExpirationDate()));
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
        try {
            target.setExpirationDate(dateFormatter(source.getExpirationDate()));
        } catch (ParseException e) {
            System.out.println("Invalid date format!");
        }
        target.setDescription(source.getDescription());
        target.setManufacturer(source.getManufacturer());

        return target;
    }

    private Date dateFormatter(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(date);
    }
}