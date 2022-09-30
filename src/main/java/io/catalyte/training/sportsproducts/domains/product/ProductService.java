package io.catalyte.training.sportsproducts.domains.product;

import java.util.HashMap;
import java.util.List;

/**
 * This interface provides an abstraction layer for the Products Service
 */
public interface ProductService {

  void deleteProductById(Long id);

  List<Product> getProducts(Product product);

  Product saveProduct(Product productToSave);

  Product getProductById(Long id);

  List<String> getDistinctCategories();

  List<String> getDistinctTypes();

  List<Product> getProductsByFilter(HashMap<String, List<String>> filter);

  Product changeProductActiveStatusById(Long id);
}
