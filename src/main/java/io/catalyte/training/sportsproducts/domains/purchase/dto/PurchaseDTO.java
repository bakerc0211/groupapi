package io.catalyte.training.sportsproducts.domains.purchase.dto;

import io.catalyte.training.sportsproducts.domains.purchase.LineItem;
import io.catalyte.training.sportsproducts.domains.purchase.Purchase;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO representing a purchase
 */
public class PurchaseDTO {
    private Long id;

    private List<LineItemDTO> products;

    private DeliveryAddressDTO deliveryAddress;

    private BillingAddressDTO billingAddress;

    private CreditCardDTO creditCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LineItemDTO> getProducts() {
        return products;
    }

    public void setProducts(List<LineItemDTO> products) {
        this.products = products;
    }

    public DeliveryAddressDTO getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddressDTO deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public BillingAddressDTO getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddressDTO billingAddress) {
        this.billingAddress = billingAddress;
    }

    public CreditCardDTO getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardDTO creditCard) {
        this.creditCard = creditCard;
    }

    public PurchaseDTO() {
        billingAddress = new BillingAddressDTO();
        deliveryAddress = new DeliveryAddressDTO();
        creditCard = new CreditCardDTO();
    }

    /**
     * Generate a Purchase object from DTO
     * @return The Purchase object
     */
    public Purchase GeneratePurchase() {
        Purchase newPurchase = new Purchase();

        newPurchase.setDeliveryAddress(this.deliveryAddress.GenerateDeliveryAddress());
        newPurchase.setBillingAddress(this.billingAddress.GenerateBillingAddress());
        newPurchase.setCreditCard(this.creditCard.GenerateCreditCard());

        List<LineItem> productList = new ArrayList<LineItem>();
        products.forEach((product) -> productList.add(product.GenerateLineItem()));
        newPurchase.setProducts(productList);

        return newPurchase;
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
