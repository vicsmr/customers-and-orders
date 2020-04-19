package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.common;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class ProductDetails {
    private Long productId;
    private int productAmount;
  
    public ProductDetails() {
    }
  
    public ProductDetails(Long productId, int productAmount) {
      this.productId = productId;
      this.productAmount = productAmount;
    }
  
    public Long getProductId() {
      return productId;
    }
  
    public int getProductAmount() {
      return productAmount;
    }
}