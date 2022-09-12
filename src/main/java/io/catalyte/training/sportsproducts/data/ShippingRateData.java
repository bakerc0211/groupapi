package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.shipping.ShippingRate;
import io.catalyte.training.sportsproducts.domains.shipping.ShippingRateRepository;
import java.text.DecimalFormat;
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

            new ShippingRate("Alabama", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Alaska", (new DecimalFormat("$#,###.00").format(10.00F))),
            new ShippingRate("American Samoa", (new DecimalFormat("$#,###.00").format(10.00F))),
            new ShippingRate("Arizona", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Arkansas", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("California", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Colorado", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Connecticut", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Delaware", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("District of Columbia", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Federated States of Micronesia", (new DecimalFormat("$#,###.00").format(10.00F))),
            new ShippingRate("Florida", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Georgia", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Guam", (new DecimalFormat("$#,###.00").format(10.00F))),
            new ShippingRate("Hawaii", (new DecimalFormat("$#,###.00").format(10.00F))),
            new ShippingRate("Idaho", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Illinois", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Indiana", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Iowa", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Kansas", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Kentucky", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Louisiana", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Maine", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Marshall Islands", (new DecimalFormat("$#,###.00").format(10.00F))),
            new ShippingRate("Maryland", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Massachusetts", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Michigan", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Minnesota", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Mississippi", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Missouri", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Montana", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Nebraska", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Nevada", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("New Hampshire", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("New Jersey", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("New Mexico", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("New York", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("North Carolina", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("North Dakota", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Northern Mariana Islands", (new DecimalFormat("$#,###.00").format(10.00F))),
            new ShippingRate("Ohio", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Oklahoma", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Oregon", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Palau", (new DecimalFormat("$#,###.00").format(10.00F))),
            new ShippingRate("Pennsylvania", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Puerto Rico", (new DecimalFormat("$#,###.00").format(10.00F))),
            new ShippingRate("Rhode Island", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("South Carolina", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("South Dakota", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Tennessee", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Texas", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Utah", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Vermont", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Virgin Islands", (new DecimalFormat("$#,###.00").format(10.00F))),
            new ShippingRate("Virginia", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Washington", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("West Virginia", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Wisconsin", (new DecimalFormat("$#,###.00").format(5.00F))),
            new ShippingRate("Wyoming", (new DecimalFormat("$#,###.00").format(5.00F)))
        )
    );
  }
}
