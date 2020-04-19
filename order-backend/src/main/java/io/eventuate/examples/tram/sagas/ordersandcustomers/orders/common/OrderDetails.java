package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.common;

import io.eventuate.examples.tram.sagas.ordersandcustomers.commondomain.Money;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class OrderDetails {

  private Long customerId;

  @Embedded
  private Money orderTotal;

  @Embedded
  private ProductDetails productDetails;

  public OrderDetails() {
  }

  public OrderDetails(Long customerId, Money orderTotal, ProductDetails productDetails) {
    this.customerId = customerId;
    this.orderTotal = orderTotal;
    this.productDetails = productDetails;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }

  public ProductDetails getProductDetails() {
    return productDetails;
  }
}
