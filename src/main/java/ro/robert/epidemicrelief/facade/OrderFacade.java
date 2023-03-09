package ro.robert.epidemicrelief.facade;

import ro.robert.epidemicrelief.dto.OrderDTO;
import ro.robert.epidemicrelief.model.Order;

/**
 * Facade responsible for operations performed on {@link ro.robert.epidemicrelief.dto.OrderDTO}.
 */

public interface OrderFacade {
    /**
     * Adds the given user.
     *
     * @param order the order to be added
     * @return
     */
    Order addOrder(OrderDTO order);
}
