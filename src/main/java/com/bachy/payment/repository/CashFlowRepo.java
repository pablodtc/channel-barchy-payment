package com.bachy.payment.repository;

import com.bachy.payment.model.CashFlowModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CashFlowRepo extends ReactiveCrudRepository<CashFlowModel, String> {

  Flux<CashFlowModel> findByDatePaymentIsBetween (String startDate, String endDate);
  //Mono<CashFlowModel> findByAmount (BigDecimal amount);

}
