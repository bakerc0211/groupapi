package io.catalyte.training.sportsproducts.domains.purchase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CreditCardValidation {

  private final Logger logger = LogManager.getLogger(CreditCardValidation.class);

  public void validCard(Purchase purchase) {
    Boolean masterCard = correctCardType(purchase.getCreditCard().getCardNumber(),
        "^[5|2][1-5][0-9]{14}$");
    Boolean visaCard = correctCardType(purchase.getCreditCard().getCardNumber(),
        "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$");
    if (!(masterCard && visaCard)) {
      throw new IllegalArgumentException("Invalid Card Type") {
      };
    }
    correctCardInfo(purchase.getCreditCard().getCardholder(),
        "^([a-zA-Z]{2,}\\s[a-zA-Z]+'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]+)?)");
    correctCardInfo(String.valueOf(purchase.getBillingAddress().getBillingZip()),
        "^\\d{5}(-\\d{4})?$");
    correctCardInfo(purchase.getCreditCard().getCvv(), "^\\d{3}$");
    correctCardInfo(purchase.getCreditCard().getExpiration(),
        "^(0[1-9]|1[0-2])\\/?([0-9]{2})$");
    dateCheck(purchase.getCreditCard().getExpiration());
  }

  private boolean correctCardType(String credential, String pattern) {
    Pattern credPattern = Pattern.compile(pattern);
    Matcher credMatcher = credPattern.matcher(credential);
    return credMatcher.matches();
  }

  private void correctCardInfo(String credential, String pattern) {
    Pattern credPattern = Pattern.compile(pattern);
    Matcher credMatcher = credPattern.matcher(credential);
    if (!credMatcher.matches()) {
      throw new IllegalArgumentException(credential + "does not meet requirements");
    }
  }

  public void dateCheck(String expirationDate) {
    String currentTime = LocalDateTime.now().toString();
    DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MM/yy");
    LocalDateTime cardExp = LocalDateTime.parse(expirationDate, displayFormat);
    LocalDateTime localTime = LocalDateTime.parse(currentTime, displayFormat);
    if (cardExp.isBefore(localTime)) {
      throw new IllegalArgumentException("Card is expired");
    }
  }

}
