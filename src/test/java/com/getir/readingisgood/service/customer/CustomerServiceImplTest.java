package com.getir.readingisgood.service.customer;

import com.getir.readingisgood.data.document.CustomerDocument;
import com.getir.readingisgood.data.repository.CustomerRepository;
import com.getir.readingisgood.service.exception.ReadingIsGoodBusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

  @Mock CustomerRepository customerRepository;

  @InjectMocks CustomerServiceImpl customerService;

  @Test
  void shouldThrowException_whenEmailExists() {
    when(customerRepository.findByEmail("ercaany@gmail.com"))
        .thenReturn(Optional.of(CustomerDocument.builder().build()));

    Throwable throwable =
        catchThrowable(
            () -> customerService.register(Customer.builder().email("ercaany@gmail.com").build()));

    assertThat(throwable).isNotNull();
    assertThat(throwable.getMessage()).isEqualTo("This email is already registered.");
    assertThat(throwable instanceof ReadingIsGoodBusinessException).isTrue();
  }

  @Test
  void shouldRegister_whenEmailNotExist() {
    when(customerRepository.findByEmail("ercaany@gmail.com")).thenReturn(Optional.empty());
    when(customerRepository.save(any(CustomerDocument.class)))
        .thenReturn(CustomerDocument.builder().email("ercaany@gmail.com").build());

    Customer registeredCustomer =
        customerService.register(Customer.builder().email("ercaany@gmail.com").build());

    assertThat(registeredCustomer).isNotNull();
    assertThat(registeredCustomer.getEmail()).isEqualTo("ercaany@gmail.com");
  }
}
