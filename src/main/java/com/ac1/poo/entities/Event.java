package com.ac1.poo.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ac1.poo.dto.EventInsertDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="TB_EVENT")
public class Event implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

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

    @OneToMany( cascade = CascadeType.PERSIST)
    @JoinColumn(name="EVENT_ID")
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="TB_PLACES_EVENTS",
            joinColumns =  @JoinColumn(name="EVENT_ID"),
            inverseJoinColumns = @JoinColumn(name="PLACE_ID")
    )
    private List<Place> places = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="ADMIN_ID")
    private Admin admin;
    
    public Event(){

    }

    public Event(EventInsertDTO event){
        this.name = event.getName();
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
    public List<Place> getPlaces() {
        return places;
    }
    public void addPlace(Place place) {
        this.places.add(place);
    }
    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
