package com.getir.readingisgood.service.statistics;

import java.util.List;

public interface MonthlyOrderStatisticsService {
    List<MonthlyOrderStatistics> findByCustomerId(String customerId);
}
