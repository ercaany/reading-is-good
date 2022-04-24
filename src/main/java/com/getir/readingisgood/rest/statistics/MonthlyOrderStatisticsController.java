package com.getir.readingisgood.rest.statistics;

import com.getir.readingisgood.rest.common.ApiResponse;
import com.getir.readingisgood.rest.common.BaseController;
import com.getir.readingisgood.rest.common.DataResponse;
import com.getir.readingisgood.rest.statistics.dto.MonthlyOrderStatisticsResponse;
import com.getir.readingisgood.service.statistics.MonthlyOrderStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class MonthlyOrderStatisticsController extends BaseController {
  private final MonthlyOrderStatisticsService monthlyOrderStatisticsService;

  @GetMapping("/{customerId}")
  public ApiResponse<DataResponse<MonthlyOrderStatisticsResponse>> findByCustomerId(
      @PathVariable String customerId) {
    return respond(
        monthlyOrderStatisticsService.findByCustomerId(customerId).stream()
            .map(MonthlyOrderStatisticsResponse::fromModel)
            .toList());
  }
}
