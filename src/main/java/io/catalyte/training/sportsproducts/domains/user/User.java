package io.catalyte.training.sportsproducts.domains.user;

import javax.persistence.*;

/**
 * User entity in database
 */
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String email;
  String role;
  String firstName;
  String lastName;
  String lastActive;

  public User() {}

  public User(Long id, String email, String role, String firstName, String lastName, String lastActive) {
    this.id = id;
    this.email = email;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.lastActive = lastActive;
  }

  public User(String email, String role, String firstName, String lastName, String lastActive) {
    this.email = email;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.lastActive = lastActive;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastActive() {return lastActive;}

  public void setLastActive(String lastActive) {this.lastActive = lastActive;}

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", role='" + role + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", last active ='" + lastActive + '\'' +
        '}';
  }
}
