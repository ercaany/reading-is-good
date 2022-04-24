package com.getir.readingisgood.service.customer;

import com.getir.readingisgood.data.repository.CustomerRepository;
import com.getir.readingisgood.service.exception.ExceptionMessage;
import com.getir.readingisgood.service.exception.ReadingIsGoodBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;

  @Override
  public Customer register(Customer customer) {
    if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
      throw new ReadingIsGoodBusinessException(ExceptionMessage.EMAIL_ALREADY_REGISTERED);
    }

    return customerRepository.save(customer.toDocument()).toModel();
  }
}
