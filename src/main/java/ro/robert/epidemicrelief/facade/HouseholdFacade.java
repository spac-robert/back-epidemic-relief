package ro.robert.epidemicrelief.facade;

import lombok.NonNull;
import ro.robert.epidemicrelief.dto.HouseholdDTO;
import ro.robert.epidemicrelief.exception.HouseholdException;

public interface HouseholdFacade {

    void addHousehold(HouseholdDTO householdDTO);

    @NonNull HouseholdDTO updateHousehold(HouseholdDTO householdDTO) throws HouseholdException;
}
