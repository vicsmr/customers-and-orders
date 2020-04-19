package io.eventuate.examples.tram.sagas.ordersandcustomers.products.webapi;

public class GetProductStockResponse {
  private int stock;

  public GetProductStockResponse() {
  }

  public GetProductStockResponse(int stock) {
    this.stock = stock;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}
