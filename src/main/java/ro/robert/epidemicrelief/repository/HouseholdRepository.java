package ro.robert.epidemicrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.epidemicrelief.model.Household;
import ro.robert.epidemicrelief.model.Media;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {
}
