package ro.robert.epidemicrelief.dto.response;

import lombok.*;
import ro.robert.epidemicrelief.dto.HouseholdDTO;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
public class HouseholdResponse {
    private HouseholdDTO household;
    private String error;
}
