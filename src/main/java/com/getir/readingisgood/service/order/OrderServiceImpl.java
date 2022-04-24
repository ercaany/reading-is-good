package com.getir.readingisgood.service.order;

import com.getir.readingisgood.data.document.OrderDocument;
import com.getir.readingisgood.data.repository.OrderRepository;
import com.getir.readingisgood.service.book.BookService;
import com.getir.readingisgood.service.book.BookStockUpdate;
import com.getir.readingisgood.service.exception.ExceptionMessage;
import com.getir.readingisgood.service.exception.ReadingIsGoodBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final BookService bookService;

  @Override
  @Transactional
  public Order create(Order order) {
    Long currentStockCount = bookService.getStockCountById(order.getBookId());
    if (order.getItemCount() > currentStockCount) {
      throw new ReadingIsGoodBusinessException(ExceptionMessage.NOT_ENOUGH_BOOK_STOCK);
    }

    /*TODO retry mechanism?*/
    BookStockUpdate bookStockUpdate =
        BookStockUpdate.builder()
            .bookId(order.getBookId())
            .stockCount(currentStockCount - order.getItemCount())
            .build();
    bookService.stockUpdate(bookStockUpdate);

    order.setOrderState(OrderState.PENDING.getLabel());
    order.setCreateDate(LocalDateTime.now());
    return orderRepository.save(order.toDocument()).toModel();
  }

  @Override
  public Order findById(String orderId) {
    return orderRepository
        .findById(orderId)
        .orElseThrow(() -> new ReadingIsGoodBusinessException(ExceptionMessage.ORDER_NOT_FOUND))
        .toModel();
  }

  @Override
  public Order complete(String orderId) {
    Order order = findById(orderId);
    order.setOrderState(OrderState.COMPLETED.getLabel());
    order.setCompleteDate(LocalDateTime.now());
    return orderRepository.save(order.toDocument()).toModel();
  }

  @Override
  public Page<Order> findByCustomerId(String customerId, Pageable pageable) {
    return orderRepository.findByCustomerId(customerId, pageable).map(OrderDocument::toModel);
  }

  @Override
  public List<Order> findByCreateDateBetween(LocalDateTime from, LocalDateTime to) {
    return orderRepository.findByCreateDateBetween(from, to).stream()
        .map(OrderDocument::toModel)
        .toList();
  }
}
