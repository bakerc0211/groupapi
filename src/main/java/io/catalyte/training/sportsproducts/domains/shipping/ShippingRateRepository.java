package io.catalyte.training.sportsproducts.domains.shipping;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ShippingRateRepository extends JpaRepository<ShippingRate, Float> {

  @Query(value = "SELECT all from shipping", nativeQuery = true)
  String getShippingRateByState(@Param("usState") String usState);
}