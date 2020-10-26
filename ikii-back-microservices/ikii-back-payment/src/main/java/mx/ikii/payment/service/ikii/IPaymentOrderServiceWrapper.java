package mx.ikii.payment.service.ikii;

import java.util.List;
import mx.ikii.commons.payload.dto.PaymentOrderDTO;
import mx.ikii.commons.payload.request.order.OrderFilter;
import mx.ikii.commons.payload.request.order.OrderRequest;
import mx.ikii.commons.payload.request.order.OrderStatusRequest;
import mx.ikii.commons.payload.response.payment.order.PaymentOrderResponse;

public interface IPaymentOrderServiceWrapper {

    PaymentOrderResponse order(OrderRequest paymentOrder);

    PaymentOrderResponse getById(String id);

    PaymentOrderResponse refund(OrderRequest orderRequest);
    
    PaymentOrderResponse update(OrderRequest paymentOrder);
    
    void updateOrderIkiiStatus(String orderId, OrderStatusRequest orderStatusRequest);
    
    List<PaymentOrderDTO> filter(OrderFilter orderFilter);
    
}
