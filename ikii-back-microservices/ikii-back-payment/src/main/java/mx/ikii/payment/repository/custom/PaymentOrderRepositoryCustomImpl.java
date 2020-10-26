package mx.ikii.payment.repository.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import mx.ikii.commons.payload.dto.PaymentOrderDTO;
import mx.ikii.commons.payload.request.order.OrderFilter;
import mx.ikii.commons.utils.Nullable;

@Repository
public class PaymentOrderRepositoryCustomImpl implements IPaymentOrderRepositoryCustom {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public Optional<List<PaymentOrderDTO>> filter(OrderFilter orderFilter) {

    List<AggregationOperation> aggregations = new ArrayList<>();
    Aggregation aggregation = null;
    MatchOperation matchStatus = null;
    MatchOperation matchCustomer = null;
    MatchOperation matchBusiness = null;
    LookupOperation lookupOrderDetail = null;
    ProjectionOperation projectionOperationRenameFields = null;

    lookupOrderDetail = LookupOperation.newLookup().from("OrderDetail").localField("orderDetailId")
        .foreignField("_id").as("orderDetailId");
    aggregations.add(lookupOrderDetail);

    projectionOperationRenameFields = Aggregation
        .project("id", "customerId", "customerIdConekta", "customerProviderId", "transactionId",
            "total", "suTotal", "providerComission", "internalComission", "tax", "amountRefunded",
            "status", "createdAt", "message", "paymentMethod", "reasonRefund", "orderType",
            "orderSubstatusDetail")
        .and(ArrayOperators.ArrayElemAt.arrayOf("$orderDetailId").elementAt(0)).as("detail");
    aggregations.add(projectionOperationRenameFields);

    if (!Nullable.isNullOrEmpty(orderFilter.getStatus())) {
      matchStatus = Aggregation.match(Criteria.where("status").is(orderFilter.getStatus()));
      aggregations.add(matchStatus);
    }

    if (!Nullable.isNullOrEmpty(orderFilter.getBusinessId())) {
      matchBusiness = Aggregation
          .match(Criteria.where("detail.businessId").is(new ObjectId(orderFilter.getBusinessId())));
      aggregations.add(matchBusiness);
    }

    if (!Nullable.isNullOrEmpty(orderFilter.getCustomerId())) {
      matchCustomer = Aggregation
          .match(Criteria.where("customerId").is(new ObjectId(orderFilter.getCustomerId())));
      aggregations.add(matchCustomer);
    }

    aggregation = Aggregation.newAggregation(aggregations);

    AggregationResults<PaymentOrderDTO> aggResponse =
        mongoTemplate.aggregate(aggregation, "PaymentOrder", PaymentOrderDTO.class);

    return Optional.of(aggResponse.getMappedResults());
  }

}
