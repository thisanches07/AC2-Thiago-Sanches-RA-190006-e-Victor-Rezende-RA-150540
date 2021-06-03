package com.ac1.poo.dto;

import javax.validation.constraints.NotEmpty;

public class AttendUpdateDTO {

    @NotEmpty(message="Usuário tem que ter um nome")
    private String name;

    @NotEmpty(message="Usuário tem que ter um email")
    private String email;
    
    
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

    
}
