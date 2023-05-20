package ro.robert.epidemicrelief.service;

import lombok.NonNull;
import ro.robert.epidemicrelief.model.Household;

/**
 * Contains business logic related to Jobs
 */
public interface HouseholdService {

    /**
     * Adds the given household
     *
     * @param household the household to be added
     */
    void addHousehold(@NonNull Household household);

    void updateHousehold(@NonNull Household household);

    Household getHousehold(Long id);
}
