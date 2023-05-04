package com.bachy.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentModel {

  @Id
  private String id;
  private String codePayment;
  private BigDecimal amount;
}