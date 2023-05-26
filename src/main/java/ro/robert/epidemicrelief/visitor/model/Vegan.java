package ro.robert.epidemicrelief.visitor.model;

import ro.robert.epidemicrelief.visitor.Visitor;

import java.util.List;

public class Vegan extends HouseholdMember {

    public Vegan(Long numberOfPersons) {
        super(numberOfPersons);
    }
    public Long getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Long numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    @Override
    public List<ProductNecessity> productNecessityList(Visitor visitor) {
        return visitor.visit(this);
    }
}
