package ro.robert.epidemicrelief.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.model.Account;
import ro.robert.epidemicrelief.model.Household;
import ro.robert.epidemicrelief.repository.HouseholdRepository;
import ro.robert.epidemicrelief.repository.AccountRepository;
import ro.robert.epidemicrelief.service.HouseholdService;

import java.util.Optional;

@Service
public class DefaultHouseholdService implements HouseholdService {

    private final HouseholdRepository householdRepository;
    private final AccountRepository accountRepository;

    public DefaultHouseholdService(HouseholdRepository repository, AccountRepository accountRepository) {
        this.householdRepository = repository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Household addHousehold(@NonNull Household household) {
        return householdRepository.save(household);
    }

    @Override
    public void updateHousehold(@NonNull Household household) {
        householdRepository.save(household);
    }

    @Override
    public Household getHousehold(Long userId) {
        Optional<Account> user = accountRepository.findById(userId);
        return user.map(Account::getHousehold).orElse(null);
    }
}
