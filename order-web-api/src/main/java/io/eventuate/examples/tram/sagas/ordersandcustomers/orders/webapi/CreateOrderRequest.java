package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.webapi;

import io.eventuate.examples.tram.sagas.ordersandcustomers.commondomain.Money;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.common.ProductDetails;

public class CreateOrderRequest {
  private Money orderTotal;
  private Long customerId;
  private ProductDetails productDetails;

  public CreateOrderRequest() {
  }

  public CreateOrderRequest(Long customerId, Money orderTotal, ProductDetails productDetails) {
    this.customerId = customerId;
    this.orderTotal = orderTotal;
    this.productDetails = productDetails;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }

  public ProductDetails getProductDetails() {
    return productDetails;
  }

  public Long getCustomerId() {
    return customerId;
  }
}
