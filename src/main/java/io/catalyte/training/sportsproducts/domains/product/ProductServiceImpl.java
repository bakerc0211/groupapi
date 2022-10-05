package io.catalyte.training.sportsproducts.domains.product;

import io.catalyte.training.sportsproducts.domains.purchase.PurchaseController;
import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * This class provides the implementation for the ProductService interface.
 */
@Service
public class ProductServiceImpl implements ProductService {

  private final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

  ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Retrieves the group of products for the specified filter
   *
   * @param filter   calls the queries for the specified filter
   * @param pageable
   * @param pageNumber
   * @return the list of products matching the criteria
   */
  public PagedListHolder getProductsByFilter(HashMap<String, List<String>> filter, Pageable pageable, int pageNumber) {
    try {
      return productRepository.filterProduct(filter, pageable, pageNumber);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  @Override
  public Product changeProductActiveStatusById(Long id) {
    Product product;
    Boolean active;
    try {
      product = productRepository.findById(id).orElse(null);
    } catch (DataAccessException e) {

      throw new ServerError(e.getMessage());
    }
    if (product != null) {
      active = product.getActive();
      product.setActive(!active);
      return productRepository.save(product);
    } else {
      logger.info("Get by id failed, it does not exist in the database: " + id);
      throw new ResourceNotFound("Get by id failed, it does not exist in the database: " + id);
    }
  }

  /**
   * Deletes the product with the provided id from the database
   *
   * @param id the id of the product to be deleted
   */
  @Override
  public void deleteProductById(Long id) {
    Product product;

    try {
      product = productRepository.findById(id).orElse(null);
    } catch (DataAccessException e) {

      throw new ServerError(e.getMessage());
    }

    if (product != null) {
        productRepository.deleteProduct(product.getId());
    } else {
      logger.info("Delete by id failed, it does not exist in the database: " + id);
      throw new ResourceNotFound("Delete by id failed, it does not exist in the database: " + id);
    }
  }

  /**
   * Retrieves all products from the database, optionally making use of an example if it is passed.
   *
   * @param product - an example product to use for querying
   * @return - a list of products matching the example, or all products if no example was passed
   */
  public List<Product> getProducts(Product product) {
    try {
      return productRepository.findAll(Example.of(product));
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  /**
   * Retrieves all products from the database and paginates them
   *
   * @param product - an example product to use for querying
   * @param page - page for pagination
   * @return - an object of pagination data including products
   */
  public Page<Product> getPaginatedProducts(Product product, Pageable page) {
    try {
      return productRepository.findAll(Example.of(product), page);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  public Product saveProduct(Product newProduct) {
    return productRepository.save(newProduct);
  }

  /**
   * Retrieves the product with the provided id from the database.
   *
   * @param id - the id of the product to retrieve
   * @return - the product
   */
  public Product getProductById(Long id) {
    Product product;

    try {
      product = productRepository.findById(id).orElse(null);
    } catch (DataAccessException e) {

      throw new ServerError(e.getMessage());
    }

    if (product != null) {
      return product;
    } else {
      logger.info("Get by id failed, it does not exist in the database: " + id);
      throw new ResourceNotFound("Get by id failed, it does not exist in the database: " + id);
    }
  }

  /**
   * Retrieves the unique product categories from the database.
   *
   * @return - the product categories
   */
  public List<String> getDistinctCategories() {
    try {
      return productRepository.findDistinctCategories();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  /**
   * Retrieves the unique product types from the database.
   *
   * @return - the product types
   */
  public List<String> getDistinctTypes() {
    try {
      return productRepository.findDistinctTypes();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }
}
