package mx.ikii.customers.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.payload.response.customer.CustomerDetailsResponse;

public interface ICustomerDetailsServiceWrapper {

	CustomerDetailsResponse getById(String id);

	CustomerDetailsResponse getByCustomerId(String customerId);

	Page<CustomerDetailsResponse> getAll(Pageable pageable);

	CustomerDetailsResponse create(CustomerDetailsRequest customerDetailsRequest);

	CustomerDetailsResponse update(CustomerDetailsRequest customerDetailsRequest, String id);

	void toggleFavoriteBusiness(String customerId, String favoriteBusinessId);

	void toggleFavoriteProduct(String customerId, String favoriteProductId);

	void delete(String id);

}
