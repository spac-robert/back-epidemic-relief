package ro.robert.epidemicrelief.facade.impl;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.converter.HouseholdConverter;
import ro.robert.epidemicrelief.dto.HouseholdDTO;
import ro.robert.epidemicrelief.exception.HouseholdException;
import ro.robert.epidemicrelief.facade.HouseholdFacade;
import ro.robert.epidemicrelief.model.Household;
import ro.robert.epidemicrelief.service.HouseholdService;

@Component(value = "householdFacade")
public class DefaultHouseholdFacade implements HouseholdFacade {

    private final HouseholdService householdService;
    private final HouseholdConverter householdConverter;

    public DefaultHouseholdFacade(HouseholdService householdService, HouseholdConverter householdConverter) {
        this.householdService = householdService;
        this.householdConverter = householdConverter;
    }

    @Override
    public void addHousehold(HouseholdDTO householdDTO) {
        Household household = householdConverter.to(householdDTO);
        householdService.addHousehold(household);
    }

    @Override
    public @NonNull HouseholdDTO updateHousehold(HouseholdDTO householdDTO) throws HouseholdException {
        Household household = householdService.addHousehold(householdConverter.to(householdDTO));
        if (household != null) {
            return householdConverter.from(household);
        } else {
            throw new HouseholdException("Couldn't update the household");
        }
    }
}
