package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.product.ProductService;
import io.catalyte.training.sportsproducts.domains.purchase.dto.PurchaseDTO;
import io.catalyte.training.sportsproducts.domains.purchase.dto.ReviewDTO;
import io.catalyte.training.sportsproducts.exceptions.BadRequest;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import io.catalyte.training.sportsproducts.exceptions.UnprocessableEntity;

import java.util.ArrayList;
import java.util.List;

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

  ReviewRepository reviewRepository;
  CreditCardValidation creditcardValidator = new CreditCardValidation();

  @Autowired
  public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ProductService productService,
      LineItemRepository lineItemRepository, ReviewRepository reviewRepository) {
    this.purchaseRepository = purchaseRepository;
    this.productService = productService;
    this.lineItemRepository = lineItemRepository;
    this.reviewRepository = reviewRepository;
  }

  /**
   * Retrieves all purchases from the database
   *
   * @return
   */
  public List<PurchaseDTO> findAllPurchasesByEmail(String email) {
    List<Purchase> results = null;

    try {
      results = purchaseRepository.findByBillingAddressEmail(email);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    List<PurchaseDTO> resultsDTO = new ArrayList<PurchaseDTO>();
    results.forEach((p) -> resultsDTO.add(p.GeneratePurchaseDTO()));
    return resultsDTO;
  }


  public Object[] findProductsPurchased() {
    try {
      return lineItemRepository.getProductsOnlyInPurchases();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  @Override
  public Object[] findProductsWithReviews() {
    try {
      return lineItemRepository.getProductsOnlyWithReviews();
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
      newPurchase = purchaseRepository.save(newPurchase);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    return newPurchase.GeneratePurchaseDTO();
  }

  @Override
  public ReviewDTO saveReview(ReviewDTO newReviewDTO) {
    Review newReview = newReviewDTO.GenerateReview();

    List<LineItem> lineItems = newReview.getProducts();
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
      newReview = reviewRepository.save(newReview);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    return newReview.GenerateReviewDTO();
  }
}
