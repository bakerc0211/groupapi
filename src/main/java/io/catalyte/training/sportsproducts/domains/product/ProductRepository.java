package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query("SELECT p FROM Product p WHERE "
      + "p.brand LIKE CONCAT('%',:query, '%')"
      + "Or p.category LIKE CONCAT('%',:query, '%')"
      + "Or p.demographic LIKE CONCAT('%',:query, '%')"
      + "Or p.price LIKE CONCAT('%',:query, '%') "
      + "Or p.primaryColorCode LIKE CONCAT('%',:query, '%')"
      + "Or p.material LIKE CONCAT('%',:query, '%')")
  List<Product> filterProducts(String query);
}
