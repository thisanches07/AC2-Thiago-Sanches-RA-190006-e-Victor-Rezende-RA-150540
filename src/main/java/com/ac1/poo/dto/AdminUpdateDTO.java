package com.ac1.poo.dto;

import javax.validation.constraints.NotEmpty;

public class AdminUpdateDTO {

    @NotEmpty(message="Administrador tem que ter um nome")
    private String name;

    @NotEmpty(message="Administrador tem que ter um email")
    private String email;

    @NotEmpty(message="Administrador tem que ter um número de telefone")
    private String phoneNumber;
    
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

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
}
