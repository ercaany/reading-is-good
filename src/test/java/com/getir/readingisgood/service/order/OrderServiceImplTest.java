package com.getir.readingisgood.service.order;

import com.getir.readingisgood.data.document.OrderDocument;
import com.getir.readingisgood.data.repository.OrderRepository;
import com.getir.readingisgood.service.book.Book;
import com.getir.readingisgood.service.book.BookService;
import com.getir.readingisgood.service.book.BookStockUpdate;
import com.getir.readingisgood.service.exception.ReadingIsGoodBusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
  @Mock OrderRepository orderRepository;

  @Mock BookService bookService;

  @InjectMocks OrderServiceImpl orderService;

  @Test
  public void shouldFindOrderById() {
    when(orderRepository.findById("randomOrderId"))
        .thenReturn(Optional.of(OrderDocument.builder().id("randomOrderId").build()));

    Order order = orderService.findById("randomOrderId");

    assertThat(order).isNotNull();
    assertThat(order.getId()).isEqualTo("randomOrderId");
  }

  @Test
  public void shouldThrowException_whenOrderNotFound() {
    when(orderRepository.findById("randomOrderId")).thenReturn(Optional.empty());

    Throwable throwable = catchThrowable(() -> orderService.findById("randomOrderId"));
    assertThat(throwable).isNotNull();
    assertThat(throwable.getMessage()).isEqualTo("Order cannot be found.");
    assertThat(throwable instanceof ReadingIsGoodBusinessException).isTrue();
  }

  @Test
  public void shouldCompleteOrder() {
    when(orderRepository.findById("randomOrderId"))
        .thenReturn(Optional.of(OrderDocument.builder().id("randomOrderId").build()));

    when(orderRepository.save(any(OrderDocument.class)))
        .thenReturn(
            OrderDocument.builder()
                .id("randomOrderId")
                .orderState(OrderState.COMPLETED.getLabel())
                .completeDate(LocalDateTime.now())
                .build());

    Order order = orderService.complete("randomOrderId");
    assertThat(order).isNotNull();
    assertThat(order.getId()).isEqualTo("randomOrderId");
    assertThat(order.getOrderState()).isEqualTo(OrderState.COMPLETED.getLabel());
    assertThat(order.getCompleteDate()).isNotNull();
  }

  @Test
  public void shouldNotCompleteOrder_whenOrderNotFound() {
    when(orderRepository.findById("randomOrderId")).thenReturn(Optional.empty());

    Throwable throwable = catchThrowable(() -> orderService.complete("randomOrderId"));
    assertThat(throwable).isNotNull();
    assertThat(throwable.getMessage()).isEqualTo("Order cannot be found.");
    assertThat(throwable instanceof ReadingIsGoodBusinessException).isTrue();
  }

  @Test
  public void shouldThrowException_whenStockIsNotEnough() {
    when(bookService.getStockCountById("bookId")).thenReturn(3L);

    Throwable throwable =
        catchThrowable(
            () ->
                orderService.create(
                    Order.builder().id("randomOrderId").bookId("bookId").itemCount(20L).build()));
    assertThat(throwable).isNotNull();
    assertThat(throwable.getMessage()).isEqualTo("There is no enough book stock.");
    assertThat(throwable instanceof ReadingIsGoodBusinessException).isTrue();
  }

  @Test
  public void shouldCreateOrder_whenStockIsEnough() {
    when(bookService.getStockCountById("bookId")).thenReturn(30L);
    when(bookService.stockUpdate(any(BookStockUpdate.class))).thenReturn(Book.builder().build());
    when(orderRepository.save(any(OrderDocument.class)))
        .thenReturn(
            OrderDocument.builder()
                .id("randomOrderId")
                .bookId("bookId")
                .itemCount(15L)
                .orderState(OrderState.PENDING.getLabel())
                .createDate(LocalDateTime.now())
                .build());

    Order createdOrder =
        orderService.create(
            Order.builder().id("randomOrderId").bookId("bookId").itemCount(15L).build());
    assertThat(createdOrder).isNotNull();
    assertThat(createdOrder.getId()).isEqualTo("randomOrderId");
    assertThat(createdOrder.getOrderState()).isEqualTo(OrderState.PENDING.getLabel());
    assertThat(createdOrder.getCreateDate()).isNotNull();
  }
}
