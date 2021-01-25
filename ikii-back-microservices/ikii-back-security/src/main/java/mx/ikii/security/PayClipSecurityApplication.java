package mx.ikii.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan({ "mx.ikii", "mx.ikii.commons", "mx.ikii.security" })
@EnableFeignClients({ "mx.ikii.commons.feignclient" })
@EnableMongoRepositories(basePackages = { "mx.ikii.repository" })
@EnableDiscoveryClient
@RestController
@RequestMapping("/")
public class PayClipSecurityApplication {
	public static void main(String[] args) {
		SpringApplication.run(PayClipSecurityApplication.class, args);
	}
}
