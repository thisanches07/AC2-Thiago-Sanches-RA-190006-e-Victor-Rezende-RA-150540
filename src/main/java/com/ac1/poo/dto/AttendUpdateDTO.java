package com.ac1.poo.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AttendUpdateDTO {

    @NotEmpty(message="Usuário tem que ter um nome")
    private String name;

    @NotEmpty(message="Usuário tem que ter um email")
    private String email;
    
    @NotNull(message="Usuário tem que ter um balanço")
    @DecimalMin(value ="0.0", message = "Balanço tem que ser igual ou maior que zero")
    private Double balance;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    
}
