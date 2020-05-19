package mx.ikii;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({ "mx.payclip", "mx.payclip.commons.feignclient" })
@EnableFeignClients({"mx.payclip.commons.feignclient"})
@EnableMongoRepositories(basePackages = { "mx.payclip.repository" })
@EnableDiscoveryClient
public class PayClipTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayClipTransactionsApplication.class, args);
	}
}
