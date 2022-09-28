package io.catalyte.training.sportsproducts.domains.purchase;

import java.util.List;

public interface PurchaseService {

  Purchase savePurchase(Purchase purchaseToSave);

  List<Purchase> findAllPurchasesByEmail(String email);
  List<LineItem> findProductsPurchasedById(Long product_id);
}
