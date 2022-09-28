package io.catalyte.training.sportsproducts.domains.purchase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.catalyte.training.sportsproducts.domains.purchase.DeliveryAddress;

/**
 * DTO representing a DeliveryAddress
 */
public class DeliveryAddressDTO {
    @JsonProperty(required = true)
    private String firstName;
    @JsonProperty(required = true)
    private String lastName;
    @JsonProperty(required = true)
    private String deliveryStreet;
    private String deliveryStreet2;
    @JsonProperty(required = true)
    private String deliveryCity;
    @JsonProperty(required = true)
    private String deliveryState;
    @JsonProperty(required = true)
    private int deliveryZip;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryStreet2() {
        return deliveryStreet2;
    }

    public void setDeliveryStreet2(String deliveryStreet2) {
        this.deliveryStreet2 = deliveryStreet2;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public int getDeliveryZip() {
        return deliveryZip;
    }

    public void setDeliveryZip(int deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    /**
     * Generate a DeliveryAddress object from DTO
     * @return The DeliveryAddress object
     */
    public DeliveryAddress GenerateDeliveryAddress() {
        DeliveryAddress newDeliveryAddress = new DeliveryAddress();

        newDeliveryAddress.setFirstName(firstName);
        newDeliveryAddress.setLastName(lastName);
        newDeliveryAddress.setDeliveryStreet(deliveryStreet);
        newDeliveryAddress.setDeliveryStreet2(deliveryStreet2);
        newDeliveryAddress.setDeliveryState(deliveryState);
        newDeliveryAddress.setDeliveryZip(deliveryZip);
        newDeliveryAddress.setDeliveryCity(deliveryCity);

        return newDeliveryAddress;
    }
    
    @Override
    public String toString() {
        return "DeliveryAddress{" +
                "deliveryStreet='" + deliveryStreet + '\'' +
                ", deliveryStreet2='" + deliveryStreet2 + '\'' +
                ", deliveryCity='" + deliveryCity + '\'' +
                ", deliveryState='" + deliveryState + '\'' +
                ", deliveryZip=" + deliveryZip +
                ", firstName='" + firstName + '\'' +
                ", lastName=" + lastName +
                '}';
    }
}
