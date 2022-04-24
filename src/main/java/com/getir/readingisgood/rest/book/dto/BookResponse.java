package com.getir.readingisgood.rest.book.dto;

import com.getir.readingisgood.service.book.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
  private String bookId;
  private String name;
  private String author;
  private Long stockCount;
  private Double price;

  public static BookResponse fromModel(Book book) {
    return BookResponse.builder()
        .bookId(book.getId())
        .name(book.getName())
        .author(book.getAuthor())
        .stockCount(book.getStockCount())
        .price(book.getPrice())
        .build();
  }
}
