package mx.ikii.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.feignclient.service.impl.IUserClipFeignService;
import mx.ikii.commons.mapper.transaction.ITransactionClipMapper;
import mx.ikii.commons.mapper.user.IUserClipMapper;
import mx.ikii.commons.payload.request.transaction.clip.crud.TransactionClipRequest;
import mx.ikii.commons.payload.response.transaction.TransactionClipReport;
import mx.ikii.commons.payload.response.transaction.TransactionClipResponse;
import mx.ikii.commons.payload.response.transaction.TransactionClipSummaryResponse;
import mx.ikii.commons.persistence.collection.TransactionClip;
import mx.ikii.commons.persistence.collection.UserClip;
import mx.ikii.commons.utils.DateHelper;
import mx.ikii.commons.utils.PageHelper;
import mx.ikii.service.ITransactionClipService;
import mx.ikii.service.ITransactionClipServiceWrapper;

/**
 * 
 * This class includes the logic to each one of the assessment points
 * 
 * @author Arturo Isaac Velázquez Vargas
 * @see <a href=
 *      "https://github.com/cesaralcancio/simple-test/blob/master/README.md">payclip_assessment</a>
 * 
 */
@Service
public class TransactionClipServiceWrapperImpl implements ITransactionClipServiceWrapper {

	/**
	 * This instance variable is used to convert transaction DTOs E.g The incoming
	 * transaction request is mapped to a transaction entity or the entity is mapped
	 * to an outcoming DTO response to the client. It uses mapstruct library.
	 */
	@Autowired
	private ITransactionClipMapper transactionClipMapper;

	/**
	 * This instance variable is used as the low-service layer in the business logic
	 * containing the basic CRUD operations
	 */
	@Autowired
	private ITransactionClipService transactionClipService;

	/**
	 * This instance variable is used as a rest client like RestTemplate, but
	 * FeingClient interacts internally with the microservices, and works as a
	 * declarative rest client.In this example it communicates with user
	 * microservice
	 */
	@Autowired
	private IUserClipFeignService userFeignService;

