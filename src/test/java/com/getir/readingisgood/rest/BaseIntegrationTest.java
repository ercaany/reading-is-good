package com.getir.readingisgood.rest;

import com.getir.readingisgood.rest.common.ApiResponse;
import com.getir.readingisgood.rest.security.controller.dto.AuthRequest;
import com.getir.readingisgood.rest.security.controller.dto.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTest {

  protected @Autowired TestRestTemplate testRestTemplate;
  protected HttpHeaders headers;

  @BeforeEach
  void setUp() {
    AuthRequest authRequest = new AuthRequest("ercan", "ercan");

    ParameterizedTypeReference<ApiResponse<AuthResponse>> typeReference =
        new ParameterizedTypeReference<>() {};

    ResponseEntity<ApiResponse<AuthResponse>> response =
        testRestTemplate.exchange(
            "/auth", HttpMethod.POST, new HttpEntity<>(authRequest), typeReference);

    assertThat(response.getBody()).isNotNull();
    String token = response.getBody().data().token();
    headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + token);
  }
}
