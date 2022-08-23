package io.catalyte.training.sportsproducts.domains.purchase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CreditCardValidation {

  private final Logger logger = LogManager.getLogger(CreditCardValidation.class);

  public void validCard(Purchase purchase) {
    isValidCardType(purchase);
    isValidCardHolder(purchase);
    isValidCVV(purchase);
    isValidZip(purchase);
    dateCheck(purchase.getCreditCard().getExpiration());
  }

  private boolean isCorrectCardType(String credential, String pattern) {
    Pattern credPattern = Pattern.compile(pattern);
    Matcher credMatcher = credPattern.matcher(credential);
    return credMatcher.matches();
  }

  private void isCorrectCardHolder(String credential, String pattern) {
    Pattern credPattern = Pattern.compile(pattern);
    Matcher credMatcher = credPattern.matcher(credential);
    if (!credMatcher.matches()) {
      throw new IllegalArgumentException(
          "The cardholder name must not be empty and must have more than two characters.");
    }
  }

  private void isCorrectCVV(String credential, String pattern, String cvv) {
    Pattern credPattern = Pattern.compile(pattern);
    Matcher credMatcher = credPattern.matcher(credential);
    if (!credMatcher.matches()) {
      throw new IllegalArgumentException(
          "The CVV must be exactly 3 numbers.");
    }
  }

  private void isCorrectZipCode(String credential, String pattern, String zipCode) {
    Pattern credPattern = Pattern.compile(pattern);
    Matcher credMatcher = credPattern.matcher(String.valueOf(credential));
    if (!credMatcher.matches()) {
      throw new IllegalArgumentException(
          "The ZipCode must be in the format of XXXXX or XXXXX-XXXX.");
    }
  }

  public void dateCheck(String expirationDate) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yy");
    YearMonth yearMonth = YearMonth.parse(expirationDate, format);
    LocalDate cardExp = yearMonth.atEndOfMonth();
    if (cardExp.isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
      throw new IllegalArgumentException(
          "The date that you've entered shows the card is expired. Please use a non-expired card.");
    }
  }

  public void isValidCardType(Purchase purchase) {
    Boolean masterCard = isCorrectCardType(purchase.getCreditCard().getCardNumber(),
        "^(?:5|2)[1-5][0-9]{14}$");
    Boolean visaCard = isCorrectCardType(purchase.getCreditCard().getCardNumber(),
        "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$");
    if (!masterCard && !visaCard) {
      throw new IllegalArgumentException(
          "Only Visa cards and Mastercards are acceptable payment methods. Please input a valid card type.") {
      };
    }
  }

  public void isValidCardHolder(Purchase purchase) {
    isCorrectCardHolder(purchase.getCreditCard().getCardHolder(),
        "^([a-zA-Z]{2,}\\s[a-zA-Z]+'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]+)?)");
  }

  public void isValidCVV(Purchase purchase) {
    isCorrectCVV(purchase.getCreditCard().getCvv(), "^\\d{3}$", "CVV");
  }

  public void isValidZip(Purchase purchase) {
    isCorrectZipCode(String.valueOf(purchase.getBillingAddress().getBillingZip()),
        "^\\d{5}(-\\d{4})?", "Zip Code");
  }
}
