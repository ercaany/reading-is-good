package com.getir.readingisgood.data.repository;

import com.getir.readingisgood.data.document.OrderDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderDocument, String> {
  Page<OrderDocument> findByCustomerId(String customerId, Pageable pageable);

  List<OrderDocument> findByCreateDateBetween(LocalDateTime from, LocalDateTime to);
}
