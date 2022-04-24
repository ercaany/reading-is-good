package com.getir.readingisgood.data.repository;

import com.getir.readingisgood.data.document.CustomerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDocument, String> {
  Optional<CustomerDocument> findByEmail(String email);
}
