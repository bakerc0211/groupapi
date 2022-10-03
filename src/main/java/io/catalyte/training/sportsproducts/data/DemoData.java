package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.product.ProductRepository;
import io.catalyte.training.sportsproducts.domains.purchase.BillingAddress;
import io.catalyte.training.sportsproducts.domains.purchase.CreditCard;
import io.catalyte.training.sportsproducts.domains.purchase.LineItem;
import io.catalyte.training.sportsproducts.domains.purchase.Purchase;
import io.catalyte.training.sportsproducts.domains.purchase.PurchaseRepository;
import io.catalyte.training.sportsproducts.domains.purchase.Review;
import io.catalyte.training.sportsproducts.domains.purchase.ReviewRepository;
import io.catalyte.training.sportsproducts.domains.purchase.dto.LineItemDTO;
import io.catalyte.training.sportsproducts.domains.user.User;
import io.catalyte.training.sportsproducts.domains.user.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Because this class implements CommandLineRunner, the run method is executed as soon as the server
 * successfully starts and before it begins accepting requests from the outside. Here, we use this
 * as a place to run some code that generates and saves a list of random products into the
 * database.
 */
@Component
public class DemoData implements CommandLineRunner {

  private final Logger logger = LogManager.getLogger(DemoData.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private PurchaseRepository purchaseRepository;

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private Environment env;

  ProductFactory productFactory = new ProductFactory();

  public static final int DEFAULT_NUMBER_OF_PRODUCTS = 500;

  @Override
  public void run(String... strings) {
    boolean loadData;

    try {
      // Retrieve the value of custom property in application.yml
      loadData = Boolean.parseBoolean(env.getProperty("products.load"));
    } catch (NumberFormatException nfe) {
      logger.error("config variable loadData could not be parsed, falling back to default");
      loadData = true;
    }

    if (loadData) {
      seedDatabase();
    }
  }

  private void seedDatabase() {
    int numberOfProducts;

    try {
      // Retrieve the value of custom property in application.yml
      numberOfProducts = Integer.parseInt(env.getProperty("products.number"));
    } catch (NumberFormatException nfe) {
      logger.error("config variable numberOfProducts could not be parsed, falling back to default");
      // If it's not a string, set it to be a default value
      numberOfProducts = DEFAULT_NUMBER_OF_PRODUCTS;
    }

    // Generate products
    List<Product> productList = productFactory.generateRandomProducts(numberOfProducts);

    //Sets active status to test savePurchase
    productList.get(0).setActive(true);
    productList.get(1).setActive(true);
    productList.get(2).setActive(false);
    productList.get(3).setActive(false);

    // Persist them to the database
    logger.info("Loading " + numberOfProducts + " products...");
    productRepository.saveAll(productList);
    logger.info("Data load completed. You can make requests now.");

    Purchase purchase1 = new Purchase();
    BillingAddress billingAddress = new BillingAddress();
    billingAddress.setEmail("bob@ross.com");
    purchase1.setBillingAddress(billingAddress);
    //CC should pass
    purchase1.setCreditCard(new CreditCard(
        "4234567812345678",
        "123",
        "01/25",
        "Bob Ross"
    ));
    purchaseRepository.save(purchase1);

    Purchase purchase2 = new Purchase();
    purchase2.setBillingAddress(billingAddress);
    //Should fail due to incorrect card type
    purchase2.setCreditCard(new CreditCard(
        "1234567812345678",
        "456",
        "02/24",
        "John Jones"
    ));
    purchaseRepository.save(purchase2);

    Purchase purchase3 = new Purchase();
    purchase3.setBillingAddress(billingAddress);
    //Should fail due to expiration date
    purchase3.setCreditCard(new CreditCard(
        "51234567812345678",
        "789",
        "01/20",
        "Frank Jenkins"
    ));
    purchaseRepository.save(purchase3);

    Purchase purchase4 = new Purchase();
    billingAddress.setEmail("blah");

    User user = new User("amir@amir.com", "Customer", "Amir", "Sharapov");
    userRepository.save(user);

    purchase4.setBillingAddress(billingAddress);

    purchaseRepository.save(purchase4);


    LineItem lineItems = new LineItem();
    lineItems.setProduct(productList.get(0));
    Review review1 = new Review();
    review1.setProducts(Collections.singletonList(lineItems));
    review1.setReviewRating(5.0);

    reviewRepository.save(review1);

    LineItem lineItem = new LineItem();
    lineItem.setProduct(productList.get(1));
    Review review2 = new Review();
    review2.setProducts(Collections.singletonList(lineItem));
    review2.setReviewRating(5.0);

    reviewRepository.save(review2);

  }
}
