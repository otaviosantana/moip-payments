package com.moip.core.model;

import java.time.YearMonth;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Card {

    @NotEmpty
    private String cardHolderName;
    @NotEmpty
    @Transient
    private String number;
    @NotNull
    @Valid
    @DateTimeFormat(pattern = "MM/yyyy")
    private YearMonth cardExpirationDate;
    @NotNull
    @Length(min = 3, max = 3)
    @Transient
    private String cvv;
}
