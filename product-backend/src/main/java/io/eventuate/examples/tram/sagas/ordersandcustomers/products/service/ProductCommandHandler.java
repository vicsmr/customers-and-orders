package io.eventuate.examples.tram.sagas.ordersandcustomers.products.service;

import io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.commands.ReserveStockCommand;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.replies.ProductNotFound;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.replies.ProductStockLimitExceeded;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.api.replies.ProductStockReserved;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductStockLimitExceededException;
import io.eventuate.examples.tram.sagas.ordersandcustomers.products.domain.ProductNotFoundException;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class ProductCommandHandler {

  private ProductService productService;

  public ProductCommandHandler(ProductService productService) {
    this.productService = productService;
  }

  public CommandHandlers commandHandlerDefinitions() {
    return SagaCommandHandlersBuilder
            .fromChannel("productService")
            .onMessage(ReserveStockCommand.class, this::reserveStock)
            .build();
  }

  public Message reserveStock(CommandMessage<ReserveStockCommand> cm) {
    ReserveStockCommand cmd = cm.getCommand();
    System.out.println(cmd.getProductId());
    try {
      System.out.println("TRYYYYY");
      productService.reserveStock(cmd.getProductId(), cmd.getOrderId(), cmd.getProductAmount());
      return withSuccess(new ProductStockReserved());
    } catch (ProductNotFoundException e) {
      return withFailure(new ProductNotFound());
    } catch (ProductStockLimitExceededException e) {
      return withFailure(new ProductStockLimitExceeded());
    }
  }

}
