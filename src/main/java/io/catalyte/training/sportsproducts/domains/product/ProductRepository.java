package io.catalyte.training.sportsproducts.domains.product;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query( value = "SELECT * FROM Product p WHERE "
      + "p.brand  LIKE CONCAT('%',:query, '%')"
      + "Or p.category  LIKE CONCAT('%',:query, '%')"
      + "Or p.demographic  LIKE CONCAT('%',:query, '%')"
     //  + "Or p.price IN :price LIKE CONCAT('%',:query, '%') "
     // + "Or p.primaryColorCode IN :primary_color_code LIKE CONCAT('%',:query, '%')"
      + "Or p.material  LIKE CONCAT('%',:query, '%')", nativeQuery = true)
  List<Product> filterProducts(@Param("query")Collection<String> query);

  @Query(value = "select distinct category from product", nativeQuery = true)
  List<String> findDistinctCategories();

  @Query(value = "select distinct type from product", nativeQuery = true)
  List<String> findDistinctTypes();
}
