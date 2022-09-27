package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.product.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * This class provides tools for random generation of products.
 */
public class ProductFactory {

  private static final String[] colors = {"#000000", // white
      "#ffffff", // black
      "#39add1", // light blue
      "#3079ab", // dark blue
      "#c25975", // mauve
      "#e15258", // red
      "#f9845b", // orange
      "#838cc7", // lavender
      "#7d669e", // purple
      "#53bbb4", // aqua
      "#51b46d", // green
      "#e0ab18", // mustard
      "#637a91", // dark gray
      "#f092b0", // pink
      "#b7c0c7"  // light gray
  };
  private static final String[] demographics = {"Men", "Women", "Kids", "Non-Binary"};
  private static final String[] categories = {"Golf", "Soccer", "Basketball", "Hockey", "Football",
      "Running", "Baseball", "Skateboarding", "Boxing", "Weightlifting"};
  private static final String[] adjectives = {"Lightweight", "Slim", "Shock Absorbing", "Exotic",
      "Elastic", "Fashionable", "Trendy", "Next Gen", "Colorful", "Comfortable", "Water Resistant",
      "Wicking", "Heavy Duty"};
  private static final String[] types = {"Pant", "Short", "Shoe", "Glove", "Jacket", "Tank Top",
      "Sock", "Sunglasses", "Hat", "Helmet", "Belt", "Visor", "Shin Guard", "Elbow Pad", "Headband",
      "Wristband", "Hoodie", "Flip Flop", "Pool Noodle"};

  private static final String[] brands = {"Nike", "Adidas", "Reebok", "New Balance", "Under Armour",
      "NOBULL", "Champion", "Colombia", "Fabletics", "Puma"};

  private static final String[] materials = {"Cotton", "Calico", "Spandex", "Polyester",
      "Microfiber", "Synthetic", "Bamboo Fiber", "Nylon", "Gore-Tex", "Rubber"};

  /**
   * Returns a random demographic from the list of demographics.
   *
   * @return - a demographic string
   */
  public static String getDemographic() {
    Random randomGenerator = new Random();
    return demographics[randomGenerator.nextInt(demographics.length)];
  }

  /**
   * Returns random category from list of categories
   *
   * @return - a category string
   */
  public static String getCategory() {
    Random randomGenerator = new Random();
    return categories[randomGenerator.nextInt(categories.length)];
  }

  /**
   * Returns a random type from the list of types
   *
   * @return - a type string
   */
  public static String getType() {
    Random randomGenerator = new Random();
    return types[randomGenerator.nextInt(types.length)];
  }

  /**
   * Returns a random adjective from the list of adjectives
   *
   * @return - an adjective string
   */
  public static String getAdjective() {
    Random randomGenerator = new Random();
    return adjectives[randomGenerator.nextInt(adjectives.length)];
  }

  /**
   * Returns a random color for primary color from the list of colors
   *
   * @return - a color string
   */
  public static String getPrimaryColorCode() {
    Random randomGenerator = new Random();
    return colors[randomGenerator.nextInt(colors.length)];
  }

  /**
   * Returns a random color that is not the same as the primary color from colors
   *
   * @return - a color string
   */
  public static String getSecondaryColorCode() {
    Random randomGenerator = new Random();
    String color = colors[randomGenerator.nextInt(colors.length)];
    String primaryColor = ProductFactory.getPrimaryColorCode();
    if (color.equals(primaryColor)) {
      color = colors[randomGenerator.nextInt(colors.length)];
      return color;
    }
    return color;
  }

  /**
   * Returns a random boolean for product active status
   *
   * @return - a boolean
   */
  public static Boolean getActive() {
    Random randomGenerator = new Random();
    return randomGenerator.nextBoolean();
  }

  /**
   * Returns a random brand from the list of brands
   *
   * @return - a brand string
   */
  public static String getBrand() {
    Random randomGenerator = new Random();
    return brands[randomGenerator.nextInt(brands.length)];
  }

  /**
   * Returns a random material from the list of materials
   *
   * @return - a material string
   */
  public static String getMaterial() {
    Random randomGenerator = new Random();
    return materials[randomGenerator.nextInt(materials.length)];
  }

  /**
   * Returns a random number between 0-300
   *
   * @return - a quantity integer
   */
  private static Integer getQuantity() {
    Random randomGenerator = new Random();
    return randomGenerator.nextInt(300);
  }

