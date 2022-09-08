package io.catalyte.training.sportsproducts.domains.product;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is a representation of a sports apparel product.
 */
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String brand;

  private String description;

  private String demographic;

  private String category;

  private String type;

  private String releaseDate;

  private String primaryColorCode;

  private String secondaryColorCode;

  private String styleNumber;

  private String globalProductCode;

  private String imageSrc;

  private String material;

  private Float price;

  private Integer quantity;

  private Boolean active;

  public Product() {
  }

  public Product(String name, String brand, String description, String demographic, String category,
      String type, String releaseDate, String primaryColorCode, String secondaryColorCode,
      String styleNumber, String globalProductCode, String imageSrc, String material, Float price,
      Integer quantity, Boolean active) {
    this.name = name;
    this.brand = brand;
    this.description = description;
    this.demographic = demographic;
    this.category = category;
    this.type = type;
    this.releaseDate = releaseDate;
    this.primaryColorCode = primaryColorCode;
    this.secondaryColorCode = secondaryColorCode;
    this.styleNumber = styleNumber;
    this.globalProductCode = globalProductCode;
    this.imageSrc = imageSrc;
    this.material = material;
    this.price = price;
    this.quantity = quantity;
    this.active = active;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDemographic() {
    return demographic;
  }

  public void setDemographic(String demographic) {
    this.demographic = demographic;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getPrimaryColorCode() {
    return primaryColorCode;
  }

  public void setPrimaryColorCode(String primaryColorCode) {
    this.primaryColorCode = primaryColorCode;
  }

  public String getSecondaryColorCode() {
    return secondaryColorCode;
  }

  public void setSecondaryColorCode(String secondaryColorCode) {
    this.secondaryColorCode = secondaryColorCode;
  }

  public String getStyleNumber() {
    return styleNumber;
  }

  public void setStyleNumber(String styleNumber) {
    this.styleNumber = styleNumber;
  }

  public String getGlobalProductCode() {
    return globalProductCode;
  }

  public void setGlobalProductCode(String globalProductCode) {
    this.globalProductCode = globalProductCode;
  }

  public String getImageSrc() {
    return imageSrc;
  }

  public void setImageSrc(String imageSrc) {
    this.imageSrc = imageSrc;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Product product = (Product) o;

    if (!Objects.equals(name, product.name)) {
      return false;
    }
    if (!Objects.equals(brand, product.brand)) {
      return false;
    }
    if (!Objects.equals(description, product.description)) {
      return false;
    }
    if (!Objects.equals(demographic, product.demographic)) {
      return false;
    }
    if (!Objects.equals(category, product.category)) {
      return false;
    }
    if (!Objects.equals(type, product.type)) {
      return false;
    }
    if (!Objects.equals(releaseDate, product.releaseDate)) {
      return false;
    }
    if (!Objects.equals(primaryColorCode, product.primaryColorCode)) {
      return false;
    }
    if (!Objects.equals(secondaryColorCode, product.secondaryColorCode)) {
      return false;
    }
    if (!Objects.equals(styleNumber, product.styleNumber)) {
      return false;
    }
    if (!Objects.equals(globalProductCode, product.globalProductCode)) {
      return false;
    }
    if (!Objects.equals(imageSrc, product.imageSrc)) {
      return false;
    }
    if (!Objects.equals(material, product.material)) {
      return false;
    }
    if (!Objects.equals(price, product.price)) {
      return false;
    }
    if (!Objects.equals(quantity, product.quantity)) {
      return false;
    }
    return Objects.equals(active, product.active);
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (brand != null ? brand.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (demographic != null ? demographic.hashCode() : 0);
    result = 31 * result + (category != null ? category.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
    result = 31 * result + (primaryColorCode != null ? primaryColorCode.hashCode() : 0);
    result = 31 * result + (secondaryColorCode != null ? secondaryColorCode.hashCode() : 0);
    result = 31 * result + (styleNumber != null ? styleNumber.hashCode() : 0);
    result = 31 * result + (globalProductCode != null ? globalProductCode.hashCode() : 0);
    result = 31 * result + (imageSrc != null ? imageSrc.hashCode() : 0);
    result = 31 * result + (material != null ? material.hashCode() : 0);
    result = 31 * result + Float.floatToIntBits(price);
    result = 31 * result + quantity;
    result = 31 * result + (active != null ? active.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Product{" + "id=" + id + ", name='" + name + '\'' + ", brand='" + brand + '\''
        + ", description='" + description + '\'' + ", demographic='" + demographic + '\''
        + ", category='" + category + '\'' + ", type='" + type + '\'' + ", releaseDate='"
        + releaseDate + '\'' + ", primaryColorCode='" + primaryColorCode + '\''
        + ", secondaryColorCode='" + secondaryColorCode + '\'' + ", styleNumber='" + styleNumber
        + '\'' + ", globalProductCode='" + globalProductCode + '\'' + ", imageSrc='" + imageSrc
        + '\'' + ", material='" + material + '\'' + ", price='" + price + '\''
        + ", quantity='" + quantity + '\'' + ", active='" + active + '\'' + '}';
  }
}