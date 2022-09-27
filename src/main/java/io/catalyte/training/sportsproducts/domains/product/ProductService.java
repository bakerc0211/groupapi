package io.catalyte.training.sportsproducts.domains.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

/**
 * This interface provides an abstraction layer for the Products Service
 */
public interface ProductService {

  List<Product> getProducts(Product product);

  Page<Product> getPaginatedProducts(Product product, Pageable page);

  Product saveProduct(Product productToSave);

  Product getProductById(Long id);

  List<String> getDistinctCategories();

  List<String> getDistinctTypes();

  List<Product> getProductsByFilter(HashMap<String, List<String>> filter);

}
