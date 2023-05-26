package ro.robert.epidemicrelief.visitor;

import ro.robert.epidemicrelief.visitor.model.ProductNecessity;

import java.util.List;

/**
 * Contains business logic related to Visitable.
 */
public interface Visitable {
    /**
     * Get a list of ProductNecessity specific for a Visitor
     *
     * @param visitor a {@link Visitor}
     * @return a list of {@link ProductNecessity}
     */
    List<ProductNecessity> productNecessityList(Visitor visitor);
}
