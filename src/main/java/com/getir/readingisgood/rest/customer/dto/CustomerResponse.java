package com.getir.readingisgood.rest.customer.dto;

import com.getir.readingisgood.service.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
  private String id;
  private String name;
  private String email;

  public static CustomerResponse fromModel(Customer customer) {
    return CustomerResponse.builder()
        .id(customer.getId())
        .email(customer.getEmail())
        .name(customer.getName())
        .build();
  }
}
