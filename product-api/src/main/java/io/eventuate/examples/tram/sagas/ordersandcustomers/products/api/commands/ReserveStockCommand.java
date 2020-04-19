package io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.commands;

import io.eventuate.tram.commands.common.Command;

public class ReserveStockCommand implements Command {
  private Long orderId;
  private int productAmount;
  private long productId;

  public ReserveStockCommand() {
  }

  public ReserveStockCommand(Long productId, Long orderId, int productAmount) {
    this.productId = productId;
    this.orderId = orderId;
    this.productAmount = productAmount;
  }

  public int getProductAmount() {
    return productAmount;
  }

  public void setProductAmount(int productAmount) {
    this.productAmount = productAmount;
  }

  public Long getOrderId() {

    return orderId;
  }

  public void setOrderId(Long orderId) {

    this.orderId = orderId;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }
}
