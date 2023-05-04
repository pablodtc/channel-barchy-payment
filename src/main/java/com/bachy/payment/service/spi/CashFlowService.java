package com.bachy.payment.service.spi;

import com.bachy.payment.model.ApiResponse;
import com.bachy.payment.model.CashFlowModel;
import com.bachy.payment.model.CashFlowRequest;
import com.bachy.payment.model.PaymentModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CashFlowService {

  Mono<ApiResponse<CashFlowRequest>> registerCashFlowMovement (CashFlowRequest request);
  Mono<List<CashFlowModel>> getAllCashFlowMovement ();

  Flux<CashFlowModel> findByDate (String startDate, String endDate);
}
