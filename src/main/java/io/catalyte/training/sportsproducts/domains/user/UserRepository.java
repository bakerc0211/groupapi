package io.catalyte.training.sportsproducts.domains.user;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;

/**
 * User repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  @Query(value = "SELECT * FROM users where email = :email", nativeQuery = true)
  User findEmailToUpdate(@Param(value = "email") String email);
}
