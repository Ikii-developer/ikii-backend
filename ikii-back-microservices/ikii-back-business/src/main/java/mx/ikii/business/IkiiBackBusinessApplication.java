package mx.ikii.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ComponentScan({ "mx.ikii", "mx.ikii.commons.feignclient", "mx.ikii.web.commons" })
@EnableMongoRepositories(basePackages = { "mx.ikii.business.repository", "mx.ikii.web.commons.repository" })
@EnableFeignClients({ "mx.ikii.commons.feignclient" })
@EnableDiscoveryClient
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class IkiiBackBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(IkiiBackBusinessApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
