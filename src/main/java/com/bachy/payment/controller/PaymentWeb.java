package com.bachy.payment.controller;

import com.bachy.payment.model.PaymentModel;
import com.bachy.payment.service.spi.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentWeb {
  private final PaymentService paymentService;

  @GetMapping("")
  public Mono<PaymentModel> registerCashFlowMovement (@RequestParam String code) {
    return paymentService.findByCodePayment(code);

  }

  @GetMapping("/get-all")
  public Flux<PaymentModel> getAllPaymentService () {
    return paymentService.getPayment();

  }
}
