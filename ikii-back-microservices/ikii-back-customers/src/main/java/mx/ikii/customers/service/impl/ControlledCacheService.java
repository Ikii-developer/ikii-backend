package mx.ikii.customers.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import mx.ikii.commons.payload.response.customer.CustomerResponse;

@Service
public class ControlledCacheService {

    @Cacheable(cacheNames = "myControlledCache1", key =  "'myControlledPrefix_'.concat(#relevant.id)")
    public CustomerResponse getFromCache(CustomerResponse relevant) {
        return null;
    }

    @CachePut(cacheNames = "myControlledCache1", key =  "'myControlledPrefix_'.concat(#relevant.id)")
    public CustomerResponse populateCache(CustomerResponse relevant) {
        return relevant;
    }

    @CacheEvict(cacheNames = "myControlledCache1", key =  "'myControlledPrefix_'.concat(#relevant)")
    public void removeFromCache(String relevant) {
    }

}
