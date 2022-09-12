package io.catalyte.training.sportsproducts.domains.purchase;

import static io.catalyte.training.sportsproducts.constants.Paths.PURCHASES_PATH;

import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    Purchase savedPurchase = purchaseService.savePurchase(newPurchase);
    return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Purchase> findAllPurchasesNoEmail() throws ResourceNotFound {
    throw new ResourceNotFound("An email is required");
  }

  @GetMapping(value = "/{email}")
  public ResponseEntity<List<Purchase>> findAllPurchasesByEmail(@PathVariable String email) {
    logger.info("Request received for findAllPurchasesByEmail");
    return new ResponseEntity<>(purchaseService.findAllPurchasesByEmail(email), HttpStatus.OK);
  }
}
