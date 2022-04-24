package com.getir.readingisgood.service.book;

import com.getir.readingisgood.data.document.BookDocument;
import com.getir.readingisgood.data.repository.BookRepository;
import com.getir.readingisgood.service.exception.ReadingIsGoodBusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
  @Mock BookRepository bookRepository;

  @InjectMocks BookServiceImpl bookService;

  @Test
  public void shouldGetStockCountById() {
    when(bookRepository.findById("bookId"))
        .thenReturn(Optional.of(BookDocument.builder().stockCount(25L).build()));

    Long stockCount = bookService.getStockCountById("bookId");

    assertThat(stockCount).isNotNull();
    assertThat(stockCount).isNotNegative();
    assertThat(stockCount).isEqualTo(25L);
  }

  @Test
  public void shouldThrowException_whenBookStockIsNotFound() {
    when(bookRepository.findById("bookId")).thenReturn(Optional.empty());

    Throwable throwable = catchThrowable(() -> bookService.getStockCountById("bookId"));
    assertThat(throwable).isNotNull();
    assertThat(throwable.getMessage()).isEqualTo("Book cannot be found.");
    assertThat(throwable instanceof ReadingIsGoodBusinessException).isTrue();
  }

  @Test
  public void shouldCreateBook() {
    when(bookRepository.save(any(BookDocument.class)))
        .thenReturn(BookDocument.builder().id("bookId").name("book").stockCount(0L).build());

    Book book = bookService.create(Book.builder().build());

    assertThat(book).isNotNull();
    assertThat(book.getId()).isNotNull();
    assertThat(book.getStockCount()).isEqualTo(0L);
  }

  @Test
  public void shouldUpdateBookStock() {
    when(bookRepository.findById("bookId"))
        .thenReturn(
            Optional.of(BookDocument.builder().id("bookId").version(3L).stockCount(25L).build()));

    when(bookRepository.save(any(BookDocument.class)))
        .thenReturn(
            BookDocument.builder().id("bookId").version(4L).name("book").stockCount(35L).build());
    BookStockUpdate bookStockUpdate = new BookStockUpdate("bookId", 35L, "ercan");

    Book book = bookService.stockUpdate(bookStockUpdate);

    assertThat(book).isNotNull();
    assertThat(book.getId()).isEqualTo("bookId");
    assertThat(book.getStockCount()).isEqualTo(35L);
  }
}
