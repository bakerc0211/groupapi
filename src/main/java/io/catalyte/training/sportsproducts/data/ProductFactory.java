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

  private static final String[] colors = {
      "#000000", // white
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
  private static final String[] demographics = {
      "Men",
      "Women",
      "Kids",
      "Non-Binary"
  };
  private static final String[] categories = {
      "Golf",
      "Soccer",
      "Basketball",
      "Hockey",
      "Football",
      "Running",
      "Baseball",
      "Skateboarding",
      "Boxing",
      "Weightlifting"
  };
  private static final String[] adjectives = {
      "Lightweight",
      "Slim",
      "Shock Absorbing",
      "Exotic",
      "Elastic",
      "Fashionable",
      "Trendy",
      "Next Gen",
      "Colorful",
      "Comfortable",
      "Water Resistant",
      "Wicking",
      "Heavy Duty"
  };
  private static final String[] types = {
      "Pant",
      "Short",
      "Shoe",
      "Glove",
      "Jacket",
      "Tank Top",
      "Sock",
      "Sunglasses",
      "Hat",
      "Helmet",
      "Belt",
      "Visor",
      "Shin Guard",
      "Elbow Pad",
      "Headband",
      "Wristband",
      "Hoodie",
      "Flip Flop",
      "Pool Noodle"
  };

  private static final String [] brands = {
      "Nike",
      "Adidas",
      "Reebok",
      "New Balance",
      "Under Armour",
      "NOBULL",
      "Champion",
      "Colombia",
      "Fabletics",
      "Puma"
  };

  private static final String [] materials = {
      "Cotton",
      "Calico",
      "Spandex",
      "Polyester",
      "Microfiber",
      "Synthetic",
      "Bamboo Fiber",
      "Nylon",
      "Gore-Tex",
      "Rubber"
  };

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
   * @return - a category string
   */
  public static String getCategory() {
    Random randomGenerator = new Random();
    return categories[randomGenerator.nextInt(categories.length)];
  }

  /**
   * Returns a random type from the list of types
   * @return - a type string
   */
  public static String getType() {
    Random randomGenerator = new Random();
    return types[randomGenerator.nextInt(types.length)];
  }

  /**
   * Returns a random adjective from the list of adjectives
   * @return - an adjective string
   */
  public static String getAdjective() {
    Random randomGenerator = new Random();
    return adjectives[randomGenerator.nextInt(adjectives.length)];
  }

  /**
   * Returns a random color for primary color from the list of colors
   * @return - a color string
   */
   public static String getPrimaryColorCode() {
    Random randomGenerator = new Random();
    return colors[randomGenerator.nextInt(colors.length)];
   }

  /**
   * Returns a random color that is not the same as the primary color from colors
   * @return - a color string
   */
  public static String getSecondaryColorCode() {
    Random randomGenerator = new Random();
    String color = colors[randomGenerator.nextInt(colors.length)];
    String primaryColor = ProductFactory.getPrimaryColorCode();
    if (color.equals(primaryColor)){
      color = colors[randomGenerator.nextInt(colors.length)];
      return color;
    }
    return color;
  }

  /**
   * Returns a random boolean for product active status
   * @return - a boolean
   */
  public static Boolean getActive(){
    Random randomGenerator = new Random();
    return randomGenerator.nextBoolean();
  }

  /**
   * Returns a random brand from the list of brands
   * @return - a brand string
   */
  public static String getBrand() {
    Random randomGenerator = new Random();
    return brands[randomGenerator.nextInt(brands.length)];
  }

  /**
   * Returns a random material from the list of materials
   * @return - a material string
   */
  public static String getMaterial() {
    Random randomGenerator = new Random();
    return materials[randomGenerator.nextInt(materials.length)];
  }

  private static int getQuantity() {
    Random randomGenerator = new Random();
    return randomGenerator.nextInt(300);
  }

  private static float getPrice() {
    float min = (float) 0.01;
    float max = 500.00F;
    float range = max - min + 1;
    float price = (float) (Math.random() * range) + min;
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
    long randomDay = ThreadLocalRandom
        .current()
        .nextLong(startEpochDay, endEpochDay);

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
    String description = category + " " +  demographic + " " + adjective;

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
    product.setReleaseDate(String.valueOf(ProductFactory.between(LocalDate.now(), LocalDate.MAX)));
    product.setBrand(ProductFactory.getBrand());
    product.setImageSrc("https://via.placeholder.com/640x360");
    product.setMaterial(ProductFactory.getMaterial());
    product.setPrice(ProductFactory.getPrice());
    product.setQuantity(ProductFactory.getQuantity());


    return product;
  }

}
