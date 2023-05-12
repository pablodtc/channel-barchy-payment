package com.bachy.payment.service.spi;

import com.bachy.payment.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CashFlowService {

  Mono<ApiResponse<CashFlowRequest>> registerCashFlowMovement (CashFlowRequest request);
  Mono<List<CashFlowModel>> getAllCashFlowMovement ();
  Flux<CashFlowModel> findByDate (String startDate, String endDate);
  Mono<List<CashFlowResponse>> getTotalAmountByDate(DateRequest dateRequest);
}
