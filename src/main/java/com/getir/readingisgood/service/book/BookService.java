package com.getir.readingisgood.service.book;

public interface BookService {
  Book create(Book Book);

  Long getStockCountById(String bookId);

  Book stockUpdate(BookStockUpdate bookStockUpdate);
}
