package mx.ikii.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ComponentScan({ "mx.ikii", "mx.ikii.commons", "mx.ikii.products", "mx.ikii.web.commons" })
@EnableFeignClients({ "mx.ikii.commons.feignclient" })
@EnableMongoRepositories(basePackages = { "mx.ikii.products.repository", "mx.ikii.web.commons.repository" })
@EnableDiscoveryClient
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class IkiiBackProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IkiiBackProductsApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
