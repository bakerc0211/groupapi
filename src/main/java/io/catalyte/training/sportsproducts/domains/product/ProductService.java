package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import org.springframework.util.MultiValueMap;

/**
 * This interface provides an abstraction layer for the Products Service
 */
public interface ProductService {

  List<Product> getProducts(Product product);

  Product getProductById(Long id);

  List<String> getDistinctCategories();

  List<String> getDistinctTypes();

  List<Product> filterProducts(MultiValueMap<String, String> queryParams);

}
