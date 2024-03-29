package ro.robert.epidemicrelief.service;

import lombok.NonNull;
import ro.robert.epidemicrelief.dto.ProductOrderDTO;
import ro.robert.epidemicrelief.model.Lot;

import java.util.Date;
import java.util.List;

public interface LotService {

    /**
     * Adds the given lot
     *
     * @param lot the product to be added
     */
    void addLot(@NonNull Lot lot);

    void updateLot(ProductOrderDTO productOrderDTO);

    List<Lot> findLotsByExpirationDateLessThan(Date expirationDate);

    void deleteAll(List<Lot> lots);
}
