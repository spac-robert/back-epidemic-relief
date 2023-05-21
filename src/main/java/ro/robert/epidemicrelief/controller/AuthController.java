package ro.robert.epidemicrelief.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.robert.epidemicrelief.auth.AuthDTO;
import ro.robert.epidemicrelief.auth.AuthResponse;
import ro.robert.epidemicrelief.service.AuthService;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public AuthResponse loginFarmer(@RequestBody AuthDTO authDto) {
        return authService.loginUser(authDto);
    }
}