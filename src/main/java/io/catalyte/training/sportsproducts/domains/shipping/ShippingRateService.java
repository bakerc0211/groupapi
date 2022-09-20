package io.catalyte.training.sportsproducts.domains.shipping;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 * This interface provides an abstraction layer for the Shipping Rate Service
 */
@Service
public interface ShippingRateService {

  List<String> getShippingRate();

  List<String> getShippingRateByState(String usState);
}
