package com.ac1.poo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="TB_ATTEND")
@PrimaryKeyJoinColumn(name="BASEUSER_ID")
public class Attend extends BaseUser {

    private Double balance;

    @OneToMany( cascade = CascadeType.PERSIST)
    @JoinColumn(name="ATTENDEE_ID")
    private List<Ticket> tickets = new ArrayList<>();

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
}