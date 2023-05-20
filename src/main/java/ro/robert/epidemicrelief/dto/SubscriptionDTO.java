package ro.robert.epidemicrelief.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDTO {

    private Integer id;
    private Long userId;
    private String date;
}
