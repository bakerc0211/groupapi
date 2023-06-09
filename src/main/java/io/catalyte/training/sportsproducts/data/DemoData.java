package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.product.ProductRepository;
import io.catalyte.training.sportsproducts.domains.product.ProductService;
import io.catalyte.training.sportsproducts.domains.purchase.BillingAddress;
import io.catalyte.training.sportsproducts.domains.purchase.CreditCard;
import io.catalyte.training.sportsproducts.domains.purchase.LineItem;
import io.catalyte.training.sportsproducts.domains.purchase.Purchase;
import io.catalyte.training.sportsproducts.domains.purchase.PurchaseRepository;
import io.catalyte.training.sportsproducts.domains.purchase.Review;
import io.catalyte.training.sportsproducts.domains.purchase.ReviewRepository;
import io.catalyte.training.sportsproducts.domains.user.User;
import io.catalyte.training.sportsproducts.domains.user.UserRepository;
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
    productList.get(4).setActive(true);
    productList.get(5).setActive(true);
    productList.get(6).setActive(true);

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

    User user = new User("amir@amir.com", "Customer", "Amir", "Sharapov", "null");
    userRepository.save(user);

    User user1 = new User("hmayer@catalyte.io", "Customer", "Harry", "Mayer", "null");
    userRepository.save(user1);

    User user2 = new User("mnolte@catalyte.io", "Customer", "Matthew", "Nolte", "null");
    userRepository.save(user2);

    User user3 = new User("chuddles@catalyte.io", "Customer", "Chase", "Huddles", "null");
    userRepository.save(user3);

    User user4 = new User("khelvey@catalyte.io", "Customer", "Kaleb", "Helvey", "null");
    userRepository.save(user4);

    User user5 = new User("nsiso@catalyte.io", "Customer", "Nushy", "Gonzalez Siso", "null");
    userRepository.save(user5);

    User user6 = new User("lvenegas@catalyte.io", "Customer", "Leslie", "Venegas", "null");
    userRepository.save(user6);

    User user7 = new User("cbaker@catalyte.io", "Customer", "Cameron", "Baker", "null");
    userRepository.save(user7);

    User user8 = new User("lowilliams@catalyte.io", "Customer", "Loretta", "Williams", "null");
    userRepository.save(user8);

    User user9 = new User("cburden@catalyte.io", "Customer", "Colton", "Burden", "null");
    userRepository.save(user9);

    User user10 = new User("aclay@catalyte.io", "Customer", "Alexander", "Clay", "null");
    userRepository.save(user10);

    User user11 = new User("scasavant@catalyte.io", "Customer", "Samuel", "Casavant", "null");
    userRepository.save(user11);

    User user12 = new User("kmacgregor@catalyte.io", "Customer", "Kyle", "MacGregor", "null");
    userRepository.save(user12);

    purchase4.setBillingAddress(billingAddress);

    purchaseRepository.save(purchase4);

    Purchase purchase5 = new Purchase();
    BillingAddress billingAddress5 = new BillingAddress();
    LineItem lineItemsPurchase = new LineItem();
    lineItemsPurchase.setProduct(productList.get(5));
    lineItemsPurchase.setQuantity(11);
    billingAddress5.setEmail("bob@ross.com");
    purchase5.setBillingAddress(billingAddress);
    purchase5.setCreditCard(new CreditCard(
        "4234567812345678",
        "123",
        "01/25",
        "Bob Ross"
    ));
    purchase5.setProducts(Collections.singletonList(lineItemsPurchase));
    purchaseRepository.save(purchase5);

    Purchase purchase6 = new Purchase();
    BillingAddress billingAddress6 = new BillingAddress();
    LineItem lineItemsPurchase6 = new LineItem();
    lineItemsPurchase6.setProduct(productList.get(6));
    lineItemsPurchase6.setQuantity(4);
    billingAddress6.setEmail("bob@ross.com");
    purchase6.setBillingAddress(billingAddress);
    purchase6.setCreditCard(new CreditCard(
        "4234567812345678",
        "123",
        "01/25",
        "Bob Ross"
    ));
    purchase6.setProducts(Collections.singletonList(lineItemsPurchase6));
    purchaseRepository.save(purchase6);

    LineItem lineItem5 = new LineItem();
    lineItem5.setProduct(productList.get(5));
    lineItem5.setQuantity(1);
    Review review5 = new Review();
    review5.setProducts(Collections.singletonList(lineItem5));
    review5.setReviewRating(4.3);

    reviewRepository.save(review5);

    LineItem lineItems = new LineItem();
    lineItems.setProduct(productList.get(3));
    lineItems.setQuantity(3);
    Review review1 = new Review();
    review1.setProducts(Collections.singletonList(lineItems));
    review1.setReviewRating(1.6);

    reviewRepository.save(review1);

    LineItem lineItem = new LineItem();
    lineItem.setProduct(productList.get(1));
    lineItem.setQuantity(5);
    Review review2 = new Review();
    review2.setProducts(Collections.singletonList(lineItem));
    review2.setReviewRating(5.0);

    reviewRepository.save(review2);

    LineItem lineItem3 = new LineItem();
    lineItem3.setProduct(productList.get(4));
    lineItem3.setQuantity(1);
    Review review3 = new Review();
    review3.setProducts(Collections.singletonList(lineItem3));
    review3.setReviewRating(3.2);

    reviewRepository.save(review3);

  }
}
