package ro.robert.epidemicrelief.service.impl;

import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.model.Account;
import ro.robert.epidemicrelief.repository.AccountRepository;
import ro.robert.epidemicrelief.service.AccountService;

import java.util.Optional;

@Service
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;

    public DefaultAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findAccountById(Long userId) {
        Optional<Account> account = accountRepository.findById(userId);
        return account.orElse(null);
    }
}
