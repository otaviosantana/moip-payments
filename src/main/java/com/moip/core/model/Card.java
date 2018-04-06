package com.moip.core.model;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @NotEmpty
    private String cardHolderName;
    @NotEmpty
    @Transient
    private String number;
    @NotNull
    @Valid
    private LocalDate cardExpirationDate;
    @NotNull
    @Length(min = 3, max = 3)
    @Transient
    private String cvv;
    @JsonIgnore
    private String authorizationCode;
}
