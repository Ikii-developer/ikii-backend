package mx.ikii.payment.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.conekta.ConektaList;
import io.conekta.ConektaObject;
import io.conekta.Order;
import mx.ikii.commons.payload.dto.CustomerInfoDTO;
import mx.ikii.commons.payload.dto.LineItemsDTO;
import mx.ikii.commons.payload.dto.TaxLineDTO;
import mx.ikii.commons.payload.response.payment.conekta.OrderConektaResponse;
import mx.ikii.commons.persistence.collection.OrderDetail;
import mx.ikii.commons.persistence.collection.PaymentOrder;
import mx.ikii.commons.persistence.collection.util.ProductDetail;
import mx.ikii.commons.utils.BigDecimalHelper;
import mx.ikii.payment.payload.dto.ChargesDTO;
import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.util.constant.Constants;


// TODO: CHANGE to Mapstruct
public class OrderConektaMapper {

  @SuppressWarnings("unchecked")
  public static OrderConektaResponse orderConektaToOrderConektaResponse(Order order) {

    OrderConektaResponse response = new OrderConektaResponse();
    response.setId(order.id);
    response.setAmount(BigDecimal.valueOf(order.amount)
        .divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UNNECESSARY));
    response.setPayment_status(order.payment_status);
    response.setCurrency(order.currency);

    CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
    customerInfoDTO.setCustomer_id(order.customer_info.id);
    customerInfoDTO.setName(order.customer_info.name);
    customerInfoDTO.setEmail(order.customer_info.email);
    customerInfoDTO.setPhone(order.customer_info.phone);
    response.setCustomer_info(customerInfoDTO);

    List<LineItemsDTO> lineItemsDTO = new ArrayList<>();
    ConektaList lineItems = order.line_items;
    lineItems.forEach(item -> {
      ConektaObject itemJSON = (ConektaObject) item;
      LineItemsDTO itemResponse = new LineItemsDTO();
      itemResponse.setId(itemJSON.getId());
      itemResponse.setName(itemJSON.getVal("name").toString());
      BigDecimal unitPrice = new BigDecimal(itemJSON.getVal("unit_price").toString())
          .divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UNNECESSARY);
      itemResponse.setUnit_price(unitPrice);
      itemResponse.setQuantity((Integer) itemJSON.getVal("quantity"));
      lineItemsDTO.add(itemResponse);
    });

    ConektaList taxLineItems = order.tax_lines;
    List<TaxLineDTO> taxes = new ArrayList<>();
    taxLineItems.forEach(item -> {
      ConektaObject itemJSON = (ConektaObject) item;
      BigDecimal amount = new BigDecimal(itemJSON.getVal("amount").toString())
          .divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UNNECESSARY);
      String description = itemJSON.getVal("description").toString();
      TaxLineDTO taxLineDTO = new TaxLineDTO();
      taxLineDTO.setAmount(amount);
      taxLineDTO.setDescription(description);
      taxes.add(taxLineDTO);
    });
    response.setTax_lines(taxes);


    response.setLine_items(lineItemsDTO);
    response.setLine_itemsId(order.line_items.id);

    // ChargesDTO chargesDTO = new ChargesDTO();
    // response.setCharges(charges);
    response.setChargesId(order.charges.id);

    // response.setMetadata(order.metadata);

    return response;
  }


  public static OrderConektaRequest paymentOrderToOrderConektaRequest(PaymentOrder paymentOrder,
      OrderDetail orderDetail) {
    OrderConektaRequest orderConektaRequest = new OrderConektaRequest();
    orderConektaRequest.setCurrency(Constants.CURRENCY_CONEKTA_MXN);
    List<mx.ikii.payment.payload.dto.LineItemsDTO> items = getItems(orderDetail.getProducts());
    orderConektaRequest.setLine_items(items);

    mx.ikii.payment.payload.dto.CustomerInfoDTO customerInfo =
        new mx.ikii.payment.payload.dto.CustomerInfoDTO();
    customerInfo.setCustomer_id(paymentOrder.getCustomerIdConekta());
    orderConektaRequest.setCustomer_info(customerInfo);

    List<ChargesDTO> charges = getCharges(paymentOrder);
    orderConektaRequest.setCharges(charges);

    List<mx.ikii.payment.payload.dto.TaxLineDTO> taxLines = getTaxLines(paymentOrder.getTax());
    orderConektaRequest.setTax_lines(taxLines);
    return orderConektaRequest;
  }

  private static List<mx.ikii.payment.payload.dto.LineItemsDTO> getItems(
      List<ProductDetail> products) {
    return products.stream().map(p -> {
      mx.ikii.payment.payload.dto.LineItemsDTO lineItemsDTO =
          new mx.ikii.payment.payload.dto.LineItemsDTO();
      lineItemsDTO.setDescription(p.getProductDescription());
      lineItemsDTO.setName(p.getProductName());
      lineItemsDTO.setQuantity(p.getUnitAmmount().intValue());
      lineItemsDTO.setUnit_price(BigDecimalHelper.bigDecimalToCents(p.getProductPrice()));
      return lineItemsDTO;
    }).collect(Collectors.toList());
  }

  private static List<ChargesDTO> getCharges(PaymentOrder paymentOrder) {
    ChargesDTO chargesDTO = new ChargesDTO();
    chargesDTO.setPayment_method(paymentOrder.getPaymentMethod());
    chargesDTO.setAmount(BigDecimalHelper.bigDecimalToCents(paymentOrder.getTotal()));
    return Arrays.asList(chargesDTO);
  }

  private static List<mx.ikii.payment.payload.dto.TaxLineDTO> getTaxLines(BigDecimal tax) {
    mx.ikii.payment.payload.dto.TaxLineDTO taxLine = new mx.ikii.payment.payload.dto.TaxLineDTO();
    taxLine.setDescription(Constants.TAX_DESCRIPTION);
    taxLine.setAmount(BigDecimalHelper.bigDecimalToCents(tax));
    return Arrays.asList(taxLine);
  }

}
