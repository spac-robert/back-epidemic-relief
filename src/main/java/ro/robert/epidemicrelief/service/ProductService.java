package ro.robert.epidemicrelief.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import ro.robert.epidemicrelief.model.Product;

import java.util.List;

public interface ProductService {

    /**
     * Adds the given product
     *
     * @param product the product to be added
     */
    void addProduct(@NonNull Product product);

    /**
     * Updates the given product
     *
     * @param product the product to be updated
     */
    void updateProduct(@NonNull Product product);

    /**
     * Remove the product with the given id
     *
     * @param id the id of the product to be removed
     * @return
     */
    boolean deleteProduct(@NonNull Integer id);

    /**
     * @return a list of products
     */
    Page<Product> getProducts(int pageSize, int pageNo, String sortBy, String sortDir);

    /**
     * @return a list of products
     */
    List<Product> getProducts();

    /**
     * @return an optional containing the matching product if it exists; epty optional otherwise
     */
    Product getById(@NonNull Integer id);

    /**
     * Search in name and description columns from product table
     *
     * @param query
     * @return
     */
    List<Product> search(String query);

    /**
     * Adds the given product
     *
     * @param product the product to be added
     */
    Page<Product> getSearchProducts(int pageSize, int pageNo, String sortBy, String sortDir, String searchQuery);
}
