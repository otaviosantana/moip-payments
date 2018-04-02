package com.moip.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponse {

    private PaymentStatus status;
    private String value;
}
