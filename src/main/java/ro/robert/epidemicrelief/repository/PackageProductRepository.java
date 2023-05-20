package ro.robert.epidemicrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.epidemicrelief.model.PackageProduct;

@Repository
public interface PackageProductRepository extends JpaRepository<PackageProduct, Long> {
}
