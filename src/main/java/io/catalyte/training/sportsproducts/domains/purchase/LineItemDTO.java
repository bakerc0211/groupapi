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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LineItem GenerateLineItem() {
        LineItem newLineItem = new LineItem();

        Product newProduct = new Product();
        newProduct.setId(id);
        newLineItem.setProduct(newProduct);
        newLineItem.setQuantity(quantity);

        return newLineItem;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
