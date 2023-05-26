package ro.robert.epidemicrelief.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public boolean validate(String email) {
        if (!email.isEmpty()) {
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            return pattern.matcher(email).matches();
        }
        return false;
    }
}