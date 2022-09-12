package io.catalyte.training.sportsproducts.domains.shipping;

import static io.catalyte.training.sportsproducts.constants.Paths.SHIPPING_PATH;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The ProductController exposes endpoints for shipping related actions.
 */
@RestController
@RequestMapping(value = SHIPPING_PATH)
public class ShippingRateController {

  Logger logger = LogManager.getLogger(ShippingRateController.class);

  @Autowired
  private ShippingRateService shippingRateService;

  @GetMapping
//  @ResponseStatus(value = HttpStatus.OK)
//  public ResponseEntity<Double> getShippingRateByState(@PathVariable String usStateString) {
//
//    logger.info("Shipping Rate request received");
//
//    return new ResponseEntity<>(shippingRateService.getShippingRateByState(usStateString), HttpStatus.OK);
//  }
  public ResponseEntity<Double> getShippingRateByState(@PathVariable String usStateString) {
    logger.info("Shipping rate request received");

    return new ResponseEntity<>(shippingRateService.getShippingRateByState(usStateString), HttpStatus.OK);
  }
}
