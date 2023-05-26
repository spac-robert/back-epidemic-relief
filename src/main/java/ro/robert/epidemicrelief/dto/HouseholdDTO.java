package ro.robert.epidemicrelief.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class HouseholdDTO {
    private Long id;
    private String representative;
    private Long numberOfPeople;
    private String phone;
    private Long numberOfChildren;
    private Long numberOfVegans;
    private Long numberOfNonVegans;
    private String email;
    private String contactAddress;
    private String city;
    private String county;
}
