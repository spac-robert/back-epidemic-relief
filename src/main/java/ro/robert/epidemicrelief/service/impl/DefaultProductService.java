package ro.robert.epidemicrelief.service.impl;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.exception.ProductNotFoundException;
import ro.robert.epidemicrelief.model.Product;
import ro.robert.epidemicrelief.repository.MediaRepository;
import ro.robert.epidemicrelief.repository.ProductRepository;
import ro.robert.epidemicrelief.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultProductService implements ProductService {
    private final ProductRepository repository;
    private final MediaRepository mediaRepository;

    public DefaultProductService(ProductRepository repository, MediaRepository mediaRepository) {
        this.repository = repository;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public void addProduct(@NonNull Product product) {
        repository.save(product);
    }

    @Override
    public void updateProduct(@NonNull Product product) {
        repository.save(product);
    }

    @Override
    public void deleteProduct(@NonNull Integer id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            repository.delete(product.get());
        } else {
            throw new ProductNotFoundException("Product with id: " + id + " doesn't exist");
        }
    }

    @Override
    public Page<Product> getProducts(int pageSize, int pageNo, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy).ascending();
        if (sortDir.equals("desc")) {
            sort = Sort.by(sortBy).descending();
        }
        Pageable page = PageRequest.of(pageNo, pageSize, sort);

        return this.repository.findAll(page);
    }

    @Override
    public List<Product> getProducts() {
        return this.repository.findAll();
    }

    @Override
    public Product getById(@NonNull Integer id) {
        Optional<Product> product = this.repository.findById(id);
        return product.orElseThrow(
                () -> new ProductNotFoundException("Product with id: " + id + " didn't exist")
        );
    }

    @Override
    public List<Product> search(String query) {
        return this.repository.search(query);
    }

    @Override
    public Page<Product> getSearchProducts(int pageSize, int pageNo, String sortBy, String sortDir, String searchQuery) {
        Sort sort = Sort.by(sortBy).ascending();
        if (sortDir.equals("desc")) {
            sort = Sort.by(sortBy).descending();
        }
        Pageable page = PageRequest.of(pageNo, pageSize, sort);

        return this.repository.search(searchQuery, page);
    }
}
