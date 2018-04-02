package com.moip.core.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Transaction {

    @Id
    private String id;
    private Client client;
    @NotNull
    private Person buyer;
    @NotNull
    private Payment payment;
}
