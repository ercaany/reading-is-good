package com.getir.readingisgood.rest.customer;

import com.getir.readingisgood.rest.common.ApiResponse;
import com.getir.readingisgood.rest.common.BaseController;
import com.getir.readingisgood.rest.common.PagedDataResponse;
import com.getir.readingisgood.rest.customer.dto.CustomerRegisterRequest;
import com.getir.readingisgood.rest.customer.dto.CustomerResponse;
import com.getir.readingisgood.rest.order.dto.OrderResponse;
import com.getir.readingisgood.service.customer.Customer;
import com.getir.readingisgood.service.customer.CustomerService;
import com.getir.readingisgood.service.order.Order;
import com.getir.readingisgood.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController extends BaseController {
  private final CustomerService customerService;
  private final OrderService orderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<CustomerResponse> register(
      @Valid @RequestBody CustomerRegisterRequest request) {
    Customer registeredCustomer = customerService.register(request.toModel());
    return respond(CustomerResponse.fromModel(registeredCustomer));
  }

  @GetMapping("/{customerId}/orders")
  public ApiResponse<PagedDataResponse<OrderResponse>> findOrdersByCustomerId(
      @PathVariable String customerId, @RequestParam int page, @RequestParam int pageSize) {

    Page<Order> pageByCustomerId =
        orderService.findByCustomerId(customerId, PageRequest.of(page, pageSize));
    List<OrderResponse> orderResponseList =
        pageByCustomerId.get().map(OrderResponse::fromModel).toList();

    return respond(orderResponseList, page, pageSize);
  }
}
