package io.catalyte.training.sportsproducts.domains.promo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromoValidatorTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  Promo testPromo;
  PromoTestHelper promoTestHelper;
  PromoValidator promoValidator;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    promoTestHelper = new PromoTestHelper();
    testPromo = promoTestHelper.generateValidPromoCode();
    promoValidator = new PromoValidator();
  }

  @Test
  public void validateNotNullOrEmptyReturnsErrorWhenNull() {
    String error = promoValidator.validateNotNullOrEmpty(null, "randomField");

    assertEquals("This field cannot be null or empty: randomField", error);
  }

  @Test
  public void validateNotNullOrEmptyReturnsErrorWhenEmpty() {
    String error = promoValidator.validateNotNullOrEmpty("", "randomField");

    assertEquals("This field cannot be null or empty: randomField", error);
  }

  @Test
  public void validateNotNullOrEmptyReturnsEmptyStringWhenValid() {
    String error = promoValidator.validateNotNullOrEmpty("bob", "randomField");

    assertEquals("", error);
  }

  @Test
  public void validateDescriptionReturnsNoErrorWhenValid() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateDescription(testPromo.getDescription(), errors);

    assertTrue(errors.isEmpty());
  }

  @Test
  public void validateDescriptionReturnsNullErrorWhenNull() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateDescription(null, errors);

    assertTrue(errors.contains(PromoValidator.nullError + "description"));
  }

  @Test
  public void validateDescriptionReturnsErrorWhenDescriptionIsLongerThan250Chars() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i <= 250; i++) {
      builder.append('c');
    }

    testPromo.setDescription(builder.toString());
    List<String> errors = new ArrayList<>();

    promoValidator.validateDescription(testPromo.getDescription(), errors);

    assertTrue(errors.contains(PromoValidator.descriptionError));
  }

  @Test
  public void validatTypeReturnsErrorWhenDescriptionIsLongerThan250Chars() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i <= 250; i++) {
      builder.append('c');
    }

    testPromo.setDescription(builder.toString());
    List<String> errors = new ArrayList<>();

    promoValidator.validateDescription(testPromo.getDescription(), errors);

    assertTrue(errors.contains(PromoValidator.descriptionError));
  }

  @Test
  public void validateTitleReturnsErrorWhenNull() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateTitle(null, errors);

    assertTrue(errors.contains(PromoValidator.nullError + "title"));
  }

  @Test
  public void validateTitleReturnsErrorWhenSpecialChars() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateTitle("#$!", errors);

    assertTrue(errors.contains(PromoValidator.titleError));
  }

  @Test
  public void validateTypeReturnsErrorWhenNull() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateType(null, errors);

    assertTrue(errors.contains(PromoValidator.nullError + "type"));
  }

  @Test
  public void validateTypeReturnsErrorWhenNotPercentOrFlat() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateType("bob", errors);

    assertTrue(errors.contains(PromoValidator.typeError));
  }

  @Test
  public void validateExpirationReturnsErrorWhenNull() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateExpiration(null, errors);

    assertTrue(errors.contains(PromoValidator.nullError + "expiration"));
  }

  @Test
  public void validateExpirationReturnsErrorWhenIncorrectFormat() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateExpiration("2022/02/02", errors);

    assertTrue(errors.contains(PromoValidator.expirationError));
  }

  @Test
  public void validateRateReturnsErrorWhenRateIsNull() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateRate(null, "percent", errors);

    assertTrue(errors.contains(PromoValidator.nullError + "rate"));
  }

  @Test
  public void validateRateReturnsErrorWhenTypeIsNull() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateRate("$25.25", null, errors);

    assertTrue(errors.contains(PromoValidator.nullError + "type"));
  }

  @Test
  public void validateRateReturnsErrorWhenPercentDoesntContainPercentSign() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateRate("20", "percent", errors);

    assertTrue(errors.contains(PromoValidator.percentError));
  }

  @Test
  public void validateRateReturnsErrorWhenPercentGreaterThan100() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateRate("101", "percent", errors);

    assertTrue(errors.contains(PromoValidator.percentError));
  }

  @Test
  public void validateRateReturnsErrorWhenPercentLessThanThan1() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateRate(".5", "percent", errors);

    assertTrue(errors.contains(PromoValidator.percentError));
  }

  @Test
  public void validateRateReturnsErrorWhenFlatHasNoDollarSign() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateRate("20.00", "flat", errors);

    assertTrue(errors.contains(PromoValidator.flatError));
  }

  @Test
  public void validateRateReturnsErrorWhenFlatHasNoDecimal() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateRate("20", "flat", errors);

    assertTrue(errors.contains(PromoValidator.flatError));
  }

  @Test
  public void validateRateReturnsErrorWhenFlatLessThanDollar() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateRate("0.20", "flat", errors);

    assertTrue(errors.contains(PromoValidator.flatError));
  }

  @Test
  public void validateRateReturnsErrorWhenGreaterThan99() {
    List<String> errors = new ArrayList<>();

    promoValidator.validateRate("100", "flat", errors);

    assertTrue(errors.contains(PromoValidator.flatError));
  }

  @Test
  public void validationDriverReturnsNoErrorWhenProvidedValidPromo() {
    List<String> errors;

    errors = promoValidator.validate(testPromo);

    assertTrue(errors.isEmpty());
  }

  @Test
  public void validationDriverReturnsErrorWhenPromoIsNull() {
    List<String> errors;
    testPromo = null;

    errors = promoValidator.validate(testPromo);

    assertTrue(errors.contains("The promo was null"));
  }

}
