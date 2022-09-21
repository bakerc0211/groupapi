package io.catalyte.training.sportsproducts.domains.purchase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.catalyte.training.sportsproducts.domains.product.Product;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class LineItemDTO {
    private Long id;

    private int quantity;
}
