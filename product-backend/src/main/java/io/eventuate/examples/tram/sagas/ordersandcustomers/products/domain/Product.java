package io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.Map;

@Entity
@Table(name="Product")
@Access(AccessType.FIELD)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private int stock;

  @ElementCollection
  private Map<Long, Integer> stockReservations;

  int availableStock() {
    return this.stock - stockReservations.values().stream().reduce(0, Integer::sum);
  }

  public Product() {
  }

  public Product(String name, int stock) {
    this.name = name;
    this.stock = stock;
    this.stockReservations = Collections.emptyMap();
  }

  public Long getId() {
    return id;
  }

  public int getStock() {
    return availableStock();
  }

  public void reserveStock(Long orderId, int productAmount) {
    if (availableStock() >= productAmount) {
      stockReservations.put(orderId, productAmount);
    } else
      throw new ProductStockLimitExceededException();
  }
}
