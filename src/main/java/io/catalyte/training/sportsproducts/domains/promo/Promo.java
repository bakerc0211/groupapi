package io.catalyte.training.sportsproducts.domains.promo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Entity is a lightweight persistence domain object. Typically, an entity represents a table in a
// relational database, and each entity instance corresponds to a row in that table
@Entity
public class Promo {

  //specifying it's an id
  //Provides for the specification of generation strategies for the values of primary keys.
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;

  private String title;
  private String description;

  private String type; //either percent off or flat

  private String rate; //percent discount or dollar amount, rounded to the hundredth

  private String expirationDate; // in DD/MM/YY

  public Promo(String title, String description, String type, String rate, String expirationDate) {
    this.title = title;
    this.description = description;
    this.type = type;
    this.rate = rate;
    this.expirationDate = expirationDate;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public String getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  @Override
  public String toString() {
    return "Promo{" +
        "id=" + id +
        ", title=" + title + '\'' +
        ", description='" + description + '\'' +
        ", type='" + type + '\'' +
        ", rate='" + rate + '\'' +
        ", expirationDate='" + expirationDate + '\'' +
        '}';
  }
}
