package ro.robert.epidemicrelief.facade.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

@Component(value = "productFacade")
@AllArgsConstructor
public class DefaultProductFacade implements ProductFacade {

    private final ProductConverter productConverter;
    private final ProductService productService;
    private final MediaService mediaService;
    private final MediaConverter mediaConverter;

    @Override
    public @NonNull Page<ProductDTO> getProducts(int pageSize, int pageNo, String sortBy, String sortDir) {
        Page<Product> products = productService.getProducts(pageSize, pageNo, sortBy, sortDir);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products.getContent()) {
            ProductDTO productDTO = this.productConverter.from(product);
            productDTO.setMediaUrl(this.mediaConverter.from(product.getMedia().get(0)));
            productDTOS.add(productDTO);
        }
        return new PageImpl<>(productDTOS, products.getPageable(), products.getTotalElements());
    }

    @Override
    public ProductDTO getById(Integer id) {
        Product product = this.productService.getById(id);
        ProductDTO productDTO = this.productConverter.from(this.productService.getById(id));
        productDTO.setMediaUrl(this.mediaConverter.from(product.getMedia().get(0)));
        return productDTO;
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
        Product productOptional = productService.getById(product.getId());
        if (productOptional != null) {
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
