package io.eventuate.examples.tram.sagas.ordersandcustomers.products.webapi;

public class CreateProductRequest {
  private String name;
  private int stock;

  public CreateProductRequest() {
  }

  public CreateProductRequest(String name, int stock) {

    this.name = name;
    this.stock = stock;
  }


  public String getName() {
    return name;
  }

  public int getStock() {
    return stock;
  }
}
