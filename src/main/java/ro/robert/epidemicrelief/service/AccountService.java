package ro.robert.epidemicrelief.service;

import ro.robert.epidemicrelief.model.Account;

public interface AccountService {

    Account findAccountById(Long userId);
}
