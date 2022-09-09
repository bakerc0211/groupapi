package io.catalyte.training.sportsproducts.domains.purchase;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShippingRate {

  @Id
  private String state;
  private String shippingCost;

}