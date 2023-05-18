package ro.robert.epidemicrelief.visitor;

import ro.robert.epidemicrelief.model.Necessity;
import ro.robert.epidemicrelief.repository.NecessityRepository;
import ro.robert.epidemicrelief.utils.PersonCategory;
import ro.robert.epidemicrelief.visitor.model.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductVisitor implements Visitor {

    private final NecessityRepository necessityRepository;

    public ProductVisitor(NecessityRepository necessityRepository) {
        this.necessityRepository = necessityRepository;
    }

    @NotNull
    private List<ProductNecessity> getProductNecessities(HouseholdMember member, PersonCategory category) {

        List<ProductNecessity> productNecessityList = new ArrayList<>();
        List<Necessity> necessities = necessityRepository.findAllByPersonCategory(category);

        for (Necessity necessity : necessities) {
            if (necessity != null) {
                ProductNecessity productNecessity = new ProductNecessity(necessity.getProduct().getId().toString(), necessity.getQuantity());
                productNecessity.setStock(necessity.getQuantity() * member.getNumberOfPersons());
                productNecessityList.add(productNecessity);
            }
        }
        return productNecessityList;
    }

    @Override
    public List<ProductNecessity> visit(Family family) {
        List<ProductNecessity> productNecessityList = new ArrayList<>();
        List<Necessity> necessities = necessityRepository.findAllByPersonCategory(PersonCategory.FAMILY)
                .stream().filter(Objects::nonNull).collect(Collectors.toList());

        for (Necessity necessity : necessities) {
            if (necessity != null) {
                ProductNecessity productNecessity = new ProductNecessity(necessity.getProduct().getId().toString(), necessity.getQuantity());
                productNecessity.setStock(necessity.getQuantity() * family.getNumberOfPersons() * 60);
                productNecessityList.add(productNecessity);
            }
        }
        return productNecessityList;
    }

    @Override
    public List<ProductNecessity> visit(Vegan vegan) {
        return getProductNecessities(vegan, PersonCategory.VEGAN);
    }

    @Override
    public List<ProductNecessity> visit(NonVegan nonVegan) {
        return getProductNecessities(nonVegan, PersonCategory.NON_VEGAN);
    }

    @Override
    public List<ProductNecessity> visit(Child child) {
        return getProductNecessities(child, PersonCategory.CHILD);
    }
}