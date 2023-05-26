package ro.robert.epidemicrelief.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
public class RegisterResponse {
    private boolean success;
    private List<String> message;
}
