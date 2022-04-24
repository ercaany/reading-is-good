package com.getir.readingisgood.rest.book;

import com.getir.readingisgood.rest.book.dto.BookCreateRequest;
import com.getir.readingisgood.rest.book.dto.BookResponse;
import com.getir.readingisgood.rest.book.dto.BookStockUpdateRequest;
import com.getir.readingisgood.rest.common.ApiResponse;
import com.getir.readingisgood.rest.common.BaseController;
import com.getir.readingisgood.service.book.Book;
import com.getir.readingisgood.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController extends BaseController {
  private final BookService bookService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<BookResponse> create(@Valid @RequestBody BookCreateRequest request) {
    Book book = bookService.create(request.toModel());
    return respond(BookResponse.fromModel(book));
  }

  @PutMapping("/{bookId}")
  public ApiResponse<BookResponse> updateStock(
      @PathVariable String bookId, @Valid @RequestBody BookStockUpdateRequest request) {
    Book book = bookService.stockUpdate(request.toModel(bookId));
    return respond(BookResponse.fromModel(book));
  }
}
