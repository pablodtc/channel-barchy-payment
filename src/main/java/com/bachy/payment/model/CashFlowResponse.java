package com.bachy.payment.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CashFlowResponse {

    private String codePayment;
    private BigDecimal totalAmount;
}
