package ro.robert.epidemicrelief.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.robert.epidemicrelief.dto.HouseholdDTO;
import ro.robert.epidemicrelief.dto.response.HouseholdResponse;
import ro.robert.epidemicrelief.exception.HouseholdException;
import ro.robert.epidemicrelief.facade.HouseholdFacade;

@RestController
@AllArgsConstructor
@RequestMapping("account/profile/household")
@CrossOrigin(origins = "http://localhost:4200")
public class HouseholdController {

    private final HouseholdFacade householdFacade;

    @PutMapping(value = "/update")
    public ResponseEntity<HouseholdResponse> updateHousehold(@RequestBody HouseholdDTO householdDTO) {
        try {
            HouseholdDTO household = householdFacade.updateHousehold(householdDTO);
            return ResponseEntity.ok().body(new HouseholdResponse(household, null));
        } catch (HouseholdException e) {
            return ResponseEntity.badRequest().body(new HouseholdResponse(null, e.getMessage()));
        }
    }
}
