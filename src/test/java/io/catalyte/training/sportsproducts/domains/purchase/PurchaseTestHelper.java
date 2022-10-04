package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.domains.purchase.dto.*;

import java.util.ArrayList;
import java.util.List;

public class PurchaseTestHelper {

    public static DeliveryAddressDTO generateValidDeliveryAddress() {
        DeliveryAddressDTO validDeliveryAddress = new DeliveryAddressDTO();
        validDeliveryAddress.setDeliveryCity("Birmingham");
        validDeliveryAddress.setDeliveryState("AL");
        validDeliveryAddress.setDeliveryZip(43690);
        validDeliveryAddress.setDeliveryStreet("123 Hickley St");

        return validDeliveryAddress;
    }

    public static BillingAddressDTO generateValidBillingAddress() {
        BillingAddressDTO validBillingAddress = new BillingAddressDTO();
        validBillingAddress.setBillingCity("Birmingham");
        validBillingAddress.setBillingState("AL");
        validBillingAddress.setBillingZip("43690");
        validBillingAddress.setBillingStreet("123 Hickley St");

        return validBillingAddress;
    }

    public static CreditCardDTO generateValidCreditCard() {
        CreditCardDTO validCreditCard = new CreditCardDTO();
        validCreditCard.setCardNumber("4435678998761234");
        validCreditCard.setCvv("789");
        validCreditCard.setExpiration("11/26");
        validCreditCard.setCardHolder("Max Perkins");

        return validCreditCard;
    }

    public static List<LineItemDTO> generateValidLineItems() {
        List<LineItemDTO> validLineItems = new ArrayList<LineItemDTO>();

        LineItemDTO validLineItem = new LineItemDTO();

        validLineItem.setQuantity(1);
        validLineItem.setId(1L);

        validLineItems.add(validLineItem);

        return validLineItems;
    }

    public static List<LineItemDTO> generateInactiveLineItems() {
        List<LineItemDTO> validLineItems = new ArrayList<LineItemDTO>();

        LineItemDTO validLineItem = new LineItemDTO();

        validLineItem.setQuantity(1);
        validLineItem.setId(3L);

        validLineItems.add(validLineItem);

        return validLineItems;
    }
    public static PurchaseDTO generateValidPurchase() {
        PurchaseDTO validPurchase = new PurchaseDTO();

        validPurchase.setDeliveryAddress(generateValidDeliveryAddress());
        validPurchase.setBillingAddress(generateValidBillingAddress());
        validPurchase.setCreditCard(generateValidCreditCard());
        validPurchase.setProducts(generateValidLineItems());

        return validPurchase;
    }
}
