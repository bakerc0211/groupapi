package io.catalyte.training.sportsproducts.domains.promo;

import static io.catalyte.training.sportsproducts.constants.Paths.PROMOS_PATH;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes endpoints for the purchase domain
 */
@RestController
@RequestMapping(value = PROMOS_PATH)
public class PromoController {

  Logger logger = LogManager.getLogger(PromoController.class);
  private PromoService promoService;

  @Autowired
  public PromoController(PromoService promoService) {
    this.promoService = promoService;
  }

  /**
   * Creates a promo code in the database
   * @param promo promo to create
   * @return created promo
   */
  @PostMapping
  public ResponseEntity savePromo(@RequestBody Promo promo) {
    Promo newPromo = promoService.savePromo(promo);

    return new ResponseEntity<>(newPromo, HttpStatus.CREATED);

  }
}
