package io.catalyte.training.sportsproducts.domains.shipping;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ShippingRate {

  @Id
  private String usState;
  private String shippingCost;

  public ShippingRate(String usState, String shippingCost) {
    this.usState = usState;
    this.shippingCost = shippingCost;
  }

  public String getUsState() {
    return usState;
  }

  public void setUsState(String usState) {
    this.usState = usState;
  }

  public String getShippingCost() {
    return shippingCost;
  }

  public void setShippingCost(String shippingCost) {
    this.shippingCost = shippingCost;
  }
}