package ro.robert.epidemicrelief.service;

import lombok.NonNull;
import ro.robert.epidemicrelief.dto.ProductOrder;
import ro.robert.epidemicrelief.model.Lot;

public interface LotService {

    /**
     * Adds the given lot
     *
     * @param lot the product to be added
     */
    void addLot(@NonNull Lot lot);

    void updateLot(ProductOrder productOrder);
}
