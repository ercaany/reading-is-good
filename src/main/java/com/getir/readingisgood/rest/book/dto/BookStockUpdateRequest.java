package com.getir.readingisgood.rest.book.dto;

import com.getir.readingisgood.service.book.BookStockUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookStockUpdateRequest {
  @Min(0)
  private Long stockCount;

  private String updatedBy;

  public BookStockUpdate toModel(String bookId) {
    return BookStockUpdate.builder()
        .bookId(bookId)
        .stockCount(stockCount)
        .updatedBy(updatedBy)
        .build();
  }
}
