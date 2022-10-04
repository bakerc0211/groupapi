package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.domains.purchase.dto.PurchaseDTO;

import io.catalyte.training.sportsproducts.domains.purchase.dto.ReviewDTO;
import java.util.List;

public interface PurchaseService {

  PurchaseDTO savePurchase(PurchaseDTO purchaseToSave);

  ReviewDTO saveReview(ReviewDTO reviewToSave);

  List<PurchaseDTO> findAllPurchasesByEmail(String email);

  Object[] findProductsPurchased();

  Object[] findProductsWithReviews();
}
