package ro.robert.epidemicrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.epidemicrelief.model.PackageEntity;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Long> {
}
