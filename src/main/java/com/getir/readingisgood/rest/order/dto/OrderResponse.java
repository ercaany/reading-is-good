package com.getir.readingisgood.rest.order.dto;

import com.getir.readingisgood.service.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
  private String orderId;
  private String orderState;
  private String bookId;
  private String customerId;
  private Long itemCount;
  private Double totalPrice;
  private LocalDateTime createDate;
  private LocalDateTime completeDate;

  public static OrderResponse fromModel(Order order) {
    return OrderResponse.builder()
        .orderId(order.getId())
        .orderState(order.getOrderState())
        .bookId(order.getBookId())
        .customerId(order.getCustomerId())
        .itemCount(order.getItemCount())
        .totalPrice(order.getTotalPrice())
        .createDate(order.getCreateDate())
        .completeDate(order.getCompleteDate())
        .build();
  }
}
