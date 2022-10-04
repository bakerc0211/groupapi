package io.catalyte.training.sportsproducts.domains.purchase.dto;

import io.catalyte.training.sportsproducts.domains.purchase.BillingAddress;

/**
 * DTO representing a BillingAddress
 */
public class BillingAddressDTO {
    private String billingStreet;
    private String billingStreet2;
    private String billingCity;
    private String billingState;
    private String billingZip;
    private String email;
    private String phone;

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingStreet2() {
        return billingStreet2;
    }

    public void setBillingStreet2(String billingStreet2) {
        this.billingStreet2 = billingStreet2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingZip() {
        return billingZip;
    }

    public void setBillingZip(String billingZip) {
        this.billingZip = billingZip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Generate BillingAddress object from DTO
     * @return The BillingAddress object
     */
    public BillingAddress GenerateBillingAddress() {
        BillingAddress newBillingAddress = new BillingAddress();

        newBillingAddress.setBillingStreet(billingStreet);
        newBillingAddress.setBillingStreet2(billingStreet2);
        newBillingAddress.setBillingState(billingState);
        newBillingAddress.setBillingZip(billingZip);
        newBillingAddress.setBillingCity(billingCity);
        newBillingAddress.setEmail(email);
        newBillingAddress.setPhone(phone);

        return newBillingAddress;
    }

    @Override
    public String toString() {
        return "BillingAddress{" +
                "billingStreet='" + billingStreet + '\'' +
                ", billingStreet2='" + billingStreet2 + '\'' +
                ", billingCity='" + billingCity + '\'' +
                ", billingState='" + billingState + '\'' +
                ", billingZip=" + billingZip +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
