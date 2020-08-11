package mx.ikii.business.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ikii.business.repository.IBusinessRateRepository;
import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.persistence.collection.BusinessRate;
import mx.ikii.commons.persistence.collection.Rate;

@Service
public class BusinessRateServiceImpl implements IBusinessRateService {

	@Autowired
	private IBusinessRateRepository businessRateRepository;

	@Override
	public BusinessRate findByBusinessId(String businessId) {
		return businessRateRepository.findByBusinessId(businessId)
				.orElseThrow(() -> new ResourceNotFoundException(businessId, BusinessRate.class));
	}

	@Override
	public BusinessRate create(BusinessRate businessRate) {
		List<Rate> rates =  businessRate.getRates().stream().map(r ->{
			r.setId(UUID.randomUUID().toString());
			r.setCreatedAt(LocalDateTime.now());
			return r;
		}).collect(Collectors.toList());
		businessRate.setRates(rates);
		businessRate.setAverage(getAverage(businessRate.getRates()));
		businessRate.setUpdatedAt(LocalDateTime.now());
		return businessRateRepository.insert(businessRate);
	}

	@Override
	public BusinessRate update(BusinessRate businessRate) {
		BusinessRate businessRatePersisted = businessRateRepository.findById(businessRate.getId())
				.orElseThrow(() -> new ResourceNotFoundException(businessRate.getId(), BusinessRate.class));
		businessRatePersisted.setAverage(getAverage(businessRate.getRates()));
		businessRatePersisted.setUpdatedAt(LocalDateTime.now());
		businessRatePersisted.setRates(businessRate.getRates());
		return businessRateRepository.save(businessRatePersisted);
	}

	@Override
	public void delete(String id) {
		BusinessRate buisnessRate = businessRateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, BusinessRate.class));
		businessRateRepository.delete(buisnessRate);
	}

	@Override
	public BusinessRate updateRate(Rate rate, String businessId) {
		BusinessRate businessRatePersisted = this.findByBusinessId(businessId);
		int index = getIndexByIdRate(rate.getId(), businessRatePersisted.getRates());
		businessRatePersisted.getRates().set(index, rate);
		businessRatePersisted.setUpdatedAt(LocalDateTime.now());
		businessRatePersisted.setAverage(getAverage(businessRatePersisted.getRates()));
		return businessRateRepository.save(businessRatePersisted);
	}

	@Override
	public BusinessRate createRate(Rate rate, String businessId) {
		BusinessRate businessRatePersisted = this.findByBusinessId(businessId);
		rate.setId(UUID.randomUUID().toString());
		businessRatePersisted.getRates().add(rate);
		businessRatePersisted.setUpdatedAt(LocalDateTime.now());
		businessRatePersisted.setAverage(getAverage(businessRatePersisted.getRates()));
		return businessRateRepository.save(businessRatePersisted);
	}

	@Override
	public void deleteRate(String idRate, String businessId) {
		BusinessRate businessRatePersisted = this.findByBusinessId(businessId);
		int index = getIndexByIdRate(idRate, businessRatePersisted.getRates());
		businessRatePersisted.getRates().remove(index);
		businessRateRepository.save(businessRatePersisted);
	}

	private double getAverage(List<Rate> rates) {
		return rates.stream().mapToDouble(Rate::getStarsRate).average().orElse(0.0);
	}
	
	private int getIndexByIdRate(String idRate, List<Rate> rates) {
		AtomicInteger index = new AtomicInteger();
		Optional<Rate> rate = rates.stream().peek(x -> index.incrementAndGet()).filter(r -> r.getId().equals(idRate))
				.findFirst();
		if (rate.isPresent()) {
			return index.get();
		} else {
			throw new ResourceNotFoundException(idRate, Rate.class);
		}
	}

}
