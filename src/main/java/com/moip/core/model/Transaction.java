package com.moip.core.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    private String id;
    private Client client;
    @NotNull
    private Person buyer;
    @NotNull
    private Payment payment;
}
