package com.bachy.payment.service.spi;

import com.bachy.payment.model.PaymentModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentService {
  Mono<PaymentModel> findByCodePayment(String code);
  Flux<PaymentModel> getPayment ();
}
