package io.eventuate.examples.tram.sagas.ordersandcustomers.products.service;

import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.Product;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductStockLimitExceededException;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductNotFoundException;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductRepository;

public class ProductService {

  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product createProduct(String name, int productStock) {
    Product product  = new Product(name, productStock);
    return productRepository.save(product);
  }

  public void reserveStock(long productId, long orderId, int productAmount) throws ProductStockLimitExceededException {
    Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    product.reserveStock(orderId, productAmount);
  }
}
