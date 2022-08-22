package io.catalyte.training.sportsproducts.domains.purchase;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CreditCardValidation {

  private final Logger logger = LogManager.getLogger(CreditCardValidation.class);

  public void validCard(Purchase purchase) {
    Boolean masterCard = isCorrectCardType(purchase.getCreditCard().getCardNumber(),
        "^(?:5|2)[1-5][0-9]{14}$");
    Boolean visaCard = isCorrectCardType(purchase.getCreditCard().getCardNumber(),
        "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$");
    if (!masterCard && !visaCard) {
      throw new IllegalArgumentException("Invalid Card Type") {
      };
    }
    isCorrectCardInfo(purchase.getCreditCard().getCardHolder(),
        "^([a-zA-Z]{2,}\\s[a-zA-Z]+'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]+)?)","cardholder name");
    isCorrectCardInfo(String.valueOf(purchase.getBillingAddress().getBillingZip()),
        "^\\d{5}(-\\d{4})?$","Zip Code");
    isCorrectCardInfo(purchase.getCreditCard().getCvv(), "^\\d{3}$","CVV");
    isCorrectCardInfo(purchase.getCreditCard().getExpiration(),
        "^(0[1-9]|1[0-2])\\/?([0-9]{2})$","card expiration date");
    dateCheck(purchase.getCreditCard().getExpiration());
  }

  private boolean isCorrectCardType(String credential, String pattern) {
    Pattern credPattern = Pattern.compile(pattern);
    Matcher credMatcher = credPattern.matcher(credential);
    return credMatcher.matches();
  }

  private void isCorrectCardInfo(String credential, String pattern, String credentialType) {
    Pattern credPattern = Pattern.compile(pattern);
    Matcher credMatcher = credPattern.matcher(credential);
    if (!credMatcher.matches()) {
      throw new IllegalArgumentException(credential + " " + "does not meet requirements for the " + credentialType +".");
    }
  }

  public void dateCheck(String expirationDate) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yy");
    YearMonth yearMonth= YearMonth.parse(expirationDate,format);
    LocalDate cardExp = yearMonth.atEndOfMonth();
    if (cardExp.isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
      throw new IllegalArgumentException("Credit Card is expired.");
    }
  }

}
