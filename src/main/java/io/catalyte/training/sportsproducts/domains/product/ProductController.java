package io.catalyte.training.sportsproducts.domains.product;

import static io.catalyte.training.sportsproducts.constants.Paths.CATEGORIES_PATH;
import static io.catalyte.training.sportsproducts.constants.Paths.PRODUCTS_PATH;
import static io.catalyte.training.sportsproducts.constants.Paths.TYPES_PATH;

import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The ProductController exposes endpoints for product related actions.
 */
@RestController
@RequestMapping(value = PRODUCTS_PATH)
public class ProductController {

  Logger logger = LogManager.getLogger(ProductController.class);

  @Autowired
  private ProductService productService;

  @GetMapping("/filter")
  public ResponseEntity<List<Product>> filterProduct(
      @RequestParam(value = "brand", required = false) List<String> brand,
      @RequestParam(value = "category", required = false) List<String> category,
      @RequestParam(value = "demographic", required = false) List<String> demographic,
      @RequestParam(value = "price", required = false) List<String> price,
      @RequestParam(value = "minPrice", required = false) List<String> minPrice,
      @RequestParam(value = "maxPrice", required = false) List<String> maxPrice,
      @RequestParam(value = "primaryColorCode", required = false) List<String> primaryColorCode,
      @RequestParam(value = "material", required = false) List<String> material
  ) {
    HashMap<String, List<String>> query = new HashMap<>();
    query.put("brand", brand);
    query.put("category", category);
    query.put("demographic", demographic);
    query.put("price", price);
    query.put("minPrice", minPrice);
    query.put("maxPrice", maxPrice);
    query.put("primaryColorCode", primaryColorCode);
    query.put("material", material);
    while (query.values().remove(null))
      ;
    return new ResponseEntity<>(productService.getProductsByFilter(query), HttpStatus.OK);

  }

  @GetMapping
  public ResponseEntity<List<Product>> getProducts(Product product) {
    logger.info("Request received for getProducts");

    return new ResponseEntity<>(productService.getProducts(product), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    logger.info("Request received for getProductsById: " + id);

    return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
  }

  @GetMapping(value = CATEGORIES_PATH)
  public ResponseEntity<List<String>> getDistinctCategories() {
    logger.info("Request received for getDistinctCategories");

    return new ResponseEntity<>(productService.getDistinctCategories(), HttpStatus.OK);
  }

  @GetMapping(value = TYPES_PATH)
  public ResponseEntity<List<String>> getDistinctTypes() {
    logger.info("Request received for getDistinctTypes");

    return new ResponseEntity<>(productService.getDistinctTypes(), HttpStatus.OK);
  }

}
