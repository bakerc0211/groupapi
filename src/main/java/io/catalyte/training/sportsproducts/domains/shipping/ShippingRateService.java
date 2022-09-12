package io.catalyte.training.sportsproducts.domains.shipping;


import java.util.List;

public interface ShippingRateService {

  Double getShippingRateByState(String usState);
}
