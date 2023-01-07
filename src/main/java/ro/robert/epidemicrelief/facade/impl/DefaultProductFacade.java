package ro.robert.epidemicrelief.facade.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.converter.MediaConverter;
import ro.robert.epidemicrelief.converter.ProductConverter;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.facade.ProductFacade;
import ro.robert.epidemicrelief.model.Media;
import ro.robert.epidemicrelief.model.Product;
import ro.robert.epidemicrelief.service.MediaService;
import ro.robert.epidemicrelief.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component(value = "productFacade")
@AllArgsConstructor
public class DefaultProductFacade implements ProductFacade {

    private final ProductConverter productConverter;
    private final ProductService productService;
    private final MediaService mediaService;
    private final MediaConverter mediaConverter;

    @Override
    public @NonNull List<ProductDTO> getProducts(int pageSize, int pageNo, String sortBy, String sortDir) {
        List<Product> products = productService.getProducts(pageSize, pageNo, sortBy, sortDir);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = this.productConverter.from(product);
            //productDTO.setMedia(List.of(mediaConverter.from(product.getMedia().get(0))));
            productDTOS.add(productDTO);
        }
        return productDTOS;
//        return productService.getProducts(pageSize, pageNo, sortBy, sortDir).stream()
//                .map(productConverter::from)
//                .collect(Collectors.toList());
    }

    @Override
    public @NonNull Optional<ProductDTO> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void addProduct(@NonNull ProductDTO productDto) {
        try {
            Media media = mediaConverter.to(productDto.getMedia());
            Product product = productConverter.to(productDto);
            product.setMedia(List.of(media));
            media.setProduct(product);
            productService.addProduct(product);
            mediaService.addMedia(media);
        } catch (IOException e) {

        }
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
