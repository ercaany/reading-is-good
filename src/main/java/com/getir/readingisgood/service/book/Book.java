package com.getir.readingisgood.service.book;

import com.getir.readingisgood.data.document.BookDocument;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
  private String id;
  private String name;
  private String author;
  private Long stockCount;
  private Double price;

  public BookDocument toDocument() {
    return BookDocument.builder()
        .id(id)
        .name(name)
        .author(author)
        .stockCount(stockCount)
        .price(price)
        .build();
  }
}
