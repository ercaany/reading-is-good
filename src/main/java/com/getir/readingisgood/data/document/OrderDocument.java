package com.getir.readingisgood.data.document;

import com.getir.readingisgood.service.order.Order;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("order")
@Builder
@Data
public class OrderDocument {
  @Id private String id;
  private String orderState;
  private String bookId;
  private String customerId;
  private Long itemCount;
  private Double totalPrice;
  private LocalDateTime createDate;
  private LocalDateTime completeDate;

  public Order toModel() {
    return Order.builder()
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
