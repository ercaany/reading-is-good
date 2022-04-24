package com.getir.readingisgood.service.book;

import com.getir.readingisgood.data.document.BookDocument;
import com.getir.readingisgood.data.repository.BookRepository;
import com.getir.readingisgood.service.exception.ExceptionMessage;
import com.getir.readingisgood.service.exception.ReadingIsGoodBusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;

  @Override
  public Book create(Book book) {
    BookDocument bookDocument = book.toDocument();
    bookDocument.setStockCount(0L);
    return bookRepository.save(bookDocument).toModel();
  }

  @Override
  public Long getStockCountById(String bookId) {
    BookDocument bookDocument = findById(bookId);
    return bookDocument.getStockCount();
  }

  @Override
  public Book stockUpdate(BookStockUpdate bookStockUpdate) {
    BookDocument bookDocument = findById(bookStockUpdate.getBookId());

    try {
      bookDocument.setStockCount(bookStockUpdate.getStockCount());
      return bookRepository.save(bookDocument).toModel();
    } catch (OptimisticLockingFailureException ex) {
      log.error(
          "Error occurred while updating stock for [bookId = {}]", bookStockUpdate.getBookId(), ex);
      throw new ReadingIsGoodBusinessException(ExceptionMessage.BOOK_ALREADY_UPDATED);
    }
  }

  private BookDocument findById(String bookId) {
    return bookRepository
        .findById(bookId)
        .orElseThrow(() -> new ReadingIsGoodBusinessException(ExceptionMessage.BOOK_NOT_FOUND));
  }
}
