package com.bachy.payment.controller;

import com.bachy.payment.model.ApiResponse;
import com.bachy.payment.model.CashFlowModel;
import com.bachy.payment.model.CashFlowRequest;
import com.bachy.payment.model.DateRequest;
import com.bachy.payment.service.impl.CashFlowServiceImpl;
import com.bachy.payment.service.spi.CashFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CashFlowWeb {
  private final CashFlowService serviceImpl;

  @PostMapping("/register")
  public Mono<ApiResponse<CashFlowRequest>> registerCashFlowMovement (@RequestBody CashFlowRequest request) {
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
}
