package io.catalyte.training.sportsproducts.domains.promo;

import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Provides implementation for the PromoService interface
 */
@Service
public class PromoServiceImpl implements PromoService {

  private final Logger logger = LogManager.getLogger(PromoServiceImpl.class);
  private final PromoRepository promoRepository;
  private final PromoValidator promoValidator;

  @Autowired
  public PromoServiceImpl(PromoRepository promoRepository, PromoValidator promoValidator) {
    this.promoRepository = promoRepository;
    this.promoValidator = promoValidator;
  }

  /**
   * Saves promo to the repository, checks for any incorrect inputs
   *
   * @param newPromo promo to save
   * @return saved promo from repository
   */
  public Promo savePromo(Promo newPromo) {
    List<String> errorMessages = promoValidator.validate(newPromo);

    if (errorMessages.isEmpty()) {
      try {
        promoRepository.save(newPromo);
      } catch (DataAccessException e) {
        logger.error(e.getMessage());
        throw new ServerError(e.getMessage());
      }
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessages.toString());
    }
    return newPromo;
  }
}
