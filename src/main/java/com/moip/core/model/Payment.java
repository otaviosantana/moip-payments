package com.moip.core.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @NotNull
    private BigDecimal amount;
    @NotNull
    private PaymentType type;
    private Card card;
    private PaymentStatus status = PaymentStatus.PENDING;
}
