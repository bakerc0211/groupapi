package io.catalyte.training.sportsproducts.domains.shipping;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ShippingRateService {

  List<String> getShippingRateByState();
}
