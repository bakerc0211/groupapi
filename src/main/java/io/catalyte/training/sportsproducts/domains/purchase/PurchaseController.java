package io.catalyte.training.sportsproducts.domains.purchase;

import static io.catalyte.training.sportsproducts.constants.Paths.PURCHASES_PATH;

import io.catalyte.training.sportsproducts.domains.purchase.dto.PurchaseDTO;
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

  static Logger logger = LogManager.getLogger(PurchaseController.class);
  CreditCardValidation validator = new CreditCardValidation();

  private static PurchaseService purchaseService;

  @Autowired
  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @PostMapping
  public ResponseEntity<PurchaseDTO> savePurchase(@RequestBody PurchaseDTO newPurchase) {
    PurchaseDTO savedPurchase = purchaseService.savePurchase(newPurchase);
    return new ResponseEntity<PurchaseDTO>(savedPurchase, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Purchase> findAllPurchasesNoEmail() throws ResourceNotFound {
    throw new ResourceNotFound("An email is required");
  }

  @GetMapping(value = "/{email}")
  public ResponseEntity<List<PurchaseDTO>> findAllPurchasesByEmail(@PathVariable String email) {
    logger.info("Request received for findAllPurchasesByEmail");
    return new ResponseEntity<List<PurchaseDTO>>(purchaseService.findAllPurchasesByEmail(email),
        HttpStatus.OK);
  }

  @GetMapping(value = "/products")
  public static ResponseEntity<Object[]> findProductsPurchased( ) {
    logger.info("Request received for findProductsPurchased");
    return new ResponseEntity<>(purchaseService.findProductsPurchased(),
        HttpStatus.OK);
  }

  @GetMapping(value = "/product_id/{product_id}")
  public static ResponseEntity<List<LineItem>> findProductsPurchasedById(
      @PathVariable Long product_id) {
    logger.info("Request received for findProductsPurchasedById: " + product_id);
    return new ResponseEntity<>(purchaseService.findProductsPurchasedById(product_id),
        HttpStatus.OK);
  }
}
