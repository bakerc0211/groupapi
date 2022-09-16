package io.catalyte.training.sportsproducts.domains.shipping;

import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ShippingRateServiceImpl implements ShippingRateService {

  private final Logger logger = LogManager.getLogger(ShippingRateServiceImpl.class);
  ShippingRateRepository shippingRateRepository;

  @Autowired
  public ShippingRateServiceImpl(ShippingRateRepository shippingRateRepository) {
    this.shippingRateRepository = shippingRateRepository;
  }

  /**
   * Retrieves all shipping rates from the database
   *
   * @return - a list of Shipping Rates
   */
  public List<String> getShippingRate() {
    try {
      return shippingRateRepository.findShippingRate();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  /**
   * Retrieves all products from the database, optionally making use of an example if it is passed.
   *
   * @param usState - an example a US State to use for querying
   * @return - shipping cost for the State
   */
  public List<String> getShippingRateByState(String usState) {
    try {
      return shippingRateRepository.findShippingRateByState(usState);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }
}
