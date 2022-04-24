package com.getir.readingisgood.rest.statistics.dto;

import com.getir.readingisgood.service.statistics.MonthlyOrderStatistics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyOrderStatisticsResponse {
  private String month;
  private Long totalOrderCount;
  private Long totalBookCount;
  private Double totalPurchasedAmount;

  public static MonthlyOrderStatisticsResponse fromModel(MonthlyOrderStatistics statistics) {
    return MonthlyOrderStatisticsResponse.builder()
        .month(statistics.getMonthName())
        .totalOrderCount(statistics.getTotalOrderCount())
        .totalBookCount(statistics.getTotalBookCount())
        .totalPurchasedAmount(statistics.getTotalPurchasedAmount())
        .build();
  }
}
