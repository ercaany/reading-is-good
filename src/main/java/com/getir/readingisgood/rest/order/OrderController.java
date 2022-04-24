package com.getir.readingisgood.rest.order;

import com.getir.readingisgood.rest.common.ApiResponse;
import com.getir.readingisgood.rest.common.BaseController;
import com.getir.readingisgood.rest.common.DataResponse;
import com.getir.readingisgood.rest.order.dto.OrderCreateRequest;
import com.getir.readingisgood.rest.order.dto.OrderResponse;
import com.getir.readingisgood.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController extends BaseController {
  private final OrderService orderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<OrderResponse> create(@Valid @RequestBody OrderCreateRequest request) {
    return respond(OrderResponse.fromModel(orderService.create(request.toModel())));
  }

  @GetMapping("/{orderId}")
  public ApiResponse<OrderResponse> findOrderById(@PathVariable String orderId) {
    return respond(OrderResponse.fromModel(orderService.findById(orderId)));
  }

  @PutMapping("/{orderId}/complete")
  public ApiResponse<OrderResponse> complete(@PathVariable String orderId) {
    return respond(OrderResponse.fromModel(orderService.complete(orderId)));
  }

  @GetMapping
  public ApiResponse<DataResponse<OrderResponse>> findOrderByDateInterval(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
    return respond(
        orderService.findByCreateDateBetween(startDate, endDate).stream()
            .map(OrderResponse::fromModel)
            .toList());
  }
}