  /**
   * Returns a random price between 0.01 and 500.00
   *
   * @return - a price float
   */
  private static Float getPrice() {
    float min = (float) 0.01;
    float max = 500.00F;
    float range = max - min;
    float price = (float) (Math.random() * range);
    return Float.parseFloat(String.format("%.2f", price));
  }

  /**
   * Generates a random product offering id.
   *
   * @return - a product offering id
   */
  public static String getRandomProductId() {
    return "gpc-" + RandomStringUtils.random(7, false, true);
  }

  /**
   * Generates a random style code.
   *
   * @return - a style code string
   */
  public static String getStyleCode() {
    return "sc" + RandomStringUtils.random(5, false, true);
  }

  /**
   * Finds a random date between two date bounds.
   *
   * @param startInclusive - the beginning bound
   * @param endExclusive   - the ending bound
   * @return - a random date as a LocalDate
   */
  private static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
    long startEpochDay = startInclusive.toEpochDay();
    long endEpochDay = endExclusive.toEpochDay();
    long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

    return LocalDate.ofEpochDay(randomDay);
  }


  /**
   * Generates a number of random products based on input.
   *
   * @param numberOfProducts - the number of random products to generate
   * @return - a list of random products
   */
  public List<Product> generateRandomProducts(Integer numberOfProducts) {

    List<Product> productList = new ArrayList<>();

    for (int i = 0; i < numberOfProducts; i++) {
      productList.add(createRandomProduct());
    }

    return productList;
  }

  /**
   * Uses random generators to build a product.
   *
   * @return - a randomly generated product
   */
  public Product createRandomProduct() {
    Product product = new Product();
    String demographic = ProductFactory.getDemographic();
    String category = ProductFactory.getCategory();
    String type = ProductFactory.getType();
    String adjective = ProductFactory.getAdjective();
    String name = adjective + " " + category + " " + type;
    String description = demographic + " " + adjective + " " + category;
    String typeDifferentiator = String.valueOf(ProductFactory.getQuantity());

    product.setQuantity(Integer.valueOf(typeDifferentiator));
    product.setName(name);
    product.setDescription(description);
    product.setCategory(category);
    product.setType(type);
    product.setDemographic(demographic);
    product.setGlobalProductCode(ProductFactory.getRandomProductId());
    product.setStyleNumber(ProductFactory.getStyleCode());
    product.setActive(ProductFactory.getActive());
    product.setPrimaryColorCode(ProductFactory.getPrimaryColorCode());
    product.setSecondaryColorCode(ProductFactory.getSecondaryColorCode());
    product.setReleaseDate(
        String.valueOf(ProductFactory.between(LocalDate.of(2015, 1, 1), LocalDate.now())));
    product.setBrand(ProductFactory.getBrand());
    if (product.getType() == "Pant" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc("https://cdn.pixabay.com/photo/2015/08/27/05/07/dad-909510_960_720.jpg");
    } else if (product.getType() == "Pant" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2020/11/09/07/39/family-5725782_960_720.jpg");
    } else if (product.getType() == "Short" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2015/11/24/19/56/family-1060678_960_720.jpg");
    } else if (product.getType() == "Short" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2016/07/14/17/15/runner-1517163_960_720.jpg");
    }else if (product.getType() == "Shoe" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2016/09/21/16/44/trainers-1685162_960_720.jpg");
    } else if (product.getType() == "Shoe" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2020/04/20/11/41/sneaker-5067977_960_720.jpg");
    } else if (product.getType() == "Glove" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2022/08/11/09/35/ocean-7379022_960_720.jpg");
    } else if (product.getType() == "Glove" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2017/05/11/18/24/hockey-2304987_960_720.jpg");
    } else if (product.getType() == "Jacket" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2016/11/29/12/17/action-1869428_960_720.jpg");
    } else if (product.getType() == "Jacket" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2018/04/09/10/36/winter-3303774_960_720.jpg");
    } else if (product.getType() == "Tank Top" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2021/10/19/11/27/yoga-6723304_960_720.jpg");
    } else if (product.getType() == "Tank Top" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2017/02/09/22/48/anyika-onuora-2053748_960_720.jpg");
    } else if (product.getType() == "Sock" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2021/12/15/18/20/soccer-6873174_960_720.jpg");
    } else if (product.getType() == "Sock" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2021/07/02/21/23/substitute-bench-6382593_960_720.jpg");
    } else if (product.getType() == "Sunglasses" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2017/09/01/21/53/sunglasses-2705642_960_720.jpg");
    } else if (product.getType() == "Sunglasses" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2017/05/27/14/03/sensolatino-2348551_960_720.jpg");
    } else if (product.getType() == "Hat" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2020/03/04/08/59/market-4900987_960_720.jpg");
    } else if (product.getType() == "Hat" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://images.unsplash.com/photo-1596455607563-ad6193f76b17?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
    } else if (product.getType() == "Helmet" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2014/12/04/22/30/hockey-557219_960_720.jpg");
    } else if (product.getType() == "Helmet" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2019/02/07/10/25/ice-hockey-3980855_960_720.jpg");
    } else if (product.getType() == "Belt" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2017/03/07/21/24/belts-2125250_960_720.jpg");
    } else if (product.getType() == "Belt" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2013/06/12/20/32/sport-history-138975_960_720.jpg");
    } else if (product.getType() == "Visor" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2016/08/06/18/51/softball-1574962_960_720.jpg");
    } else if (product.getType() == "Visor" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2019/12/06/10/47/ice-hockey-player-4677065_960_720.jpg");
    }else if (product.getType() == "Shin Guard" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2021/12/16/10/00/football-6874296_960_720.jpg");
    } else if (product.getType() == "Shin Guard" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2019/08/15/15/19/hockey-4408281_960_720.jpg");
    } else if (product.getType() == "Elbow Pad" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2015/12/13/20/53/skateboard-1091710_960_720.jpg");
    } else if (product.getType() == "Elbow Pad" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://img.freepik.com/premium-photo/cropped-close-up-woman-putting-wrist-guard-rollerblading-protective-gear-young-girl_130388-2867.jpg?w=996");
    } else if (product.getType() == "Headband" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://media.gettyimages.com/photos/crazy-badminton-woman-picture-id183250437?k=20&m=183250437&s=612x612&w=0&h=b0SdiRiMycJ4bc1aJOve0MEx6MidRm44KEBLfN0hKLw=");
    } else if (product.getType() == "Headband" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://media.gettyimages.com/photos/portrait-of-young-female-runner-picture-id682702552?k=20&m=682702552&s=612x612&w=0&h=6W59JNxbHCbh3bDqURuc2UIf1bfEftJcJkACFqX7n2I=");
    } else if (product.getType() == "Wristband" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2018/10/14/18/20/swing-stick-3747100_960_720.jpg");
    } else if (product.getType() == "Wristband" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2018/05/21/09/55/tennis-3417791_960_720.jpg");
    } else if (product.getType() == "Hoodie" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2017/08/06/02/23/people-2587807_960_720.jpg");
    } else if (product.getType() == "Hoodie" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2022/03/26/09/40/woman-7092612_960_720.jpg");
    } else if (product.getType() == "Flip Flop" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2017/04/30/22/05/flip-flop-2274146_960_720.jpg");
    } else if (product.getType() == "Flip Flop" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2018/08/18/14/26/feet-3614862_960_720.jpg");
    } else if (product.getType() == "Pool Noodle" && Integer.parseInt(typeDifferentiator) % 2 == 0) {
      product.setImageSrc(
          "https://media.gettyimages.com/photos/portrait-of-smiling-girl-with-pool-noodle-under-water-in-swimming-picture-id909596824?k=20&m=909596824&s=612x612&w=0&h=bzsypDtkcMtDxOFzVLvCp6tqDj5UVLgDOPaUz_q4ZBI=");
    } else if (product.getType() == "Pool Noodle" && Integer.parseInt(typeDifferentiator) % 2 == 1) {
      product.setImageSrc(
          "https://media.gettyimages.com/photos/portrait-of-a-group-of-young-children-using-pool-noodles-during-a-picture-id1341029173?k=20&m=1341029173&s=612x612&w=0&h=PCpw1mb00iHnnSPtiEv7ItDoJV8PG62_HP8mH5Olhkg=");
    } else {
      product.setImageSrc(
          "https://cdn.pixabay.com/photo/2017/08/24/22/18/sport-2678653_960_720.jpg");
    }
    product.setMaterial(ProductFactory.getMaterial());
    product.setPrice(ProductFactory.getPrice());
    product.setPrice(ProductFactory.getPrice());
    product.setQuantity(ProductFactory.getQuantity());

    return product;
  }

}
