package io.catalyte.training.sportsproducts.domains.promo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//creating the repository that will act as your database through springboot
@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {

}
