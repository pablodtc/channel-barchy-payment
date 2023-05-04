package com.bachy.payment.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Constants {
    PAYMENT_WITHDRAW ("withdraw"),
    PAYMENT_DEPOSIT ("deposit");
    private String value;
}
