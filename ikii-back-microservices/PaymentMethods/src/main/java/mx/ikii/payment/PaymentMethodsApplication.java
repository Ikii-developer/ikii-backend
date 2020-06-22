package mx.ikii.payment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan({ "mx.ikii.payment" })
@SpringBootApplication
public class PaymentMethodsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PaymentMethodsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		ConektaExamples conekta = new ConektaExamples();
//		
//		conekta.createCustomer();
//		conekta.createAnOrderWithCardCharge();
//		conekta.createAnOrderWithAnOXXOPayCharge();
//		conekta.createAnOrderWithSPEIcharge();
//		conekta.handleErrors();
	}

}
