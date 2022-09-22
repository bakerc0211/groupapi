package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.product.ProductService;
import io.catalyte.training.sportsproducts.exceptions.BadRequest;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import io.catalyte.training.sportsproducts.exceptions.UnprocessableEntity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  private final Logger logger = LogManager.getLogger(PurchaseServiceImpl.class);

  PurchaseRepository purchaseRepository;
  ProductService productService;
  LineItemRepository lineItemRepository;

  CreditCardValidation creditcardValidator = new CreditCardValidation();
  @Autowired
  public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ProductService productService,
      LineItemRepository lineItemRepository) {
    this.purchaseRepository = purchaseRepository;
    this.productService = productService;
    this.lineItemRepository = lineItemRepository;
  }

  /**
   * Retrieves all purchases from the database
   *
   * @return
   */
  public List<PurchaseDTO> findAllPurchasesByEmail(String email) {
    try {
      List<Purchase> results = purchaseRepository.findByBillingAddressEmail(email);
      List<PurchaseDTO> resultsDTO = new ArrayList<PurchaseDTO>();
      results.forEach((p) -> resultsDTO.add(p.GeneratePurchaseDTO()));
      return resultsDTO;
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  /**
   * Persists a purchase to the database
   *
   * @param newPurchaseDTO - the purchase to persist
   * @return the persisted purchase with ids
   */
  public PurchaseDTO savePurchase(PurchaseDTO newPurchaseDTO) {
    Purchase newPurchase = newPurchaseDTO.GeneratePurchase();

    try {
      creditcardValidator.validCard(newPurchase);
    } catch (Exception e) {
      throw new BadRequest(e.getMessage());
    }

    List<LineItem> lineItems = newPurchase.getProducts();
    List<String> inactiveProducts = new ArrayList<>();

    lineItems.forEach(lineItem -> {
      Product lineProduct = productService.getProductById(lineItem.getProduct().getId());
      if (!lineProduct.getActive()) {
        inactiveProducts.add(lineProduct.getName());
      }
    });

    if (inactiveProducts.size() > 0) {
      throw new UnprocessableEntity(inactiveProducts + " inactive product");
    }

    try {
      purchaseRepository.save(newPurchase);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    // after the purchase is persisted and has an id, we need to handle its line items and persist them as well
    //handleLineItems(newPurchase);

    return newPurchase.GeneratePurchaseDTO();
  }

  /**
   * This helper method retrieves product information for each line item and persists it
   *
   * @param purchase - the purchase object to handle line items for
   */
  private void handleLineItems(Purchase purchase) {
    List<LineItem> itemsList = purchase.getProducts();

    if (itemsList != null) {
      itemsList.forEach(lineItem -> {

        // retrieve full product information from the database
        Product product = productService.getProductById(lineItem.getProduct().getId());

        // set the product info into the line item
        if (product != null) {
          lineItem.setProduct(product);
        }

        // set the purchase on the line item
        lineItem.setPurchase(purchase);

        // persist the populated line item
        try {
          lineItemRepository.save(lineItem);
        } catch (DataAccessException e) {
          logger.error(e.getMessage());
          throw new ServerError(e.getMessage());
        }
      });
    }
  }
}

