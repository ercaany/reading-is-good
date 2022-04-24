package com.getir.readingisgood.rest.book.dto;

import com.getir.readingisgood.service.book.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequest {
  @NotBlank private String name;
  @NotBlank private String author;
  @Positive private Double price;

  public Book toModel() {
    return Book.builder().name(name).author(author).price(price).build();
  }
}
