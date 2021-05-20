package com.ac1.poo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;


public class EventUpdateDTO {

    @NotEmpty(message="Evento tem que ter um nome")
    private String name;

    @NotEmpty(message="Evento tem que ter uma descrição")
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message="Evento tem que ter uma data de inicio")
    private LocalDate start_date;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message="Evento tem que ter uma data de fim")
    private LocalDate end_date;

    @NotNull(message="Evento tem que ter um horário de início")
    private LocalTime start_time;

    @NotNull(message="Evento tem que ter um horário de fim")
    private LocalTime end_time;

    @NotEmpty(message="Evento tem que ter um email")
    private String email_contact;

    @NotNull(message="Evento tem que ter um email")
    @DecimalMin(value ="0.0", message = "Número de tickets gratuitos tem que ser maior ou igual a zero")
    private Long amountFreeTickets;

    @NotNull(message="Número de tickets pagos tem que ser difrente de null")
    @DecimalMin(value ="0.0", message = "Número de tickets pagos tem que ser maior ou igual a zero")
    private Long amountPayedTickets;

    @NotNull(message="Tickets pagos do evento precisam ter um valor")
    @DecimalMin(value ="0.0", message = "Preço dos tickets tem que ser maior que zero")
    private Double priceTicket;
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public String getEmail_contact() {
        return email_contact;
    }
    
    public void setEmail_contact(String email_contact) {
        this.email_contact = email_contact;
    }

    public Long getAmountFreeTickets() {
        return amountFreeTickets;
    }

    public void setAmountFreeTickets(Long amountFreeTickets) {
        this.amountFreeTickets = amountFreeTickets;
    }

    public Long getAmountPayedTickets() {
        return amountPayedTickets;
    }

    public void setAmountPayedTickets(Long amountPayedTickets) {
        this.amountPayedTickets = amountPayedTickets;
    }

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }
    
}
