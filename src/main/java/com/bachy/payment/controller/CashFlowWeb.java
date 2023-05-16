package com.bachy.payment.controller;

import com.bachy.payment.model.*;
import com.bachy.payment.service.spi.CashFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CashFlowWeb {
  private final CashFlowService serviceImpl;

  @PostMapping("/register")
  public Mono<ApiResponse<CashFlowRequest>> registerCashFlowMovement (@Valid @RequestBody CashFlowRequest request) {
    return serviceImpl.registerCashFlowMovement(request);

  }

  @GetMapping()
  public Mono<List<CashFlowModel>> getAllCashFlowMovement () {
    return serviceImpl.getAllCashFlowMovement();
  }

  @GetMapping("/get-date")
  public Flux<CashFlowModel> getByDate (@RequestBody DateRequest dateRequest) {
    return serviceImpl.findByDate(dateRequest.getStartDate(), dateRequest.getEndDate());
  }

  @GetMapping("/get-total-amount")
  public Mono<List<CashFlowResponse>> getTotalAmountByDate (@RequestBody DateRequest dateRequest) {
    return serviceImpl.getTotalAmountByDate(dateRequest);
  }
}
