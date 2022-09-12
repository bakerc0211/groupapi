package io.catalyte.training.sportsproducts.domains.shipping;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ShippingRateRepository extends JpaRepository<ShippingRate, Double> {

  @Query(value = "SELECT shipping_cost from shipping_rate.us_state = :usState", nativeQuery = true)
  Double findShippingRateByState(@Param("usState") String usState);
}