package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.domains.purchase.dto.LineItemDTO;
import io.catalyte.training.sportsproducts.domains.purchase.dto.ReviewDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<LineItem> products;
  private Double reviewRating;

  public Review() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<LineItem> getProducts() {
    return products;
  }

  public void setProducts(List<LineItem> products) {
    this.products = products;
  }

  public Double getReviewRating() {
    return reviewRating;
  }

  public void setReviewRating(Double reviewRating) {
    this.reviewRating = reviewRating;
  }

  /**
   * Generate a ReviewDTO object
   *
   * @return The ReviewDTO object
   */
  public ReviewDTO GenerateReviewDTO() {
    ReviewDTO newReviewDTO = new ReviewDTO();

    newReviewDTO.setId(id);
    newReviewDTO.setRating(this.reviewRating);

    List<LineItemDTO> productList = new ArrayList<>();
    products.forEach((product) -> productList.add(product.GenerateLineItemDTO()));
    newReviewDTO.setProducts(productList);

    return newReviewDTO;
  }

  @Override
  public String toString() {
    return "Review{" +
        "id=" + id +
        " rating=" + reviewRating +
        '}';
  }
}





