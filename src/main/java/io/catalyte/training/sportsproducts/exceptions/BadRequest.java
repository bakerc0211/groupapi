package io.catalyte.training.sportsproducts.exceptions;

/**
 * A class representation of a BadRequest runtime exception which will trigger the correct response code
 * from the controller.
 */
public class BadRequest extends RuntimeException {
    public BadRequest() {
    }

    public BadRequest(String message) {
        super(message);
    }
}
