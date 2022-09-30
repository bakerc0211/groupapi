package io.catalyte.training.sportsproducts.domains.purchase;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {
  @Query(value = "Select * From line_item Where product_id = :product_id ", nativeQuery = true)
  List<LineItem> findProductsPurchased(@Param("product_id") Long product_id);

  @Query(value = "SELECT product_id FROM line_item", nativeQuery = true)
  Object[] getProductsOnlyInPurchases();
}
