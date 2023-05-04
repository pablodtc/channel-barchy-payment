package com.bachy.payment.mapper;

import com.bachy.payment.model.CashFlowModel;
import com.bachy.payment.model.CashFlowRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CashFlowMapper {

  public CashFlowModel toCashFlowModel (CashFlowRequest request) {

    CashFlowModel model = new CashFlowModel();
    model.setCodeService(request.getCodeService());
    model.setCodePayment(request.getCodePayment());
    model.setDescription(request.getDescription());
    model.setAmount(request.getAmount());
    LocalDateTime dateTimeNow = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formatDateTime = dateTimeNow.format(formatter);
    model.setDatePayment(formatDateTime);

    return model;
  }
}
