package ro.robert.epidemicrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.robert.epidemicrelief.model.Lot;

public interface LotRepository extends JpaRepository<Lot, String> {
}
