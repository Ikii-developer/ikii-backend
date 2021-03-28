package mx.ikii.security.service;

import mx.ikii.commons.persistence.collection.Customer;

public interface IUsuarioService {
	
	public Customer findByUsername(String username);

}
