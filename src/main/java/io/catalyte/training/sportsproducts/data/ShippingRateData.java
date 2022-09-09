package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.purchase.ShippingRate;
import io.catalyte.training.sportsproducts.domains.purchase.ShippingRateRepository;
import java.util.Arrays;
import java.util.List;
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

        new ShippingRate("Alabama", 5.00))
    );
  }
}