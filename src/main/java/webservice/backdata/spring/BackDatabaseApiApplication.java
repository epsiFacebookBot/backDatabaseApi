package webservice.backdata.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("webservice.backdata")
public class BackDatabaseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackDatabaseApiApplication.class, args);
	}
}
