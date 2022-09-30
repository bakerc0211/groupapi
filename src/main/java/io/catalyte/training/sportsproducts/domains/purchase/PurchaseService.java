package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.domains.purchase.dto.PurchaseDTO;

import java.util.List;

public interface PurchaseService {

  PurchaseDTO savePurchase(PurchaseDTO purchaseToSave);

  List<PurchaseDTO> findAllPurchasesByEmail(String email);
  List<LineItem> findProductsPurchasedById(Long product_id);

  Object[] findProductsPurchased();
}
