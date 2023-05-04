package com.bachy.payment.repository;

import com.bachy.payment.model.PaymentModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PaymentRepo extends ReactiveCrudRepository<PaymentModel, String> {

  Mono<PaymentModel> findByCodePayment (String code);
}
