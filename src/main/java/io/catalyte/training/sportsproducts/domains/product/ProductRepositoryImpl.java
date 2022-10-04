package io.catalyte.training.sportsproducts.domains.product;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static java.util.stream.DoubleStream.of;

public class ProductRepositoryImpl implements ProductRepositoryCustom{

  @PersistenceContext
  EntityManager entityManager;
  public Float min = 0.0f;
  public Float max = 0.0f;
  public Float maxP = 0.0f;
  public Float minP = 0.0f;

  /**
   * Construct the predicates and group of predicates to be used in the query
   *
   * @param filter     read the values of the queries
   * @param pageable
   * @param pageNumber
   * @return a result list of products matching the criteria
   */
  @Override
  public PagedListHolder filterProduct(HashMap<String, List<String>> filter, Pageable pageable, int pageNumber) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
    Root<Product> root = query.from(Product.class);
    List<Predicate> predicates = new ArrayList<>();
    List<Predicate> predicateGroups = new ArrayList<>();
    filter.forEach((field, value) -> {
      if(field.equals("active")){
        predicates.add(criteriaBuilder.isTrue(root.get("active")));
      }
      if (field.equals("colorCode")) {
        In<List<String>> inClausePrimary = criteriaBuilder.in(root.get("primaryColorCode"));
        inClausePrimary.value(value);
        In<List<String>> inClauseSecondary = criteriaBuilder.in(root.get("secondaryColorCode"));
        inClauseSecondary.value(value);
        Predicate colorPredicate = criteriaBuilder.or(inClausePrimary, inClauseSecondary);
        predicates.add(colorPredicate);
      }
      if (field.equals("minPrice") || (field.equals("maxPrice"))) {
        if (field.equals("minPrice")) {
          min = Float.valueOf(value.get(0));
          minP = min;
        } else {
          min = 0.0f;
          predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),
              Float.parseFloat(value.get(0)) + 0.001F));
        }
        if (field.equals("maxPrice")) {
          max = Float.valueOf(value.get(0));
          maxP = max;
        } else {
          max = 0.0f;
          predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
              Float.parseFloat(value.get(0)) - 0.001F));
        }
        predicates.add(criteriaBuilder.between(root.get("price"), min, max));

        if (minP.compareTo(maxP) == 0) {
          predicates.add(criteriaBuilder.equal(root.get("price"), minP));
        }
      } else {
        value.forEach((item) -> {
          if (field != "colorCode") {
            predicates.add(criteriaBuilder.equal(root.get(field), item));
          }
        });
      }
      predicateGroups.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
      predicates.clear();
    });

    Predicate predicate = criteriaBuilder.and(predicateGroups.toArray((new Predicate[0])));
    query.select(root).where(predicate);

    List<Product> list = entityManager.createQuery(query).getResultList();
    PagedListHolder page = new PagedListHolder(list);
    page.setPageSize(20); // number of items per page
    page.setPage(pageNumber);
    return page;
  }
}