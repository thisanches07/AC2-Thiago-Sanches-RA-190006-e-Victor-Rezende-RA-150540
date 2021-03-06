package com.ac1.poo.entities;

import java.util.ArrayList;
import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.ac1.poo.dto.AttendInsertDTO;

@Entity
@Table(name="TB_ATTEND")
@PrimaryKeyJoinColumn(name="BASEUSER_ID")
public class Attend extends BaseUser {

    private Double balance;

    @OneToMany
    @JoinColumn(name="ATTENDEE_ID")
    private List<Ticket> tickets = new ArrayList<>();

    public Attend(){
    }

    public Attend(AttendInsertDTO attend){
        super(attend.getName(),attend.getEmail());
        this.balance = attend.getBalance();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTickets(Ticket tickets) {
        this.tickets.add(tickets);
    }

    public void removeTickets(Ticket ticket) {
        this.tickets.remove(ticket);
    }
    
}
