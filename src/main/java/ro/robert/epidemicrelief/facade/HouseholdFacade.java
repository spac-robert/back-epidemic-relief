package ro.robert.epidemicrelief.facade;

import ro.robert.epidemicrelief.dto.HouseholdDTO;

public interface HouseholdFacade {

    void addHousehold(HouseholdDTO householdDTO);

    void updateHousehold(HouseholdDTO householdDTO);
}
