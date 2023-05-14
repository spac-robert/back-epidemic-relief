package ro.robert.epidemicrelief.facade;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import ro.robert.epidemicrelief.dto.LotDTO;
import ro.robert.epidemicrelief.dto.ProductDTO;

import java.util.List;

/**
 * Facade responsible for operations performed on {@link ProductDTO}.
 */

public interface ProductFacade {
    /**
     * @return list of all products listed o a page
     */
    @NonNull
    Page<ProductDTO> getProducts(int pageSize, int pageNo, String sortBy, String sortDir);

    /**
     * @return list of all products
     */
    @NonNull
    List<ProductDTO> getProducts();

    /**
     * @return an Optional containing the matching household if it exists; empty optional otherwise
     */
    @NonNull
    ProductDTO getById(Integer id);

    /**
     * Adds the given user.
     *
     * @param product the product to be added
     */
    void addProduct(@NonNull ProductDTO product);

    /**
     * Updates the given user.
     *
     * @param product the product to be updated
     */
    void updateProduct(@NonNull ProductDTO product);

    /**
     * Removes the product with the given id.
     *
     * @param id id of the product to be removed
     */
    void deleteProduct(Integer id);

    /**
     * Adds a lot for a given product
     */
    void addLot(LotDTO lotDTO);
}
