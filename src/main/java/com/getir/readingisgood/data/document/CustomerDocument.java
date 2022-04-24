package com.getir.readingisgood.data.document;

import com.getir.readingisgood.service.customer.Customer;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("customer")
@Builder
@Data
public class CustomerDocument {
  @Id private String id;
  private String name;
  private String email;
  private String password;

  public Customer toModel() {
    return Customer.builder().id(id).name(name).email(email).password(password).build();
  }
}
