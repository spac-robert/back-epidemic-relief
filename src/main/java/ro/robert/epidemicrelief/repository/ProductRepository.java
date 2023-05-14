package ro.robert.epidemicrelief.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.robert.epidemicrelief.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.media m WHERE p.name LIKE %:query% OR p.description LIKE %:query%")
    List<Product> search(@Param("query") String query);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:searchQuery% OR p.description LIKE %:searchQuery%")
    Page<Product> search(@Param("searchQuery") String searchQuery, Pageable pageable);
}
