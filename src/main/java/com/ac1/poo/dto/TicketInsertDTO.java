package com.ac1.poo.dto;

import java.time.LocalDate;

import com.ac1.poo.entities.TicketType;

public class TicketInsertDTO {
    private TicketType type;
    private LocalDate date;
    private Double price;
    
    public TicketType getType() {
        return type;
    }
    public void setType(TicketType type) {
        this.type = type;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    
    
   

    
}
