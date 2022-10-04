package io.catalyte.training.sportsproducts.domains.purchase;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {
  @Query(value = "SELECT product_id FROM line_item", nativeQuery = true)
  Object[] getProductsOnlyInPurchases();

  @Query(value = "SELECT product_id FROM line_item WHERE line_item.id IN (SELECT review_products.products_id FROM review_products)", nativeQuery = true)
  Object[] getProductsOnlyWithReviews();
}
