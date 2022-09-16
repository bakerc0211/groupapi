package io.catalyte.training.sportsproducts.domains.shipping;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Shipping Rate Repository
 */

@Repository
public interface ShippingRateRepository extends JpaRepository<ShippingRate, Float> {

  @Query(value = "select * from shipping", nativeQuery = true)
  List<String> findShippingRate();

  @Query(value = "SELECT shipping_cost FROM shipping where shipping.us_state = :usState", nativeQuery = true)
  List<String> findShippingRateByState(@Param("usState") String usState);
}