package ro.robert.epidemicrelief.visitor;

import ro.robert.epidemicrelief.visitor.model.*;

import java.util.List;

/**
 * Contains business logic related to Visitor.
 */
public interface Visitor {

    /**
     * Get a list of ProductNecessities for a Vegan
     *
     * @param vegan a {@link Vegan}
     * @return a list of {@link ProductNecessity}
     */
    List<ProductNecessity> visit(Vegan vegan);

    /**
     * Get a list of ProductNecessities for a family
     *
     * @param family a {@link Family}
     * @return a list of {@link ProductNecessity}
     */
    List<ProductNecessity> visit(Family family);

    /**
     * Get a list of ProductNecessities for a nonVegan
     *
     * @param nonVegan a {@link NonVegan}
     * @return * @return a list of {@link ProductNecessity}
     */
    List<ProductNecessity> visit(NonVegan nonVegan);

    /**
     * Get a list of ProductNecessities for a child
     *
     * @param child a {@link Child}
     * @return a list of {@link ProductNecessity}
     */
    List<ProductNecessity> visit(Child child);
}
