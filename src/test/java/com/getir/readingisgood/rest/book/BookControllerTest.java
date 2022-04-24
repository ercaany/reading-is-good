package com.getir.readingisgood.rest.book;

import com.getir.readingisgood.rest.BaseIntegrationTest;
import com.getir.readingisgood.rest.book.dto.BookCreateRequest;
import com.getir.readingisgood.rest.book.dto.BookResponse;
import com.getir.readingisgood.rest.book.dto.BookStockUpdateRequest;
import com.getir.readingisgood.rest.common.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class BookControllerTest extends BaseIntegrationTest {
  ParameterizedTypeReference<ApiResponse<BookResponse>> typeReference =
      new ParameterizedTypeReference<>() {};

  @Test
  public void shouldCreateBook() {
    BookCreateRequest bookCreateRequest =
        BookCreateRequest.builder().author("ercan").name("kitap").price(20.0).build();

    ResponseEntity<ApiResponse<BookResponse>> response =
        testRestTemplate.exchange(
            "/books", HttpMethod.POST, new HttpEntity<>(bookCreateRequest, headers), typeReference);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().data().getName()).isEqualTo("kitap");
    assertThat(response.getBody().data().getAuthor()).isEqualTo("ercan");
    assertThat(response.getBody().data().getPrice()).isEqualTo(20.0);
    assertThat(response.getBody().data().getBookId()).isNotNull();
  }

  @Test
  public void shouldUpdateBookStock() {
    BookStockUpdateRequest bookStockUpdateRequest =
        BookStockUpdateRequest.builder().stockCount(125L).updatedBy("ercan").build();

    ResponseEntity<ApiResponse<BookResponse>> response =
        testRestTemplate.exchange(
            "/books/6265a662e8483567de6b7d90",
            HttpMethod.PUT,
            new HttpEntity<>(bookStockUpdateRequest, headers),
            typeReference);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().data().getStockCount()).isEqualTo(125L);
  }
}
