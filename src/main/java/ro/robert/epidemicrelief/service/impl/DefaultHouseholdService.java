package ro.robert.epidemicrelief.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.model.Household;
import ro.robert.epidemicrelief.repository.HouseholdRepository;
import ro.robert.epidemicrelief.service.HouseholdService;

@Service
public class DefaultHouseholdService implements HouseholdService {

    private final HouseholdRepository repository;

    public DefaultHouseholdService(HouseholdRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addHousehold(@NonNull Household household) {
        repository.save(household);
    }
    //TODo am in config deja un household, trebuie ca account sa aiba un household, sa implementez acel visitor din academy
    //de facut debug pe proiectul din academy
    //TODO de facut CRUD pt household
    //TODO de facut CRUT User(trebuie schimbat in Account)
}
