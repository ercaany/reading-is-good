package com.getir.readingisgood.rest.order;

import com.getir.readingisgood.rest.BaseIntegrationTest;
import com.getir.readingisgood.rest.common.ApiResponse;
import com.getir.readingisgood.rest.order.dto.OrderCreateRequest;
import com.getir.readingisgood.rest.order.dto.OrderResponse;
import com.getir.readingisgood.service.order.OrderState;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class OrderControllerTest extends BaseIntegrationTest {
  ParameterizedTypeReference<ApiResponse<OrderResponse>> typeReference =
      new ParameterizedTypeReference<>() {};

  @Test
  public void shouldCreateOrder() {
    OrderCreateRequest orderCreateRequest =
        OrderCreateRequest.builder()
            .bookId("6265a673c88c533daa5aea67")
            .customerId("6265104b0c313a7be8834026")
            .itemCount(3L)
            .totalPrice(255.0)
            .build();

    ResponseEntity<ApiResponse<OrderResponse>> response =
        testRestTemplate.exchange(
            "/orders",
            HttpMethod.POST,
            new HttpEntity<>(orderCreateRequest, headers),
            typeReference);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().data().getBookId()).isEqualTo("6265a673c88c533daa5aea67");
    assertThat(response.getBody().data().getCustomerId()).isEqualTo("6265104b0c313a7be8834026");
    assertThat(response.getBody().data().getTotalPrice()).isEqualTo(255.0);
    assertThat(response.getBody().data().getItemCount()).isEqualTo(3L);
    assertThat(response.getBody().data().getOrderId()).isNotNull();
  }

  @Test
  public void shouldFindOrderById() {
    ResponseEntity<ApiResponse<OrderResponse>> response =
        testRestTemplate.exchange(
            "/orders/62652346c6bb2f024cf6b36b",
            HttpMethod.GET,
            new HttpEntity<>(headers),
            typeReference);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().data().getOrderId()).isEqualTo("62652346c6bb2f024cf6b36b");
    assertThat(response.getBody().data().getBookId()).isEqualTo("62651a8f4ad75f20abeba7ff");
    assertThat(response.getBody().data().getCustomerId()).isEqualTo("62651aa84ad75f20abeba800");
    assertThat(response.getBody().data().getTotalPrice()).isEqualTo(77.0);
    assertThat(response.getBody().data().getItemCount()).isEqualTo(7L);
  }

  @Test
  public void shouldCompleteOrder() {
    ResponseEntity<ApiResponse<OrderResponse>> response =
        testRestTemplate.exchange(
            "/orders/62652346c6bb2f024cf6b36b/complete",
            HttpMethod.PUT,
            new HttpEntity<>(headers),
            typeReference);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().data().getOrderId()).isEqualTo("62652346c6bb2f024cf6b36b");
    assertThat(response.getBody().data().getCompleteDate()).isNotNull();
    assertThat(response.getBody().data().getOrderState())
        .isEqualTo(OrderState.COMPLETED.getLabel());
  }
}
