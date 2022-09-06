package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.domains.product.Product;

import java.util.HashSet;
import java.util.Set;

public class PurchaseTestHelper {

    public static DeliveryAddress generateValidDeliveryAddress() {
        DeliveryAddress validDeliveryAddress = new DeliveryAddress();
        validDeliveryAddress.setDeliveryCity("Birmingham");
        validDeliveryAddress.setDeliveryState("AL");
        validDeliveryAddress.setDeliveryZip(43690);
        validDeliveryAddress.setDeliveryStreet("123 Hickley St");

        return validDeliveryAddress;
    }

    public static BillingAddress generateValidBillingAddress() {
        BillingAddress validBillingAddress = new BillingAddress();
        validBillingAddress.setBillingCity("Birmingham");
        validBillingAddress.setBillingState("AL");
        validBillingAddress.setBillingZip("43690");
        validBillingAddress.setBillingStreet("123 Hickley St");

        return validBillingAddress;
    }

    public static CreditCard generateValidCreditCard() {
        CreditCard validCreditCard = new CreditCard();
        validCreditCard.setCardNumber("4435678998761234");
        validCreditCard.setCvv("789");
        validCreditCard.setExpiration("11/26");
        validCreditCard.setCardHolder("Max Perkins");

        return validCreditCard;
    }

    public static Set<LineItem> generateValidLineItems() {
        Set<LineItem> validLineItems = new HashSet<LineItem>();

        Product validLineProduct = new Product();
        validLineProduct.setId(1L);
        validLineProduct.setName("X");
        validLineProduct.setBrand("X");
        validLineProduct.setDescription("X");
        validLineProduct.setCategory("X");
        validLineProduct.setType("X");
        validLineProduct.setReleaseDate("X");
        validLineProduct.setPrimaryColorCode("X");
        validLineProduct.setSecondaryColorCode("X");
        validLineProduct.setStyleNumber("X");
        validLineProduct.setGlobalProductCode("X");
        validLineProduct.setImageSrc("X");
        validLineProduct.setMaterial("X");
        validLineProduct.setPrice(0.0f);
        validLineProduct.setQuantity(1);
        validLineProduct.setActive(true);

        LineItem validLineItem = new LineItem();

        validLineItem.setQuantity(1);
        validLineItem.setProduct(validLineProduct);

        validLineItems.add(validLineItem);

        return validLineItems;
    }

    public static Set<LineItem> generateInactiveLineItems() {
        Set<LineItem> validLineItems = new HashSet<LineItem>();

        Product validLineProduct = new Product();
        validLineProduct.setId(3L);
        validLineProduct.setName("X");
        validLineProduct.setBrand("X");
        validLineProduct.setDescription("X");
        validLineProduct.setCategory("X");
        validLineProduct.setType("X");
        validLineProduct.setReleaseDate("X");
        validLineProduct.setPrimaryColorCode("X");
        validLineProduct.setSecondaryColorCode("X");
        validLineProduct.setStyleNumber("X");
        validLineProduct.setGlobalProductCode("X");
        validLineProduct.setImageSrc("X");
        validLineProduct.setMaterial("X");
        validLineProduct.setPrice(0.0f);
        validLineProduct.setQuantity(1);
        validLineProduct.setActive(true);

        LineItem validLineItem = new LineItem();

        validLineItem.setQuantity(1);
        validLineItem.setProduct(validLineProduct);

        validLineItems.add(validLineItem);

        return validLineItems;
    }
    public static Purchase generateValidPurchase() {
        Purchase validPurchase = new Purchase();

        validPurchase.setDeliveryAddress(generateValidDeliveryAddress());
        validPurchase.setBillingAddress(generateValidBillingAddress());
        validPurchase.setCreditCard(generateValidCreditCard());
        validPurchase.setProducts(generateValidLineItems());

        return validPurchase;
    }
}
