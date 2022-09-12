package io.catalyte.training.sportsproducts.domains.shipping;

import io.catalyte.training.sportsproducts.domains.shipping.ShippingRateService;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import org.springframework.dao.DataAccessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingRateServiceImpl {

  private final Logger logger = LogManager.getLogger(ShippingRateServiceImpl.class);
  ShippingRateRepository shippingRateRepository;

  @Autowired
  public ShippingRateServiceImpl(ShippingRateRepository shippingRateRepository) {
    this.shippingRateRepository = shippingRateRepository;
}

  public Double getShippingRate(String usState) {
    try {
      return shippingRateRepository.getShippingRateByState(usState);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }
}
