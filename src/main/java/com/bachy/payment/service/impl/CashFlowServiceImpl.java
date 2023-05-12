package com.bachy.payment.service.impl;

import com.bachy.payment.mapper.CashFlowMapper;
import com.bachy.payment.model.*;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            .message("Código no encontrado")
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
    return repo.findAll().collectList();

  }

  @Override
  public Flux<CashFlowModel> findByDate(String startDate, String endDate) {
    return repo.findByDatePaymentIsBetween(startDate, endDate);
  }

  @Override
  public Mono<List<CashFlowResponse>> getTotalAmountByDate(DateRequest dateRequest) {
    return this.findByDate(dateRequest.getStartDate(), dateRequest.getEndDate())
            .collect(Collectors.groupingBy(CashFlowModel::getCodePayment))
            .map(x -> x.values()
                    .stream()
                    .map(cashFlowModels -> cashFlowModels
                            .stream()
                            .reduce((a, b) -> new CashFlowModel(null, null, a.getCodePayment(), null, a.getAmount().add(b.getAmount()), null)))
                    .map(Optional::get)
                    .map(mapper::toCashFlowResponse)
                    .collect(Collectors.toList()));

    /*return this.findByDate(dateRequest.getStartDate(), dateRequest.getEndDate())
            .collect(Collectors.groupingBy(CashFlowModel::getCodePayment))
            .map(x -> x.entrySet()
                    .stream()
                    .map(xx -> xx.getValue()
                            .stream()
                            .reduce((a, b) -> new CashFlowModel(null, null, a.getCodePayment(), null, a.getAmount().add(b.getAmount()), null)))
                    .map(y -> y.get())
                    .map(z -> mapper.toCashFlowResponse(z))
                    .collect(Collectors.toList()));*/
            //        collectingAndThen(reducing(BigDecimal.ZERO, BigDecimal::add))))

  }

}
