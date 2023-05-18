package ro.robert.epidemicrelief.visitor.model;

import ro.robert.epidemicrelief.visitor.Visitable;
import ro.robert.epidemicrelief.visitor.Visitor;

import java.util.List;

/**
 * Contains business logic related to HouseholdMember.
 */
public class HouseholdMember implements Visitable {
    protected Long numberOfPersons;

    public HouseholdMember(Long numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public Long getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Long numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    @Override
    public List<ProductNecessity> productNecessityList(Visitor visitor) {
        return null;
    }

}