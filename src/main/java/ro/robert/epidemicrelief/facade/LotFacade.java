package ro.robert.epidemicrelief.facade;

import ro.robert.epidemicrelief.dto.ProductOrder;

import java.util.List;

/**
 * Facade responsible for operations performed on {@link ro.robert.epidemicrelief.dto.LotDTO}.
 */
public interface LotFacade {

    void updateLot(List<ProductOrder> products);
}
