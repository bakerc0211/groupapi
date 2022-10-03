package io.catalyte.training.sportsproducts.domains.purchase.dto;

import io.catalyte.training.sportsproducts.domains.purchase.LineItem;
import io.catalyte.training.sportsproducts.domains.purchase.Review;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO representing a line item
 */
public class ReviewDTO {
  private Long id;

  private List<LineItemDTO> products;
  public List<LineItemDTO> getProducts() {
    return products;
  }

  public void setProducts(List<LineItemDTO> products) {
    this.products = products;
  }
  public Double rating;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  /**
   * Generate a LineItem object from DTO
   *
   * @return The LineItem object
   */
  public Review GenerateReview() {
    Review newReview = new Review();

    List<LineItem> productList = new ArrayList<LineItem>();
    products.forEach((product) -> productList.add(product.GenerateLineItem()));
    newReview.setProducts(productList);
    newReview.setReviewRating(rating);

    return newReview;
  }

  @Override
  public int hashCode() {
    int result = id.intValue();
    result = (int) (31 * rating);
    return result;
  }

  @Override
  public String toString() {
    return "LineItem{" +
        "id=" + id +
        "rating=" + rating +
        '}';
  }
}

