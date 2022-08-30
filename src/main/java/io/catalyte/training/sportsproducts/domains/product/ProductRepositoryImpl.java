package io.catalyte.training.sportsproducts.domains.product;

import java.util.ArrayList;
import java.util.Arrays;
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
  @Override
  public List<Product> filterProduct(HashMap<String, List<String>> filter) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Product> query= criteriaBuilder.createQuery(Product.class);
    Root<Product> root = query.from(Product.class);
    List<Predicate> predicates = new ArrayList<>();
    List<Predicate> predicateGroups = new ArrayList<>();
    filter.forEach((field,value) ->
    {
      if (field.equals("maxPrice")){
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), value.get(0)));
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