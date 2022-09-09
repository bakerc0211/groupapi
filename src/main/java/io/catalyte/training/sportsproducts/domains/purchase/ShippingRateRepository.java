package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.domains.purchase.ShippingRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRateRepository extends JpaRepository<ShippingRate, Float> {

}