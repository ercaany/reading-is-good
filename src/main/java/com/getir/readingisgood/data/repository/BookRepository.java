package com.getir.readingisgood.data.repository;

import com.getir.readingisgood.data.document.BookDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<BookDocument, String> {}
