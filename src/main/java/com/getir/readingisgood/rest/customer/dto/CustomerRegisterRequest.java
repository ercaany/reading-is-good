package com.getir.readingisgood.rest.customer.dto;

import com.getir.readingisgood.service.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegisterRequest {
  @NotBlank private String name;
  @NotBlank private String email;
  @NotBlank private String password;

  public Customer toModel() {
    return Customer.builder().name(name).email(email).password(password).build();
  }
}
