package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.domains.purchase.dto.LineItemDTO;
import io.catalyte.training.sportsproducts.domains.purchase.dto.PurchaseDTO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Describes a purchase object that holds the information for a transaction
 */
@Entity
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
  private List<LineItem> products;

  private DeliveryAddress deliveryAddress;

  private BillingAddress billingAddress;

  private CreditCard creditCard;
  public Purchase() {
    billingAddress = new BillingAddress();
    deliveryAddress = new DeliveryAddress();
    creditCard = new CreditCard();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<LineItem> getProducts() {
    return products;
  }

  public void setProducts(List<LineItem> products) {
    this.products = products;
  }

  public DeliveryAddress getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  public BillingAddress getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(BillingAddress billingAddress) {
    this.billingAddress = billingAddress;
  }

  public CreditCard getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(CreditCard creditCard) {
    this.creditCard = creditCard;
  }

  /**
   * Generate a DTO from Purchase object
   * @return The PurchaseDTO object
   */
  public PurchaseDTO GeneratePurchaseDTO() {
    PurchaseDTO newPurchaseDTO = new PurchaseDTO();

    newPurchaseDTO.setId(id);

    newPurchaseDTO.setDeliveryAddress(this.deliveryAddress.GenerateDeliveryAddressDTO());
    newPurchaseDTO.setBillingAddress(this.billingAddress.GenerateBillingAddressDTO());

    List<LineItemDTO> productList = new ArrayList<LineItemDTO>();
    products.forEach((product) -> productList.add(product.GenerateLineItemDTO()));
    newPurchaseDTO.setProducts(productList);

    return newPurchaseDTO;
  }

  @Override
  public String toString() {
    return "Purchase{" +
        "id=" + id +
        ", deliveryAddress=" + deliveryAddress +
        ", billingAddress=" + billingAddress +
        ", creditCard=" + creditCard +
        '}';
  }
}