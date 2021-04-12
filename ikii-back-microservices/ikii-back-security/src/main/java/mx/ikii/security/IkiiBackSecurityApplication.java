package mx.ikii.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ******************************
 * * 	AUTHORIZATION SERVER	*
 * ******************************
 */
@SpringBootApplication
@ComponentScan({ "mx.ikii", "mx.ikii.commons", "mx.ikii.security" })
@EnableFeignClients({ "mx.ikii.commons.feignclient" })
@EnableDiscoveryClient
public class IkiiBackSecurityApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(IkiiBackSecurityApplication.class, args);
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		
		for (int i = 0; i < 4; i++) {
			String passwordBCrypt = passwordEncode.encode(password);
			System.out.println(passwordBCrypt);
		}
		
	}
	
}
