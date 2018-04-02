package com.moip.core.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Person {

    @NotEmpty
    private String name;
    @Email
    private String email;
    @NotEmpty
    private String cpf;
}
