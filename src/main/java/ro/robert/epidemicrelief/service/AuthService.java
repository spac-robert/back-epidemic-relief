package ro.robert.epidemicrelief.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.auth.AuthDTO;
import ro.robert.epidemicrelief.auth.AuthResponse;
import ro.robert.epidemicrelief.auth.JwtService;
import ro.robert.epidemicrelief.converter.AccountConverter;
import ro.robert.epidemicrelief.dto.RegisterDTO;
import ro.robert.epidemicrelief.exception.RegisterException;
import ro.robert.epidemicrelief.exception.WrongFormatEmail;
import ro.robert.epidemicrelief.model.Account;
import ro.robert.epidemicrelief.model.Household;
import ro.robert.epidemicrelief.repository.AccountRepository;
import ro.robert.epidemicrelief.repository.HouseholdRepository;
import ro.robert.epidemicrelief.validator.EmailValidator;
import ro.robert.epidemicrelief.validator.PasswordValidator;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;
    private final HouseholdRepository householdRepository;
    private final PasswordValidator passwordValidator;
    private final EmailValidator emailValidator;

    private final JwtService jwtService;

    public AuthResponse loginUser(AuthDTO authDto) {
        if (emailValidator.validate(authDto.getEmail())) {
            if (accountRepository.findUserByEmail(authDto.getEmail()).isPresent()) {
                Account account = accountRepository.findUserByEmail(authDto.getEmail()).get();
                String pass = account.getPassword();
                if (passwordEncoder.matches(authDto.getPassword(), pass))
                    return new AuthResponse(jwtService.generateJwtToken(account), account, null);
            }
            AuthResponse authResponse = AuthResponse.builder().build();
            authResponse.setError("Invalid password or mail");
            return authResponse;
        } else {
            AuthResponse authResponse = AuthResponse.builder().build();
            authResponse.setError("Wrong format. Email must be (ex: example@domainName.domain)");
            return authResponse;
        }
    }

    public boolean register(RegisterDTO register) {
        String message = "";
        if (accountRepository.findUserByEmail(register.getEmail()).isEmpty()) {
            if (passwordValidator.validate(register.getPassword())) {
                Household household = new Household();
                register.setPassword(passwordEncoder.encode(register.getPassword()));
                Account newAccount = accountConverter.to(register);
                householdRepository.save(household);
                newAccount.setHousehold(household);
                accountRepository.save(newAccount);
                return true;
            } else {
                message = """
                        Minimum length of 6 characters
                        Password must have at least one digit
                        At least one uppercase letter
                        At least one symbol from the set !@#$%^&+=
                        Does not contain whitespace
                        """;
            }
        } else {
            message = message + "Email is already used";
        }
        throw new RegisterException(message);
    }

}