package com.bachy.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashFlowRequest {

  private String codeService;
  private String codePayment;
  private String description;
  private BigDecimal amount;
  private LocalDate datePayment;
}
