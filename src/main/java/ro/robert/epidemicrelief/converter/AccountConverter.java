package ro.robert.epidemicrelief.converter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.dto.RegisterDTO;
import ro.robert.epidemicrelief.enums.Role;
import ro.robert.epidemicrelief.model.Account;

@Component
public class AccountConverter {

    @NonNull
    public Account to(@NonNull RegisterDTO source) {
        Account target = new Account();
        target.setEmail(source.getEmail());
        target.setRole(Role.CUSTOMER);
        target.setUsername(source.getUsername());
        target.setPassword(source.getPassword());
        return target;
    }
}
