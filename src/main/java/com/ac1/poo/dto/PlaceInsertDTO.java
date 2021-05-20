package com.ac1.poo.dto;

import javax.validation.constraints.NotEmpty;

public class PlaceInsertDTO {

    @NotEmpty(message="Lugar tem que ter um nome")
    private String name;

    @NotEmpty(message="Lugar tem que ter um endereço")
    private String address;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
   

    
}
