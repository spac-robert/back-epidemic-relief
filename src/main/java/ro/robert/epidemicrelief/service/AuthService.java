package ro.robert.epidemicrelief.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.auth.AuthDTO;
import ro.robert.epidemicrelief.auth.AuthResponse;
import ro.robert.epidemicrelief.auth.JwtService;
import ro.robert.epidemicrelief.model.Account;
import ro.robert.epidemicrelief.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    private final JwtService jwtService;

    public AuthResponse loginUser(AuthDTO authDto) {
        if (accountRepository.findUserByEmail(authDto.getEmail()).isPresent()) {
            Account account = accountRepository.findUserByEmail(authDto.getEmail()).get();
            String pass = account.getPassword();
            if (passwordEncoder.matches(authDto.getPassword(), pass))
                return new AuthResponse(jwtService.generateJwtToken(account), account, null);
        }
        AuthResponse authResponse = AuthResponse.builder().build();
        authResponse.setError("Invalid password or mail");
        return authResponse;
    }

//    public AuthResponse signupFarmer(Account account) {
//        account.setPassword(passwordEncoder.encode(account.getPassword()));
//        account.setContracts(new ArrayList<>());
//        account.setFields(new ArrayList<>());
//        validateMail(farmer.getUser().getMail());
//        Farmer savedFarmer = farmerRepository.save(farmer);
//        return new AuthResponse(jwtService.generateJwtToken(savedFarmer), savedFarmer, null);
//    }

}