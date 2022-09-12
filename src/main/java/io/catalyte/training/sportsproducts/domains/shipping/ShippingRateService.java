package io.catalyte.training.sportsproducts.domains.shipping;


import java.util.List;

public interface ShippingRateService {

  List<Float> getShippingRateByState();
}
