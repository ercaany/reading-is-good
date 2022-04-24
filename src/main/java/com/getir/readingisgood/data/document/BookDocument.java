package com.getir.readingisgood.data.document;

import com.getir.readingisgood.service.book.Book;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("book")
@Builder
@Data
public class BookDocument {
  @Id private String id;
  @Version private Long version;
  private String name;
  private String author;
  private Long stockCount;
  private Double price;

  public Book toModel() {
    return Book.builder()
        .id(id)
        .author(author)
        .name(name)
        .stockCount(stockCount)
        .price(price)
        .build();
  }
}
