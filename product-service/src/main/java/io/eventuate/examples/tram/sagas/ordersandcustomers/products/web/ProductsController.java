package io.eventuate.examples.tram.sagas.ordersandcustomers.products.web;

import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.Product;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductRepository;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.service.ProductService;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.webapi.CreateProductRequest;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.webapi.CreateProductResponse;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.webapi.GetProductStockResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsController {

  private ProductService productService;
  private ProductRepository productRepository;

  @Autowired
  public ProductsController(ProductService productService, ProductRepository productRepository) {
    this.productService = productService;
    this.productRepository = productRepository;
  }

  @RequestMapping(value = "/products", method = RequestMethod.POST)
  public CreateProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
    Product product = productService.createProduct(createProductRequest.getName(), createProductRequest.getStock());
    return new CreateProductResponse(product.getId());
  }

  @RequestMapping(value="/products/{productId}/stock", method= RequestMethod.GET)
  public ResponseEntity<GetProductStockResponse> getProductStock(@PathVariable Long productId) {
    return productRepository
            .findById(productId)
            .map(o -> new ResponseEntity<>(new GetProductStockResponse(o.getStock()), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
