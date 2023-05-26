package ro.robert.epidemicrelief.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.robert.epidemicrelief.auth.AuthDTO;
import ro.robert.epidemicrelief.auth.AuthResponse;
import ro.robert.epidemicrelief.dto.RegisterDTO;
import ro.robert.epidemicrelief.dto.response.RegisterResponse;
import ro.robert.epidemicrelief.exception.RegisterException;
import ro.robert.epidemicrelief.service.AuthService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthDTO authDto) {
        AuthResponse response = authService.loginUser(authDto);
        if (response.getError() == null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterDTO registerDTO) {
        try {
            if (authService.register(registerDTO)) {
                RegisterResponse response = new RegisterResponse(true, List.of("A new account was created"));
                return ResponseEntity.ok().body(response);
            }
        } catch (RegisterException e) {
            String[] lines = e.getMessage().split("\\r?\\n");
            List<String> errorMessage = new ArrayList<>(Arrays.asList(lines));
            RegisterResponse response = new RegisterResponse(false, errorMessage);
            return ResponseEntity.badRequest().body(response);
        }
        return null;
    }
}