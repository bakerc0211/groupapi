package io.catalyte.training.sportsproducts.exceptions;

public class UnprocessableEntity extends RuntimeException {
  public UnprocessableEntity() {
  }
  public UnprocessableEntity(String message) {

    super(message);
  }
}
