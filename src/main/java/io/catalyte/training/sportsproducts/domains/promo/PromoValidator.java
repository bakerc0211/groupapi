package io.catalyte.training.sportsproducts.domains.promo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PromoValidator {

  String regexDate = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/[0-9][0-9]$";
  String regexTitle = "^[0-9A-Z]*$";
  String regexPercent = "^%[1-9]$|^%[1-9][0-9]$";
  String regexFlat = "^[$][1-9][0-9]*.[0-9][0-9]$";

  public static final String titleError = "Title must only contain upper case letters and numbers.";
  public static final String descriptionError = "Description must be shorter than 250 characters.";
  public static final String typeError = "Type must either be percent or flat.";
  public static final String expirationError = "Expiration Date must be formatted as DD/MM/YY.";
  public static final String percentError = "Percent must be formatted as %NN and be a number between 1 and 99.";
  public static final String flatError = "Flat must be formatted as $NN.NN. The first digit must not be 0 and the cents should be between 00 and 99.";
  public static final String nullError = "This field cannot be null or empty: ";

  /**
   * Fully validates a promo code's fields.
   *
   * @param promo - the promo to validate.
   * @return - a list of validation error messages
   */
  public List<String> validate(Promo promo) {
    List<String> errorMessages = new ArrayList<>();

    if (promo == null) {
      errorMessages.add("The promo was null");
    } else {
      validateTitle(promo.getTitle(), errorMessages);
      validateDescription(promo.getDescription(), errorMessages);
      validateType(promo.getType(), errorMessages);
      validateExpiration(promo.getExpirationDate(), errorMessages);
      validateRate(promo.getRate(), promo.getType(), errorMessages);
    }

    return errorMessages;
  }

  /**
   * Validates a String value by checking if it is null or empty string.
   *
   * @param valueToValidate - the string to be validated
   * @param fieldName       - the name of the field, to generate a relevant error message
   * @return - an error message or empty string if no error was found
   */
  public String validateNotNullOrEmpty(String valueToValidate, String fieldName) {
    if (valueToValidate == null || valueToValidate.equals("")) {
      return nullError + fieldName;
    } else {
      return "";
    }
  }

  /**
   * Validates a title field and adds an error message to the list if a problem is found.
   *
   * @param title         - the title to validate
   * @param errorMessages
   */
  public void validateTitle(String title, List<String> errorMessages) {
    String nullError = validateNotNullOrEmpty(title, "title");

    if (nullError.isEmpty()) {
      if (!title.matches(regexTitle)) {
        errorMessages.add(titleError);
      }
    } else {
      errorMessages.add(nullError);
    }
  }

  /**
   * Validates a description field and adds an error message to the list if a problem is found.
   *
   * @param description   - the description to validate
   * @param errorMessages
   */
  public void validateDescription(String description, List<String> errorMessages) {
    String nullError = validateNotNullOrEmpty(description, "description");

    if (nullError.isEmpty()) {
      if (description.length() > 250) {
        errorMessages.add(descriptionError);
      }
    } else {
      errorMessages.add(nullError);
    }
  }

  /**
   * Validates a type field and adds an error message to the list if a problem is found.
   *
   * @param type          - the type to validate
   * @param errorMessages
   */
  public void validateType(String type, List<String> errorMessages) {
    String nullError = validateNotNullOrEmpty(type, "type");

    if (nullError.isEmpty()) {
      if (!type.equalsIgnoreCase("percent") && !type.equalsIgnoreCase("flat")) {
        errorMessages.add(typeError);
      }
    } else {
      errorMessages.add(nullError);
    }
  }

  /**
   * Validates an expiration field and adds an error message to the list if a problem is found.
   *
   * @param expiration    - expiration type to validate
   * @param errorMessages
   */
  public void validateExpiration(String expiration, List<String> errorMessages) {
    String nullError = validateNotNullOrEmpty(expiration, "expiration");

    if (nullError.isEmpty()) {
      if (!expiration.matches(regexDate)) {
        errorMessages.add(expirationError);
      }
    } else {
      errorMessages.add(nullError);
    }
  }

  /**
   * Validates a rate field and adds an error message to the list if a problem is found.
   *
   * @param rate          - rate type to validate
   * @param errorMessages
   */
  public void validateRate(String rate, String type, List<String> errorMessages) {
    String rateNullError = validateNotNullOrEmpty(rate, "rate");
    String typeNullError = validateNotNullOrEmpty(type, "type");

    if (!rateNullError.isEmpty()) {
      errorMessages.add(rateNullError);
    } else if (!typeNullError.isEmpty()) {
      errorMessages.add(typeNullError);
    } else {
      if (type.equals("percent") && !rate.matches(regexPercent)) {
        errorMessages.add(percentError);
      } else if (type.equals("flat") && !rate.matches(regexFlat)) {
        errorMessages.add(flatError);
      }
    }
  }
}
