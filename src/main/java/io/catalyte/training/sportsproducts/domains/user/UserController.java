package io.catalyte.training.sportsproducts.domains.user;

import static io.catalyte.training.sportsproducts.constants.Paths.USERS_PATH;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for the user entity
 */
@RestController
@RequestMapping(value = USERS_PATH)
public class UserController {

  Logger logger = LogManager.getLogger(UserController.class);

  @Autowired
  private final UserServiceImpl userService;

  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  // METHODS

  /**
   * Controller method for logging the user in
   *
   * @param user        User to login
   * @param bearerToken String value in the Authorization property of the header
   * @return User
   */
  @PostMapping()
  public ResponseEntity<User> createUser(
      @RequestBody User user,
      @RequestHeader("Authorization") String bearerToken
  ) {
    logger.info("Request received for createUser");
    return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
  }

  /**
   * Controller method for updating the user
   *
   * @param id          Id of the user to update
   * @param user        User to update
   * @param bearerToken String value in the Authorization property of the header
   * @return User - Updated user
   */
  @PutMapping(path = "/{id}")
  public ResponseEntity<User> updateUser(
      @PathVariable Long id,
      @RequestBody User user,
      @RequestHeader("Authorization") String bearerToken
  ) {
    logger.info("Request received for updateUser");
    return new ResponseEntity<>(userService.updateUser(bearerToken, id, user), HttpStatus.OK);
  }

  /**
   * Controller method for updating the user
   *
   * @param email       Email of the user to update
   * @param user        User to update
   * @param bearerToken String value in the Authorization property of the header
   * @return User - Updated user
   */
  @PutMapping(path = "/email" + "/{email}")
  public ResponseEntity<User> updateUserByEmail(
      @PathVariable String email,
      @RequestBody User user,
      @RequestHeader("Authorization") String bearerToken
  ) {
    logger.info("Request received for updateUser");
    return new ResponseEntity<>(userService.updateUserByEmail(bearerToken, email, user),
        HttpStatus.OK);
  }

  /**
   * Controller method for getting a user by email
   *
   * @param email Email to get user by
   * @return User found in database
   */
  @GetMapping(path = "/{email}")
  public ResponseEntity<User> getUserByEmail(
      @PathVariable String email
  ) {
    logger.info("Request received for getUserByEmail");
    return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
  }

}
