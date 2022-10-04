package io.catalyte.training.sportsproducts.domains.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * User repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  @Query(value = "SELECT * FROM users where email = :email", nativeQuery = true)
  User findEmailToUpdate(@Param(value = "email") String email);
}
