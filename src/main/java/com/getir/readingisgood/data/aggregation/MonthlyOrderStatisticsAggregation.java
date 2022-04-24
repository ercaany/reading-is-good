package com.getir.readingisgood.data.aggregation;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;

import java.time.LocalDateTime;

public class MonthlyOrderStatisticsAggregation {

  public static Aggregation getAggregationByCustomerId(String customerId) {
    return Aggregation.newAggregation(
        Aggregation.project("createDate", "itemCount", "totalPrice", "customerId")
            .and(DateOperators.Month.monthOf("createDate"))
            .as("month")
            .and(DateOperators.Year.yearOf("createDate"))
            .as("year"),
        Aggregation.match(Criteria.where("year").is(LocalDateTime.now().getYear())),
        Aggregation.match(Criteria.where("customerId").is(customerId)),
        Aggregation.group("month")
            .sum("itemCount")
            .as("totalBookCount")
            .sum("totalPrice")
            .as("totalPurchasedAmount")
            .count()
            .as("totalOrderCount"),
        Aggregation.project("totalBookCount", "totalOrderCount", "totalPurchasedAmount")
            .and("month")
            .previousOperation());
  }
}
