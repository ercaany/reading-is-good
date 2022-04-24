package com.getir.readingisgood.rest.customer;

import com.getir.readingisgood.rest.BaseIntegrationTest;
import com.getir.readingisgood.rest.common.ApiResponse;
import com.getir.readingisgood.rest.customer.dto.CustomerRegisterRequest;
import com.getir.readingisgood.rest.customer.dto.CustomerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerControllerTest extends BaseIntegrationTest {

  @Test
  void shouldRegisterCustomer() {
    String randomEmail = UUID.randomUUID().toString().replace("-", "") + "@gmail.com";

    CustomerRegisterRequest request =
        CustomerRegisterRequest.builder().name("ercan").password("pass").email(randomEmail).build();
    ParameterizedTypeReference<ApiResponse<CustomerResponse>> typeReference =
        new ParameterizedTypeReference<>() {};

    ResponseEntity<ApiResponse<CustomerResponse>> response =
        testRestTemplate.exchange(
            "/customers", HttpMethod.POST, new HttpEntity<>(request), typeReference);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().data().getEmail()).isEqualTo(randomEmail);
    assertThat(response.getBody().data().getName()).isEqualTo("ercan");
  }
}
