package io.catalyte.training.sportsproducts.domains.purchase;

import static io.catalyte.training.sportsproducts.constants.Paths.PURCHASES_PATH;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes endpoints for the purchase domain
 */
@RestController
@RequestMapping(value = PURCHASES_PATH)
public class PurchaseController {
  Logger logger = LogManager.getLogger(PurchaseController.class);
  CreditCardValidation validator = new CreditCardValidation();

  private PurchaseService purchaseService;

  @Autowired
  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @PostMapping
  public ResponseEntity savePurchase(@RequestBody Purchase newPurchase) {
    try {
      validator.validCard(newPurchase);
    } catch (Exception e) {
      return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    Purchase savedPurchase = purchaseService.savePurchase(newPurchase);
    return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity findAllPurchases() {
    return new ResponseEntity<>(purchaseService.findAllPurchases(), HttpStatus.OK);
  }
}
