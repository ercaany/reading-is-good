package com.getir.readingisgood.service.statistics;

import com.getir.readingisgood.data.aggregation.MonthlyOrderStatisticsAggregation;
import com.getir.readingisgood.data.document.OrderDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthlyOrderStatisticsServiceImpl implements MonthlyOrderStatisticsService {
  private final MongoTemplate mongoTemplate;

  @Override
  public List<MonthlyOrderStatistics> findByCustomerId(String customerId) {

    List<MonthlyOrderStatistics> mappedResults =
        mongoTemplate
            .aggregate(
                MonthlyOrderStatisticsAggregation.getAggregationByCustomerId(customerId),
                mongoTemplate.getCollectionName(OrderDocument.class),
                MonthlyOrderStatistics.class)
            .getMappedResults();

    return mappedResults;
  }
}
