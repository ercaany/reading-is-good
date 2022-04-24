package com.getir.readingisgood.service.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderState {
  PENDING("PENDING"),
  COMPLETED("COMPLETED");

  private final String label;
}
