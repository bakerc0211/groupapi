package io.catalyte.training.sportsproducts.domains.promo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.web.server.ResponseStatusException;

@RunWith(MockitoJUnitRunner.class)
public class PromoServiceImpTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @InjectMocks
  PromoServiceImpl promoService;

  @Mock
  PromoRepository promoRepository;

  @Mock
  PromoValidator promoValidator;

  Promo testPromo;
  PromoTestHelper promoTestHelper;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    promoTestHelper = new PromoTestHelper();
    testPromo = promoTestHelper.generateValidPromoCode();

    promoService = new PromoServiceImpl(promoRepository, promoValidator);

    when(promoRepository.save(any(Promo.class))).thenReturn(testPromo);
  }

  @Test
  public void savePromoHappyPathReturnsSavedPromo() {
    Promo result = promoService.savePromo(testPromo);

    assertEquals(result, testPromo);
  }

  @Test(expected = ServerError.class)
  public void savePromoThrowsServerErrorWhenDatabaseErrorIsCaught() {
    when(promoRepository.save(any(Promo.class))).thenThrow(
        new DataAccessException("error message") {
        });

    promoService.savePromo(testPromo);
  }

  @Test
  public void savePromoThrowsResponseStatusExceptionWhenDatabaseErrorIsCaught() {
    List<String> errors = new ArrayList<>();
    errors.add("error message");

    when(promoValidator.validate(any(Promo.class))).thenReturn(errors);

    Exception exception = assertThrows(ResponseStatusException.class, () -> {
      promoService.savePromo(testPromo);
    });

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains("400 BAD_REQUEST"));
    assertTrue(actualMessage.contains("error message"));
  }
}
