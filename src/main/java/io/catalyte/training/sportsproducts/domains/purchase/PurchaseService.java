package io.catalyte.training.sportsproducts.domains.purchase;

import java.util.List;

public interface PurchaseService {

  PurchaseDTO savePurchase(PurchaseDTO purchaseToSave);

  List<Purchase> findAllPurchasesByEmail(String email);

}
