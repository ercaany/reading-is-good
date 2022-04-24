package com.getir.readingisgood.service.statistics;

import lombok.Builder;
import lombok.Data;

import java.time.Month;

@Data
@Builder
public class MonthlyOrderStatistics {
  private String customerId;
  private int month;
  private Long totalOrderCount;
  private Long totalBookCount;
  private Double totalPurchasedAmount;

  public String getMonthName() {
    return Month.of(month).name();
  }
}
