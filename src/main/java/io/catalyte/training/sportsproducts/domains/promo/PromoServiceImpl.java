package io.catalyte.training.sportsproducts.domains.promo;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Provides implementation for the PromoService interface
 */
@Service
public class PromoServiceImpl implements PromoService {

  private final Logger logger = LogManager.getLogger(PromoServiceImpl.class);

  PromoRepository promoRepository;

  PromoService promoService;

  @Autowired
  public PromoServiceImpl(PromoRepository promoRepository) {
    this.promoRepository = promoRepository;

  }

  /**
   * Saves promo to the repository, checks for any incorrect inputs
   * @param newPromo promo to save
   * @return saved promo from repository
   */
  public Promo savePromo(Promo newPromo) {
    String regexDate = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/[0-9][0-9]$";
    String regexTitle = "^[0-9A-Z]*$";
    String regexPercent = "^%[1-9]$|^%[1-9][0-9]$";
    String regexFlat = "^[$][1-9][0-9]*.[0-9][0-9]$";

    if (!newPromo.getTitle().matches(regexTitle)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title must only contain "
          + "upper case letters and numbers.");

    } else if (newPromo.getDescription().length() > 250) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description must be shorter"
          + "than 250 characters.");
    } else if (!newPromo.getType().equalsIgnoreCase("percent") &&
        !newPromo.getType().equalsIgnoreCase("flat")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Type must either be "
          + "percent or flat.");
    } else if (!newPromo.getExpirationDate().matches(regexDate)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Expiration Date must be "
          + "formatted as DD/MM/YY.");
    } else if (newPromo.getType().equalsIgnoreCase("percent") &&
        !newPromo.getRate().matches(regexPercent)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Percent must be formatted as "
          + "%NN and be a number between 1 and 99.");
    } else if (newPromo.getType().equalsIgnoreCase("flat") &&
        !newPromo.getRate().matches(regexFlat)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flat must be formatted as "
          + "$NN.NN. The first digit must not be 0 and the cents should be between 00 and 99.");
    }
    promoRepository.save(newPromo);
    return newPromo;
  }
}
