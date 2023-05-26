package ro.robert.epidemicrelief.facade.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.converter.LotConverter;
import ro.robert.epidemicrelief.converter.MediaConverter;
import ro.robert.epidemicrelief.converter.ProductConverter;
import ro.robert.epidemicrelief.dto.LotDTO;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.facade.ProductFacade;
import ro.robert.epidemicrelief.model.Lot;
import ro.robert.epidemicrelief.model.Media;
import ro.robert.epidemicrelief.model.Product;
import ro.robert.epidemicrelief.service.LotService;
import ro.robert.epidemicrelief.service.MediaService;
import ro.robert.epidemicrelief.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(value = "productFacade")
@AllArgsConstructor
public class DefaultProductFacade implements ProductFacade {

    private final ProductConverter productConverter;
    private final LotConverter lotConverter;
    private final LotService lotService;
    private final ProductService productService;
    private final MediaService mediaService;
    private final MediaConverter mediaConverter;

    @Override
    public @NonNull Page<ProductDTO> getProducts(int pageSize, int pageNo, String sortBy, String sortDir) {
        Page<Product> products = productService.getProducts(pageSize, pageNo, sortBy, sortDir);

        return new PageImpl<>(convertProducts(products), products.getPageable(), products.getTotalElements());
    }

    @Override
    public @NonNull List<ProductDTO> getProducts() {
        List<Product> products = this.productService.getProducts();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products) {
            int quantity = 0;
            ProductDTO productDTO = this.productConverter.from(product);
            for (Lot lot : product.getLots()) {
                quantity = quantity + lot.getQuantity();
            }

            productDTO.setStock(quantity);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public @NonNull ProductDTO getById(Integer id) {
        int quantity = 0;
        Product product = this.productService.getById(id);
        ProductDTO productDTO = this.productConverter.from(product);
        for (Lot lot : product.getLots()) {
            quantity = quantity + lot.getQuantity();
        }
        productDTO.setStock(quantity);
        if (product.getMedia() != null) {
            productDTO.setMediaUrl(this.mediaConverter.from(product.getMedia()));
        }
        return productDTO;

    }

    @Override
    public void addProduct(@NonNull ProductDTO productDto) {
        try {
            Media media = mediaConverter.to(productDto.getMedia());
            Product product = productConverter.to(productDto);
            product.setMedia(media);
            media.setProduct(product);
            productService.addProduct(product);
            mediaService.addMedia(media);
        } catch (IOException ignored) {
            System.out.println("Problem");
        }
    }

    @Override
    public void updateProduct(@NonNull ProductDTO product) {
        Media media = new Media();
        Product productOptional = productService.getById(product.getId());

        if (productOptional != null) {
            try {
                if (product.getMedia().isEmpty()) {
                    media = productOptional.getMedia();
                } else {
                    media = mediaConverter.to(product.getMedia());
                    deleteMedia(productOptional);
                }
            } catch (Exception ignored) {

            }
            convert(product, productOptional);
            productOptional.setMedia(media);
            media.setProduct(productOptional);
            mediaService.addMedia(media);

        } else {
            throw new EntityNotFoundException("Product with id: " + product.getId() + " does not exist");
        }
    }

    private void convert(ProductDTO product, Product productOptional) {
        productOptional.setDescription(product.getDescription());
        productOptional.setManufacturer(product.getManufacturer());
        productOptional.setName(product.getName());
        productOptional.setPrice(product.getPrice());
    }

    private void deleteMedia(Product productOptional) {
        if (productOptional.getMedia() != null) {
            mediaService.deleteAllByProduct(productOptional);
        }
        productOptional.setMedia(null);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        return productService.deleteProduct(id);
    }

    @Override
    public void addLot(LotDTO lotDTO) {
        Lot lot = this.lotConverter.to(lotDTO);
        Integer productId = lotDTO.getProductId();
        Product product = this.productService.getById(productId);

        if (product != null) {
            lot.setProduct(product);
            this.lotService.addLot(lot);
        }
    }

    @Override
    public List<ProductDTO> search(String query) {
        List<Product> products = this.productService.search(query);
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = this.productConverter.from(product);
            productDTO.setMediaUrl(this.mediaConverter.from(product.getMedia()));
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public Page<ProductDTO> searchProducts(String searchQuery, String sortBy, String sortDir, int pageSize, int pageNo) {
        Page<Product> products = productService.getSearchProducts(pageSize, pageNo, sortBy, sortDir, searchQuery);

        return new PageImpl<>(convertProducts(products), products.getPageable(), products.getTotalElements());
    }

    private List<ProductDTO> convertProducts(Page<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products.getContent()) {
            ProductDTO productDTO = this.productConverter.from(product);
            if (product.getMedia() != null) {
                productDTO.setMediaUrl(this.mediaConverter.from(product.getMedia()));
            }
            int quantity = 0;
            for (Lot lot : product.getLots()) {
                quantity = quantity + lot.getQuantity();
            }
            productDTO.setStock(quantity);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}
