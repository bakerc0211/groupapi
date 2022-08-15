package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
@Query(value = "select category from product where " + "category LIKE %?1% or product.category like %?1%")
List<Product> findByKeyword(String keyword);
}
