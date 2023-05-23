package ro.robert.epidemicrelief.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.robert.epidemicrelief.dto.HouseholdDTO;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.facade.HouseholdFacade;

@RestController
@AllArgsConstructor
@RequestMapping("profile/household")
@CrossOrigin(origins = "http://localhost:4200")
public class HouseholdController {

    private final HouseholdFacade householdFacade;

    @PostMapping(value = "/update")
    public void updateHousehold(@ModelAttribute HouseholdDTO householdDTO) {
        householdFacade.updateHousehold(householdDTO);
    }
}
