package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

  @Query(value = "select distinct category from product", nativeQuery = true)
  List<String> findDistinctCategories();

  @Query(value = "select distinct type from product", nativeQuery = true)
  List<String> findDistinctTypes();

  @Modifying
  @Transactional
  @Query(value = "Delete from product Where id = :id", nativeQuery = true)
  void deleteProduct(@Param("id") Long id);
}
