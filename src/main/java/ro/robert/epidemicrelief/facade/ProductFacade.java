package ro.robert.epidemicrelief.facade;

import lombok.NonNull;
import ro.robert.epidemicrelief.dto.ProductDTO;

import java.util.List;

/**
 * Facade responsible for operations performed on {@link ProductDTO}.
 */

public interface ProductFacade {
    /**
     * @return list of all products
     */
    @NonNull
    List<ProductDTO> getProducts(int pageSize, int pageNo, String sortBy, String sortDir);

    /**
     * @return an Optional containing the matching household if it exists; empty optional otherwise
     */
    @NonNull
    ProductDTO getById(Integer id);

    /**
     * Adds the given user.
     *
     * @param product the user to be added
     * @param media
     */
    void addProduct(@NonNull ProductDTO product);

    /**
     * Updates the given user.
     *
     * @param product the user to be updated
     */
    void updateProduct(@NonNull ProductDTO product);

    /**
     * Removes the product with the given id.
     *
     * @param id id of the product to be removed
     */
    void deleteProduct(Integer id);

}
