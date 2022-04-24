package com.getir.readingisgood.service.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
  Order create(Order order);

  Order findById(String orderId);

  Order complete(String orderId);

  Page<Order> findByCustomerId(String customerId, Pageable pageable);

  List<Order> findByCreateDateBetween(LocalDateTime from, LocalDateTime to);
}
