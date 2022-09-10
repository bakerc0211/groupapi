package io.catalyte.training.sportsproducts.domains.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

  @PersistenceContext
  EntityManager entityManager;
  public Float min = 0.0f;
  public Float max = 0.0f;
  public Float maxP = 0.0f;
  public Float minP = 0.0f;

  /**
   * Construct the predicates and group of predicates to be used in the query
   *
   * @param filter read the values of the queries
   * @return a result list of products matching the criteria
   */
  @Override
  public List<Product> filterProduct(HashMap<String, List<String>> filter) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
    Root<Product> root = query.from(Product.class);
    List<Predicate> predicates = new ArrayList<>();
    List<Predicate> predicateGroups = new ArrayList<>();
    filter.forEach((field, value) -> {
      if (field.equals("minPrice") || (field.equals("maxPrice"))) {
        if (field.equals("minPrice")) {
          min = Float.valueOf(value.get(0));
          minP = min;
        } else {
          min = 0.0f;
          predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
              Float.valueOf(value.get(0)) + 0.001F));
        }
        if (field.equals("maxPrice")) {
          max = Float.valueOf(value.get(0));
          maxP = max;
        } else {
          max = 0.0f;
          predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
              Float.valueOf(value.get(0)) - 0.001F));
        }
        predicates.add(criteriaBuilder.between(root.get("price"), min, max));

        if (minP.compareTo(maxP) == 0) {
          predicates.add(criteriaBuilder.equal(root.get("price"), minP));
        }
      } else {
        value.forEach((item) -> {
          predicates.add(criteriaBuilder.equal(root.get(field), item));
        });
      }
      predicateGroups.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
      predicates.clear();
    });

    Predicate predicate = criteriaBuilder.and(predicateGroups.toArray((new Predicate[0])));
    query.select(root).where(predicate);
    return entityManager.createQuery(query).getResultList();
  }
}