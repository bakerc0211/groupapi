package io.catalyte.training.sportsproducts.domains.purchase;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  @Query(value = "SELECT * FROM PURCHASE WHERE email = :emailAddress ", nativeQuery = true)
  List<Purchase> findByBillingAddressEmail(@Param("emailAddress") String emailAddress);

}
