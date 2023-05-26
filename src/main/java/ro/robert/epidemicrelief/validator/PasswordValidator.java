package ro.robert.epidemicrelief.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PasswordValidator {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&+=]?)(?=\\S+$).{6,}$";

    public boolean validate(String password) {
        if (!password.isEmpty()) {
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            return pattern.matcher(password).matches();
        }
        return false;
    }
}