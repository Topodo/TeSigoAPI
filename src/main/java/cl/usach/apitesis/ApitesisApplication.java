package cl.usach.apitesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"cl.usach.apitesis", "cl.usach.apitesis.rest"})
@EntityScan("cl.usach.apitesis.entities")
@EnableJpaRepositories("cl.usach.apitesis.repository")
public class ApitesisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApitesisApplication.class, args);
	}
}
