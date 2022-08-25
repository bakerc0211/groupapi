package io.catalyte.training.sportsproducts.domains.product;

import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

/**
 * This class provides the implementation for the ProductService interface.
 */
@Service
public class ProductServiceImpl implements ProductService {

  private final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

  @PersistenceContext
  ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }


  public List<Product> filterProducts(HashMap<String, Object> conditions)
  {

    CriteriaBuilder cb = productRepository.getCriteriaBuilder();
    CriteriaQuery<Product> query= cb.createQuery(Product.class);
    Root<Product> root = query.from(Product.class);

    List<java.util.function.Predicate> predicates = new ArrayList<>();
    conditions.forEach((field,value) ->
    {
      switch (field)
      {
        case "brand":
          predicates.add((Predicate) cb.equal (root.get(field),"%"+(String)value+"%"));
          break;
        case "category":
          predicates.add((Predicate) cb.like(root.get(field),"%"+(String)value+"%"));
          break;
      }
    });

    query.select(root).where(predicates.toArray(new Predicate[predicates]));
    return EntityManager.createQuery(query).getResultList();
  }









//  @Override
//  public List<Product> filterProducts(MultiValueMap<String, String> query) {
//    List<String> brand = query.get("brand");
//    List<String> category = query.get("category");
//    List<String> demographic = query.get("demographic");
//    List<String> price = query.get("price");
//    List<String> primaryColorCode = query.get("primaryColorCode");
//    List<String> material = query.get("material");
//
//    return productRepository.filterProducts(brand, demographic, category, price, primaryColorCode, material);
//  }
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
