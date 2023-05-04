package com.bachy.payment.service.impl;

import com.bachy.payment.model.PaymentModel;
import com.bachy.payment.repository.PaymentRepo;
import com.bachy.payment.service.spi.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepo paymentRepo;

  @Override
  public Mono<PaymentModel> findByCodePayment(String code) {
    return paymentRepo.findByCodePayment(code);
  }

  @Override
  public Flux<PaymentModel> getPayment() {
    return paymentRepo.findAll();
  }
}
