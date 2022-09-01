package io.catalyte.training.sportsproducts.domains.promo;

public class PromoTestHelper {

  public Promo generateValidPromoCode(){
    return new Promo("A", "something", "Flat", "$22.22", "22/10/24");
  }

}
