package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.purchase.dto.LineItemDTO;

import java.util.Objects;
import javax.persistence.*;

/**
 * Describes one line item of a purchase transaction
 */
@Entity
public class LineItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Purchase purchase;

  @ManyToOne(fetch= FetchType.EAGER, cascade = CascadeType.MERGE)
  private Product product;

  private int quantity;

  public LineItem() {
  }

  public LineItem(Long id, Purchase purchase, Product product, int quantity) {
    this.id = id;
    this.purchase = purchase;
    this.product = product;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Purchase getPurchase() {
    return purchase;
  }

  public void setPurchase(Purchase purchase) {
    this.purchase = purchase;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    LineItem lineItem = (LineItem) o;

    if (quantity != lineItem.quantity) {
      return false;
    }
    if (!Objects.equals(purchase, lineItem.purchase)) {
      return false;
    }
    return Objects.equals(product, lineItem.product);
  }

  /**
   * Generate a LineItemDTO object
   * @return The LineItemDTO object
   */
  public LineItemDTO GenerateLineItemDTO() {
    LineItemDTO newLineItemDTO = new LineItemDTO();

    newLineItemDTO.setId(product.getId());
    newLineItemDTO.setQuantity(quantity);

    return newLineItemDTO;
  }

  @Override
  public int hashCode() {
    int result = purchase != null ? purchase.hashCode() : 0;
    result = 31 * result + (product != null ? product.hashCode() : 0);
    result = 31 * result + quantity;
    return result;
  }

  @Override
  public String toString() {
    return "LineItem{" +
        "id=" + id +
        ", purchase=" + purchase +
        ", product=" + product +
        ", quantity=" + quantity +
        '}';
  }
}
