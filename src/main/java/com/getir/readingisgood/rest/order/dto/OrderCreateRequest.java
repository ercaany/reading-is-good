package com.getir.readingisgood.rest.order.dto;

import com.getir.readingisgood.service.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {
  @NotNull private String bookId;
  @NotNull private String customerId;

  @Min(1)
  private Long itemCount;

  @Positive private Double totalPrice;

  public Order toModel() {
    return Order.builder()
        .bookId(bookId)
        .customerId(customerId)
        .itemCount(itemCount)
        .totalPrice(totalPrice)
        .build();
  }
}
