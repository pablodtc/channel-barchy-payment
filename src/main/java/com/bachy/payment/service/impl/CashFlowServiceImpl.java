package com.bachy.payment.service.impl;

import com.bachy.payment.mapper.CashFlowMapper;
import com.bachy.payment.model.ApiResponse;
import com.bachy.payment.model.CashFlowModel;
import com.bachy.payment.model.CashFlowRequest;
import com.bachy.payment.model.PaymentModel;
import com.bachy.payment.repository.CashFlowRepo;
import com.bachy.payment.repository.PaymentRepo;
import com.bachy.payment.service.spi.CashFlowService;
import com.bachy.payment.service.spi.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CashFlowServiceImpl implements CashFlowService {
  private final CashFlowRepo repo;
  private final CashFlowMapper mapper;
  private final PaymentRepo paymentRepo;
  private final PaymentService paymentService;

  @Override
  public Mono<ApiResponse<CashFlowRequest>> registerCashFlowMovement (CashFlowRequest request) {

    return paymentService.findByCodePayment(request.getCodePayment())
        .flatMap(paymentModel -> {
          if (paymentModel.getCodePayment().equals("withdraw")) {
            return addAmountToTotalpayment(request, paymentModel);
          } else if (paymentModel.getCodePayment().equals("deposit")){
            return addAmountToTotalpayment(request, paymentModel);
          }
          return Mono.empty();
        }).defaultIfEmpty(ApiResponse.<CashFlowRequest>builder()
            .code("E0001")
            .message("Codigo no encontrado")
            .payload(request)
            .build());

  }

  private Mono<ApiResponse<CashFlowRequest>> addAmountToTotalpayment(CashFlowRequest request, PaymentModel paymentModel) {
    BigDecimal add = paymentModel.getAmount().add(request.getAmount());
    paymentModel.setCodePayment(request.getCodePayment());
    paymentModel.setAmount(add);
    return paymentRepo.save(paymentModel)
        .then(repo.save(mapper.toCashFlowModel(request)))
        .then(Mono.just(ApiResponse.<CashFlowRequest>builder()
            .code("S0001")
            .message("Creado Correctamente")
            .payload(request)
            .build()));
  }

  @Override
  public Mono<List<CashFlowModel>> getAllCashFlowMovement() {
    Mono<List<CashFlowModel>> listMono = repo.findAll().collectList();
    return listMono;
  }

  @Override
  public Flux<CashFlowModel> findByDate(String startDate, String endDate) {
    return repo.findByDatePaymentIsBetween(startDate, endDate);
  }

}
