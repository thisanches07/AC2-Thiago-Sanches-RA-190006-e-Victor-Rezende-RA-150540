package com.ac1.poo.dto;

import java.time.LocalDate;

import com.ac1.poo.entities.Ticket;
import com.ac1.poo.entities.TicketType;

public class TicketDTO {
    private Long id;
    private TicketType type;
    private LocalDate date;
    private Double price;

    public TicketDTO() {
    }
    
    public TicketDTO(Long id, TicketType type, LocalDate date, Double price) {
        setId(id);
        setType(type);
        setDate(date);
        setPrice(price);  
    }

    public TicketDTO(Ticket ticket){
        this.id = ticket.getId();
        this.type = ticket.getType();
        this.date = ticket.getDate();
        this.price = ticket.getPrice();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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
