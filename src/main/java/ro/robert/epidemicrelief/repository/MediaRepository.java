package ro.robert.epidemicrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.robert.epidemicrelief.model.Media;
import ro.robert.epidemicrelief.model.Product;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Optional<Media> findByName(String name);

    Optional<Media> findByProduct(Product product);

    void deleteByProduct(Product product);
}