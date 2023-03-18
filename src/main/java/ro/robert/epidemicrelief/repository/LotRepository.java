package ro.robert.epidemicrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.robert.epidemicrelief.model.Lot;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LotRepository extends JpaRepository<Lot, String> {
    List<Lot> findLotsByExpirationDateLessThan(Date expirationDate);
    List<Lot> findByProductIdOrderByExpirationDateAsc(Integer productId);

}
