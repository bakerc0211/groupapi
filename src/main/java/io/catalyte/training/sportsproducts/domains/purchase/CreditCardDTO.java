package io.catalyte.training.sportsproducts.domains.purchase;

public class CreditCardDTO {
    private String cardNumber;
    private String cvv;
    private String expiration;
    private String cardHolder;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public CreditCard GenerateCreditCard() {
        CreditCard newCreditCard = new CreditCard();

        newCreditCard.setCardHolder(cardHolder);
        newCreditCard.setCardNumber(cardNumber);
        newCreditCard.setExpiration(expiration);
        newCreditCard.setCvv(cvv);

        return newCreditCard;
    }
}
