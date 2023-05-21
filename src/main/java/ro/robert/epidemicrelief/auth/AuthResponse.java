package ro.robert.epidemicrelief.auth;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private UserDetails user;
    private String error;
}
