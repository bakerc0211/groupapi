package io.catalyte.training.sportsproducts.domains.shipping;

import org.springframework.stereotype.Service;

@Service
public interface ShippingRateService {

  Double getShippingRateByState(String usState);
}
