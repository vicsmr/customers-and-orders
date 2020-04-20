package gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Value("#{systemEnvironment['ADVERTISED_HOST_NAME']}")
  	private String hostName;

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
					.route(p -> p
						.path("/orders/**")
						.uri("http://" + hostName + ":8081"))
					.route(p -> p
						.path("/customers")
						.uri("http://" + hostName + ":8082"))
					.route(p -> p
						.path("/products/**")
						.uri("http://" + hostName + ":8083"))
					.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
