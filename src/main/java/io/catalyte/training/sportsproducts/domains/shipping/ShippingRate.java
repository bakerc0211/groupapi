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
  private Double shippingCost;

  public ShippingRate(String usState, Double shippingCost) {
    this.usState = usState;
    this.shippingCost = shippingCost;
  }

  public String getUsState() {
    return usState;
  }

  public void setUsState(String usState) {
    this.usState = usState;
  }

  public Double getShippingCost() {
    return shippingCost;
  }

  public void setShippingCost(Double shippingCost) {
    this.shippingCost = shippingCost;
  }
}