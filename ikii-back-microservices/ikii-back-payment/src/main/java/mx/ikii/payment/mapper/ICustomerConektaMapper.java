package mx.ikii.payment.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import io.conekta.Customer;
import mx.ikii.payment.payload.response.CustomerConektaResponse;

//@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ICustomerConektaMapper {
	
	
	@Mappings({ 
		@Mapping(source = "customer.id", target = "id"),
		@Mapping(source = "customer.name", target = "name"),
		@Mapping(source = "customer.email", target = "email"),
		@Mapping(source = "customer.phone", target = "phone"),
		@Mapping(source = "customer.default_card_id", target = "default_card_id"),
		@Mapping(source = "customer.default_payment_source_id", target = "default_payment_source_id")
	})
	CustomerConektaResponse customerConektaToCustomerConekta(Customer customer);

}
