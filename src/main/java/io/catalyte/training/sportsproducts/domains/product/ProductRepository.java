package io.catalyte.training.sportsproducts.domains.product;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(value = "SELECT p FROM Product p WHERE "
      + "p.brand IN :brand "
      + "OR p.category IN :category "
      + "OR p.demographic IN :demographic "
      + "OR CAST(p.price AS text) IN :price "
      + "OR p.primaryColorCode IN :primaryColorCode "
      + "OR p.material IN :material "
  )

  List<Product> filterProducts( Collection<String> brand,
      Collection<String> demographic, Collection<String> category,
      Collection<String> price, Collection<String> primaryColorCode,
      Collection<String> material);


  @Query(value = "select distinct category from product", nativeQuery = true)
  List<String> findDistinctCategories();

  @Query(value = "select distinct type from product", nativeQuery = true)
  List<String> findDistinctTypes();
}
