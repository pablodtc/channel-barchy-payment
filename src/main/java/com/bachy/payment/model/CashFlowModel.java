package com.bachy.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document (collection = "cash_flow")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CashFlowModel {

  @Id
  private String id;
  private String codeService;
  private String codePayment;
  private String description;
  private BigDecimal amount;
  private String datePayment;

  /*
  Service
  food, public_services, baby_health, financial_payments, shopping, public_transportation, others
   */
}
