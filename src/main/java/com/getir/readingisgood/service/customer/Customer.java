package com.getir.readingisgood.service.customer;

import com.getir.readingisgood.data.document.CustomerDocument;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Customer {
  private String id;
  private String name;
  private String email;
  private String password;

  public CustomerDocument toDocument() {
    return CustomerDocument.builder().id(id).email(email).name(name).password(password).build();
  }
}
