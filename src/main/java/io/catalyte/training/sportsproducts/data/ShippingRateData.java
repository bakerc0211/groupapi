package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.purchase.ShippingRate;
import io.catalyte.training.sportsproducts.domains.purchase.ShippingRateRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ShippingRateData implements CommandLineRunner {

  @Autowired
  ShippingRateRepository eRepo;

  @Override
  public void run(String... args) throws Exception {

    eRepo.saveAll(Arrays.asList(

        new ShippingRate("Alabama", 5.00F),
        new ShippingRate("Alaska", 10.00F),
        new ShippingRate("American Samoa", 5.00F),
        new ShippingRate("Arizona", 5.00F),
        new ShippingRate("Arkansas", 5.00F),
        new ShippingRate("California", 5.00F),
        new ShippingRate("Colorado", 5.00F),
        new ShippingRate("Connecticut", 5.00F),
        new ShippingRate("Delaware", 5.00F),
        new ShippingRate("District of Columbia", 5.00F),
        new ShippingRate("Federated States of Micronesia", 5.00F),
        new ShippingRate("Florida", 5.00F),
        new ShippingRate("Georgia", 5.00F),
        new ShippingRate("Guam", 5.00F),
        new ShippingRate("Hawaii", 10.00F),
        new ShippingRate("Idaho", 5.00F),
        new ShippingRate("Illinois", 5.00F),
        new ShippingRate("Indiana", 5.00F),
        new ShippingRate("Iowa", 5.00F),
        new ShippingRate("Kansas", 5.00F),
        new ShippingRate("Kentucky", 5.00F),
        new ShippingRate("Louisiana", 5.00F),
        new ShippingRate("Maine", 5.00F),
        new ShippingRate("Marshall Islands", 5.00F),
        new ShippingRate("Maryland", 5.00F),
        new ShippingRate("Massachusetts", 5.00F),
        new ShippingRate("Michigan", 5.00F),
        new ShippingRate("Minnesota", 5.00F),
        new ShippingRate("Mississippi", 5.00F),
        new ShippingRate("Missouri", 5.00F),
        new ShippingRate("Montana", 5.00F),
        new ShippingRate("Nebraska", 5.00F),
        new ShippingRate("Nevada", 5.00F),
        new ShippingRate("New Hampshire", 5.00F),
        new ShippingRate("New Jersey", 5.00F),
        new ShippingRate("New Mexico", 5.00F),
        new ShippingRate("New York", 5.00F),
        new ShippingRate("North Carolina", 5.00F),
        new ShippingRate("North Dakota", 5.00F),
        new ShippingRate("Northern Mariana Islands", 5.00F),
        new ShippingRate("Ohio", 5.00F),
        new ShippingRate("Oklahoma", 5.00F),
        new ShippingRate("Oregon", 5.00F),
        new ShippingRate("Palau", 5.00F),
        new ShippingRate("Pennsylvania", 5.00F),
        new ShippingRate("Puerto Rico", 5.00F),
        new ShippingRate("Rhode Island", 5.00F),
        new ShippingRate("South Carolina", 5.00F),
        new ShippingRate("South Dakota", 5.00F),
        new ShippingRate("Tennessee", 5.00F),
        new ShippingRate("Texas", 5.00F),
        new ShippingRate("Utah", 5.00F),
        new ShippingRate("Vermont", 5.00F),
        new ShippingRate("Virgin Islands", 5.00F),
        new ShippingRate("Virginia", 5.00F),
        new ShippingRate("Washington", 5.00F),
        new ShippingRate("West Virginia", 5.00F),
        new ShippingRate("Wisconsin", 5.00F),
        new ShippingRate("Wyoming", 5.00F)
        )
    );
  }
}