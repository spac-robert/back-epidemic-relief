package ro.robert.epidemicrelief.service.impl;

import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.model.*;
import ro.robert.epidemicrelief.repository.*;
import ro.robert.epidemicrelief.service.PackageService;
import ro.robert.epidemicrelief.visitor.ProductVisitor;
import ro.robert.epidemicrelief.visitor.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultPackageService implements PackageService {

    private final PackageRepository packageRepository;
    private final NecessityRepository necessityRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final LotRepository lotRepository;

    public DefaultPackageService(PackageRepository packageRepository, NecessityRepository necessityRepository, ProductRepository productRepository, AccountRepository accountRepository, LotRepository lotRepository) {
        this.packageRepository = packageRepository;
        this.necessityRepository = necessityRepository;
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
        this.lotRepository = lotRepository;
    }

    @Override
    public void fillPackage(Long userId) {
        PackageEntity packageEntity = createPackage(userId);
        List<ProductNecessity> productNecessityList = createNecessityList(packageEntity);
        for (ProductNecessity product : productNecessityList) {

            Optional<Product> productByUuid = productRepository.findById(product.getUuid());

            if (productByUuid.isPresent()) {
                Product productFromUuid = productByUuid.get();
                Long productStock = product.getStock();
                List<Lot> lots = lotRepository.findByProductIdOrderByExpirationDateAsc(productFromUuid.getId());
                for (Lot lot : lots) {
                    if (lot.getQuantity() >= productStock) {
                        lot.setQuantity((int) (lot.getQuantity() - productStock));
                        break;
                    } else {
                        productStock = productStock - lot.getQuantity();
                        lot.setQuantity(0);
                    }
                }
                lotRepository.saveAll(lots);
                //AICI vreau sa fac pentru orice product sa am catitatea
                packageEntity.getProducts().add(new PackageProducts(productFromUuid, packageEntity, productStock));
            }
        }
        // packageRepository.save(aPackage);
    }

    //TODO Mock deoarece pachetul are un household
    private PackageEntity createPackage(Long userId) {
        Optional<Account> user = accountRepository.findById(userId);
        PackageEntity packageEntity = new PackageEntity();

        user.ifPresent(value -> packageEntity.setHousehold(value.getHousehold()));
        return packageEntity;
    }

    private List<ProductNecessity> createNecessityList(PackageEntity aPackage) {

        ProductVisitor productVisitor = new ProductVisitor(necessityRepository);
        Household household = aPackage.getHousehold();
        List<HouseholdMember> members = new LinkedList<>();
        List<ProductNecessity> productNecessityList = new ArrayList<>();

        members.add(new Family(household.getNumberOfPeople()));
        members.add(new Child(household.getNumberOfChildren()));
        members.add(new Vegan(household.getNumberOfVegans()));
        members.add(new NonVegan(household.getNumberOfNonVegans()));

        for (HouseholdMember householdMembers : members) {
            productNecessityList.addAll(householdMembers.productNecessityList(productVisitor));
        }
        return productNecessityList;
    }
}
