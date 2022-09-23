package io.catalyte.training.sportsproducts.domains.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

/**
 * This interface provides an abstraction layer for the Products Service
 */
public interface ProductService {

  Page<Product> getProducts(Product product, Pageable page);

  Product saveProduct(Product productToSave);

  Product getProductById(Long id);

  List<String> getDistinctCategories();

  List<String> getDistinctTypes();

  List<Product> getProductsByFilter(HashMap<String, List<String>> filter);

}
