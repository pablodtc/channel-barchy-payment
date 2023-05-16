package com.bachy.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashFlowRequest {

  @Pattern(regexp = "food|public_services|baby_health|financial_payments|shopping|public_transportation|others")
  private String codeService;
  @Pattern(regexp = "deposit|withdraw")
  private String codePayment;
  private String description;
  private BigDecimal amount;
  private LocalDate datePayment;
}
