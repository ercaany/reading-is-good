package com.getir.readingisgood.service.order;

import com.getir.readingisgood.data.document.OrderDocument;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Order {
  private String id;
  private String orderState;
  private String bookId;
  private String customerId;
  private Long itemCount;
  private Double totalPrice;
  private LocalDateTime createDate;
  private LocalDateTime completeDate;

  public OrderDocument toDocument() {
    return OrderDocument.builder()
        .id(id)
        .orderState(orderState)
        .bookId(bookId)
        .customerId(customerId)
        .itemCount(itemCount)
        .totalPrice(totalPrice)
        .createDate(createDate)
        .completeDate(completeDate)
        .build();
  }
}
