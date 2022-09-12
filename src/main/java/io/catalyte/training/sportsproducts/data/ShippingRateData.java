package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.shipping.ShippingRate;
import io.catalyte.training.sportsproducts.domains.shipping.ShippingRateRepository;
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

        new ShippingRate("Alabama", 5.00),
        new ShippingRate("Alaska", 10.00),
        new ShippingRate("American Samoa", 10.00),
        new ShippingRate("Arizona", 5.00),
        new ShippingRate("Arkansas", 5.00),
        new ShippingRate("California", 5.00),
        new ShippingRate("Colorado", 5.00),
        new ShippingRate("Connecticut", 5.00),
        new ShippingRate("Delaware", 5.00),
        new ShippingRate("District of Columbia", 5.00),
        new ShippingRate("Federated States of Micronesia", 10.00),
        new ShippingRate("Florida", 5.00),
        new ShippingRate("Georgia", 5.00),
        new ShippingRate("Guam", 10.00),
        new ShippingRate("Hawaii", 10.00),
        new ShippingRate("Idaho", 5.00),
        new ShippingRate("Illinois", 5.00),
        new ShippingRate("Indiana", 5.00),
        new ShippingRate("Iowa", 5.00),
        new ShippingRate("Kansas", 5.00),
        new ShippingRate("Kentucky", 5.00),
        new ShippingRate("Louisiana", 5.00),
        new ShippingRate("Maine", 5.00),
        new ShippingRate("Marshall Islands", 10.00),
        new ShippingRate("Maryland", 5.00),
        new ShippingRate("Massachusetts", 5.00),
        new ShippingRate("Michigan", 5.00),
        new ShippingRate("Minnesota", 5.00),
        new ShippingRate("Mississippi", 5.00),
        new ShippingRate("Missouri", 5.00),
        new ShippingRate("Montana", 5.00),
        new ShippingRate("Nebraska", 5.00),
        new ShippingRate("Nevada", 5.00),
        new ShippingRate("New Hampshire", 5.00),
        new ShippingRate("New Jersey", 5.00),
        new ShippingRate("New Mexico", 5.00),
        new ShippingRate("New York", 5.00),
        new ShippingRate("North Carolina", 5.00),
        new ShippingRate("North Dakota", 5.00),
        new ShippingRate("Northern Mariana Islands", 10.00),
        new ShippingRate("Ohio", 5.00),
        new ShippingRate("Oklahoma", 5.00),
        new ShippingRate("Oregon", 5.00),
        new ShippingRate("Palau", 10.00),
        new ShippingRate("Pennsylvania", 5.00),
        new ShippingRate("Puerto Rico", 10.00),
        new ShippingRate("Rhode Island", 5.00),
        new ShippingRate("South Carolina", 5.00),
        new ShippingRate("South Dakota", 5.00),
        new ShippingRate("Tennessee", 5.00),
        new ShippingRate("Texas", 5.00),
        new ShippingRate("Utah", 5.00),
        new ShippingRate("Vermont", 5.00),
        new ShippingRate("Virgin Islands", 10.00),
        new ShippingRate("Virginia", 5.00),
        new ShippingRate("Washington", 5.00),
        new ShippingRate("West Virginia", 5.00),
        new ShippingRate("Wisconsin", 5.00),
        new ShippingRate("Wyoming", 5.00))
    );
  }
}