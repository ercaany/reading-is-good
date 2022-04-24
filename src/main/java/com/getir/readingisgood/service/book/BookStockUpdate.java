package com.getir.readingisgood.service.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookStockUpdate {
  private String bookId;
  private Long stockCount;
  private String updatedBy;
}
