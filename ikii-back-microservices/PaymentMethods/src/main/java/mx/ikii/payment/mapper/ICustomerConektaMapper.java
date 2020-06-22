package mx.ikii.payment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import io.conekta.Customer;
import mx.ikii.payment.payload.request.CustomerConektaRequest;
import mx.ikii.payment.payload.response.CustomerConektaResponse;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ICustomerConektaMapper {

//	@Mappings({ 
//		@Mapping(source = "customerConektaRequest.name", target = "name"),
//		@Mapping(source = "customerConektaRequest.email", target = "email"),
//		@Mapping(source = "customerConektaRequest.phone", target = "phone"),
//		@Mapping(source = "", target = ""),
//		@Mapping(source = "", target = ""),
//		@Mapping(source = "", target = "")
//		})
	default Customer customerConektaRequestToCustomerConekta(CustomerConektaRequest customerConektaRequest) {
		return null;
	}

//	@Mappings({ 
//		@Mapping(source = "name", target = "name"),
//		@Mapping(source = "email", target = "email"),
//		@Mapping(source = "phone", target = "phone"),
//		@Mapping(source = "", target = ""),
//		@Mapping(source = "", target = ""),
//		@Mapping(source = "", target = "")
//	})
	default CustomerConektaResponse customerConektaToCustomerConektaResponse(Customer customer) {
		CustomerConektaResponse response = new CustomerConektaResponse();
		response.setId(customer.id);
		response.setName(customer.name);
		response.setEmail(customer.email);
		response.setPhone(customer.phone);
		return response;
	}

}
