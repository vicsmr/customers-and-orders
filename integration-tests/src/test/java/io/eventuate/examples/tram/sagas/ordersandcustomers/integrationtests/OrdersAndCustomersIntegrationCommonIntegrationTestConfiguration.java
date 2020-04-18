package io.eventuate.examples.tram.sagas.ordersandcustomers.integrationtests;

import io.eventuate.examples.tram.sagas.ordersandcustomers.customers.CustomerConfiguration;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.OrderConfiguration;
import io.eventuate.tram.messaging.common.ChannelMapping;
import io.eventuate.tram.messaging.common.DefaultChannelMapping;
import io.eventuate.tram.sagas.spring.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.spring.commands.producer.TramCommandProducerConfiguration;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@Import({
        OrderConfiguration.class,
        CustomerConfiguration.class,
        TramEventsPublisherConfiguration.class,
        TramCommandProducerConfiguration.class,
        SagaOrchestratorConfiguration.class

})
public class OrdersAndCustomersIntegrationCommonIntegrationTestConfiguration {

  @Bean
  public ChannelMapping channelMapping(TramCommandsAndEventsIntegrationData data) {
    return DefaultChannelMapping.builder()
            .with("CustomerAggregate", data.getAggregateDestination())
            .with("customerService", data.getCommandChannel())
            .build();
  }


  @Bean
  public TramCommandsAndEventsIntegrationData tramCommandsAndEventsIntegrationData() {
    return new TramCommandsAndEventsIntegrationData();
  }



}