	/**
	 * This instance variable is used to map the user DTOs
	 */
	@Autowired
	private IUserClipMapper userClipMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransactionClipResponse add(TransactionClipRequest transaction, String userId) {
//
//		UserClip user = userFeignService.getById(userId);
//
//		TransactionClip transactionEntity = transactionClipMapper.requestToEntity(transaction);
//		transactionEntity.setUserId(user.getId());
//
//		TransactionClip transactionUser = transactionClipService.create(transactionEntity);
//
//		user.getTransactions().add(transactionUser);
//		userFeignService.update(userClipMapper.entityToRequest(user), userId);
//		return transactionClipMapper.entityToResponse(transactionUser);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransactionClipResponse findByTransactionIdByUserId(String transactionId, String userId) {

//		UserClip user = userFeignService.getById(userId);
//
//		// Validates if the transaction belongs to the underlying user
//		Optional<TransactionClip> transactionValidation = user.getTransactions().stream()
//				.filter(s -> s.getId().equals(transactionId)).findAny();
//
//		if (transactionValidation.isPresent()) {
//			return transactionClipMapper.entityToResponse(transactionValidation.get());
//		} else {
//			throw new ResourceNotFoundException(transactionId, TransactionClip.class);
//		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TransactionClipResponse> findAllByUserId(String userId) {
//		UserClip user = userFeignService.getById(userId);
//		List<TransactionClip> userTransactions = user.getTransactions().stream()
//				.sorted((s1, s2) -> s1.getDate().compareTo(s2.getDate())).collect(Collectors.toList());

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransactionClipSummaryResponse sumByUserId(String userId) {
//		UserClip user = userFeignService.getById(userId);
//		DoubleSummaryStatistics summaryStatistics = user.getTransactions().stream()
//				.mapToDouble(t -> t.getAmount().doubleValue()).summaryStatistics();
//
//		TransactionClipSummaryResponse summaryResponse = new TransactionClipSummaryResponse();
//		summaryResponse.setUserId(userId);
//		summaryResponse.setSum(summaryStatistics.getSum());

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TransactionClipReport> getReportByUserId(String userId) {
//		UserClip user = userFeignService.getById(userId);
//
//		// Sorts the transactions in natural
//		// order according to the transaction date in order to provide a better
//		// iteration order
//		List<TransactionClip> filteredTransactions = user.getTransactions().stream()
//				.sorted((s1, s2) -> s1.getDate().compareTo(s2.getDate())).collect(Collectors.toList());
//
//		// Creates a map to allocate the transactions grouped by a virtual frame week
//		// containing a startWeekDay and a endWeekDay
//		Map<FrameWeekDate, List<TransactionClip>> groupedByWeeks = new LinkedHashMap<>();
//
//		// Iteration of the transactions already sorted naturally by date
//		for (TransactionClip transactionClip : filteredTransactions) {
//
//			FrameWeekDate rangeWeekKey = new FrameWeekDate();
//
//			LocalDate transactionDay = transactionClip.getDate().toInstant().atZone(ZoneId.systemDefault())
//					.toLocalDate();
//			// When the transaction day turns out to be the last day of the month the start
//			// day of the frame starts the next month e.g 30/11/2019->01/12/2019
//			if (transactionDay.getDayOfMonth() == YearMonth.now().atEndOfMonth().getDayOfMonth()) {
//				transactionDay = transactionDay.plusDays(1);
//			}
//
//			// Calculates the start day of the week based on the assessment requirements
//			// (Friday)
//			rangeWeekKey.setStartWeek(DateHelper.getLocalDatefromFirstDayOfWeek(transactionDay));
//			// Calculates the end day of the week based on the assessment requirements
//			// (Thursday)
//			rangeWeekKey.setEndWeek(DateHelper.getLocalDatefromLastDayOfWeek(transactionDay));
//
//			List<TransactionClip> weekTransactions = null;
//			// When the frame key is not in the grouping map , it is inserted otherwise the
//			// transaction is added to the current date frame
//			if (!groupedByWeeks.containsKey(rangeWeekKey)) {
//				weekTransactions = new ArrayList<>();
//				weekTransactions.add(transactionClip);
//
//				groupedByWeeks.put(rangeWeekKey, weekTransactions);
//			} else {
//				weekTransactions = groupedByWeeks.get(rangeWeekKey);
//				weekTransactions.add(transactionClip);
//				groupedByWeeks.put(rangeWeekKey, weekTransactions);
//			}
//
//		}
//		ArrayDeque<Double> totalAmounts = new ArrayDeque<>();
//		totalAmounts.offer(0.0);
//		// Creating the User report response DTO with all the transactions already
//		// grouped by the date frame
//		List<TransactionClipReport> reportUserTransactions = groupedByWeeks.entrySet().stream().map(s -> {
//			TransactionClipReport userReport = new TransactionClipReport();
//
//			List<TransactionClip> transactionsUser = s.getValue();
//
//			int quantity = transactionsUser.size();
//			double sum = transactionsUser.stream().mapToDouble(t -> t.getAmount().doubleValue()).sum();
//
//			TransactionClip firstTransaction = transactionsUser.stream().findAny().get();
//
//			double totalAmount = totalAmounts.stream().mapToDouble(ta -> ta).sum() + sum;
//			totalAmounts.offer(totalAmount);
//
//			FrameWeekDate frameWeek = s.getKey();
//
//			userReport.setUserId(firstTransaction.getUserId());
//			userReport.setQuantity(quantity);
//			userReport.setAmount(sum);
//			userReport.setTotalAmount(totalAmounts.poll());
//			userReport.setWeekStartDate(
//					Date.from(frameWeek.getStartWeek().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
//			userReport.setWeekEndDate(
//					Date.from(frameWeek.getEndWeek().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
//
//			return userReport;
//
//		}).collect(Collectors.toList());

		return Collections.emptyList();
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public TransactionClipResponse findRandom() {

		List<TransactionClip> transactions = transactionClipService.findAll(PageRequest.of(0, Integer.MAX_VALUE))
				.getContent();

		if (transactions.isEmpty()) {
			throw new ResourceNotFoundException("Random transaction not found");
		}
		List<TransactionClip> transactionsTemp = new ArrayList<>(transactions);
		Collections.shuffle(transactionsTemp);

		Optional<TransactionClip> optionalTransaction = transactionsTemp.stream().findAny();

		if (optionalTransaction.isPresent()) {
			return transactionClipMapper.entityToResponse(optionalTransaction.get());
		}
		throw new ResourceNotFoundException("Random transaction not found");
	}

	/**
	 * This class helps to create a virtual week date frame used to group the
	 * transactions based on the startWeek and endWeek
	 */
	public static class FrameWeekDate {

		private LocalDate startWeek;
		private LocalDate endWeek;

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FrameWeekDate other = (FrameWeekDate) obj;
			if (endWeek == null) {
				if (other.endWeek != null)
					return false;
			} else if (!endWeek.equals(other.endWeek))
				return false;
			if (startWeek == null) {
				if (other.startWeek != null)
					return false;
			} else if (!startWeek.equals(other.startWeek))
				return false;
			return true;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((endWeek == null) ? 0 : endWeek.hashCode());
			result = prime * result + ((startWeek == null) ? 0 : startWeek.hashCode());
			return result;
		}

		public LocalDate getStartWeek() {
			return startWeek;
		}

		public void setStartWeek(LocalDate startWeek) {
			this.startWeek = startWeek;
		}

		public LocalDate getEndWeek() {
			return endWeek;
		}

		public void setEndWeek(LocalDate endWeek) {
			this.endWeek = endWeek;
		}

	}

	@Override
	public TransactionClipResponse findById(String id) {
		return transactionClipMapper.entityToResponse(transactionClipService.findById(id));
	}

	@Override
	public Page<TransactionClipResponse> findAll(Pageable pageable) {
		Page<TransactionClip> transactionsClip = transactionClipService.findAll(pageable);
		List<TransactionClipResponse> transactionsResponse = transactionClipMapper
				.entityToResponse(transactionsClip.getContent());

		return PageHelper.createPage(transactionsResponse, pageable, transactionsClip.getTotalElements());
	}

	@Override
	public TransactionClipResponse create(TransactionClipRequest transaction) {
		TransactionClip transactionEntity = transactionClipMapper.requestToEntity(transaction);
		return transactionClipMapper.entityToResponse(transactionClipService.create(transactionEntity));
	}

	@Override
	public TransactionClipResponse update(TransactionClipRequest transaction, String id) {
		TransactionClip transactionEntity = transactionClipMapper.requestToEntity(transaction);
		return transactionClipMapper.entityToResponse(transactionClipService.update(transactionEntity, id));
	}

	@Override
	public void delete(String id) {
		transactionClipService.delete(id);
	}

}
