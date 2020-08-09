package mx.ikii.customers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.mapper.customer.ICustomerAdressMapper;
import mx.ikii.commons.persistence.collection.CustomerAdress;
import mx.ikii.customers.repository.ICustomerAdressRepository;
import mx.ikii.customers.repository.impl.ICustomerAdressRepositoryCustom;
import mx.ikii.customers.service.ICustomerAdressService;

@Service
public class CustomerAdressServiceImpl implements ICustomerAdressService {

	@Autowired
	private ICustomerAdressRepository customerAdressRepository;
	
	@Autowired
	private ICustomerAdressRepositoryCustom customerAdressRepositoryCustom;

	@Autowired
	private ICustomerAdressMapper customerAdressMapper;

	@Override
	public Page<CustomerAdress> getAll(Pageable pageable) {
		return customerAdressRepository.findAll(pageable);
	}

	@Override
	public CustomerAdress getById(String id) {
		return customerAdressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, CustomerAdress.class));
	}

	@Override
	public List<CustomerAdress> getByCustomerId(String customerId) {
		return customerAdressRepository.findByCustomerId(customerId);
	}

	@Override
	public CustomerAdress createCustomerAddress(CustomerAdress request) {
		return customerAdressRepository.insert(request);
	}

	@Override
	public CustomerAdress updateCustomerAddress(CustomerAdress request, String id) {
		CustomerAdress customerAdress = customerAdressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, CustomerAdress.class));
		customerAdressMapper.updateEntity(customerAdress, request);
		return customerAdressRepository.save(customerAdress);
	}

	@Override
	public void deleteCustomerAddress(String id) {
		customerAdressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, CustomerAdress.class));
		customerAdressRepository.deleteById(id);
	}

	@Override
	public List<GeoResult<CustomerAdress>> findByLocationNear(String latitude, String longitude, Double maxDistance) {
		Point location = new Point(Double.parseDouble(latitude), Double.parseDouble(longitude));
		Distance distance = new Distance(maxDistance, Metrics.KILOMETERS);
		
		GeoResults<CustomerAdress> geoResult = customerAdressRepository.findByLocationNear(location, distance);
		List<GeoResult<CustomerAdress>> listGeoResult = geoResult.getContent();
		listGeoResult.forEach(e -> {
			System.out.println(e.getContent().getDescription());
		});
		return listGeoResult;
	}
	
	@Override
	public List<CustomerAdress> nearByMe(Double latitude, Double longitude, Double maxDistance) {
		
		List<CustomerAdress> customeAddress = customerAdressRepositoryCustom.nearByMe(latitude, longitude, maxDistance);
		
		customeAddress.forEach(e->System.out.println(e.getDescription()));
		
		return customeAddress; 
	}

}
