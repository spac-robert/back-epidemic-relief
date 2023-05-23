package ro.robert.epidemicrelief.converter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.dto.HouseholdDTO;
import ro.robert.epidemicrelief.model.Household;

@Component
public class HouseholdConverter {

    @NonNull
    public HouseholdDTO from(@NonNull Household source) {
        HouseholdDTO target = new HouseholdDTO();
        target.setId(source.getId());
        target.setCity(source.getCity());
        target.setEmail(target.getEmail());
        target.setCounty(source.getCounty());
        target.setPhone(source.getPhone());
        target.setNumberOfChildren(source.getNumberOfChildren());
        target.setNumberOfNonVegans(source.getNumberOfNonVegans());
        target.setNumberOfPeople(source.getNumberOfPeople());
        target.setNumberOfVegans(source.getNumberOfVegans());
        target.setContactAddress(source.getContactAddress());

        return target;
    }

    @NonNull
    public Household to(@NonNull HouseholdDTO source) {
        Household target = new Household();
        target.setCity(source.getCity());
        target.setEmail(target.getEmail());
        target.setCounty(source.getCounty());
        target.setPhone(source.getPhone());
        target.setNumberOfChildren(source.getNumberOfChildren());
        target.setNumberOfNonVegans(source.getNumberOfNonVegans());
        target.setNumberOfPeople(source.getNumberOfPeople());
        target.setNumberOfVegans(source.getNumberOfVegans());
        target.setContactAddress(source.getContactAddress());

        return target;
    }
}
