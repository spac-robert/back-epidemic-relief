package ro.robert.epidemicrelief.service.impl;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.model.Product;
import ro.robert.epidemicrelief.repository.ProductRepository;
import ro.robert.epidemicrelief.service.ProductService;

import java.util.Optional;

@Service
public class DefaultProductService implements ProductService {
    private final ProductRepository repository;

    public DefaultProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addProduct(@NonNull Product product) {
        repository.save(product);
    }

    @Override
    public void updateProduct(@NonNull Product product) {

    }

    @Override
    public void deleteProduct(@NonNull Integer id) {

    }

    @Override
    public Page<Product> getProducts(int pageSize, int pageNo, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy).ascending();
        if (sortDir.equals("desc")) {
            sort = Sort.by(sortBy).descending();
        }
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> productPage = this.repository.findAll(page);

        return productPage;
    }

    @Override
    public Product getById(@NonNull Integer id) {
        Optional<Product> product = this.repository.findById(id);
        return product.orElse(null);
    }
}
