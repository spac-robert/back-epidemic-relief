package ro.robert.epidemicrelief.facade.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.converter.ProductConverter;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.facade.ProductFacade;
import ro.robert.epidemicrelief.model.Product;
import ro.robert.epidemicrelief.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component(value = "productFacade")
@AllArgsConstructor
public class DefaultProductFacade implements ProductFacade {

    private final ProductConverter productConverter;
    private final ProductService productService;

    @Override
    public @NonNull List<ProductDTO> getProducts(int pageSize, int pageNo, String sortBy, String sortDir) {
        return productService.getProducts(pageSize, pageNo, sortBy, sortDir).stream()
                .map(productConverter::from)
                .collect(Collectors.toList());
    }

    @Override
    public @NonNull Optional<ProductDTO> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void addProduct(@NonNull ProductDTO product) {
        productService.addProduct(productConverter.to(product));
    }

    @Override
    public void updateProduct(@NonNull ProductDTO product) {
        Optional<Product> productOptional = productService.getById(product.getId());
        if (productOptional.isPresent()) {
            productService.updateProduct(productConverter.to(product));
        } else {
            throw new EntityNotFoundException("Product with id: " + product.getId() + " does not exist");
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        productService.deleteProduct(id);
    }
}
