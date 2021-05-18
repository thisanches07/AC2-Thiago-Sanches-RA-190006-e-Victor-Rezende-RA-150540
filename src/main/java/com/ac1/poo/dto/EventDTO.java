package com.ac1.poo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ac1.poo.entities.Event;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private String place;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate start_date;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate end_date;
    private LocalTime start_time;
    private LocalTime end_time;
    private String email_contact;
    private Long amountFreeTickets;
    private Long amountPayedTickets;
    private Double priceTicket;
    public EventDTO() {
    }

    public EventDTO(Long id, String name, String description, String place, LocalDate start_date, LocalDate end_date, LocalTime start_time, LocalTime end_time, String email_contact,long amountFreeTickets, long amountPayedTickets,Double priceTicket) {
        setId(id);
        setName(name);
        setDescription(description);
        setPlace(place);
        setStart_date(start_date);
        setEnd_date(end_date);
        setStart_time(start_time);
        setEnd_time(end_time);
        setEmail_contact(email_contact);
        setAmountFreeTickets(amountFreeTickets);
        setAmountPayedTickets(amountPayedTickets);
        setPriceTicket(priceTicket);
    }
    public EventDTO(Event event){
        this.id = event.getId();
        this.name = event.getName();
        this.place = event.getPlace();
        this.description = event.getDescription();
        this.start_date = event.getStart_date();
        this.end_date = event.getEnd_date();
        this.start_time = event.getStart_time();
        this.end_time = event.getEnd_time();
        this.email_contact = event.getEmail_contact();
        this.amountFreeTickets = event.getAmountFreeTickets();
        this.amountPayedTickets = event.getAmountPayedTickets();
        this.priceTicket = event.getPriceTicket();
        
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
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
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
}
