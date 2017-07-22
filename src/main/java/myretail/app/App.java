package myretail.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configure SpringBootApp and mongo repository and scan for components
 */
@SpringBootApplication
@EnableMongoRepositories({ "myretail" })
@ComponentScan("myretail")
public class App {

	public static void main(String[] args) {
		System.getProperties().put("server.port", 8080);
		SpringApplication.run(App.class, args);
	}

}
