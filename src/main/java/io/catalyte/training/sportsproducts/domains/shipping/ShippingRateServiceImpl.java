package io.catalyte.training.sportsproducts.domains.shipping;

import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ShippingRateServiceImpl implements ShippingRateService {

  private final Logger logger = LogManager.getLogger(ShippingRateServiceImpl.class);
  ShippingRateRepository shippingRateRepository;

  @Autowired
  public ShippingRateServiceImpl(ShippingRateRepository shippingRateRepository) {
    this.shippingRateRepository = shippingRateRepository;
  }

  public List<String> getShippingRateByState() {
    try {
      return shippingRateRepository.findShippingRateByState();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }
}
