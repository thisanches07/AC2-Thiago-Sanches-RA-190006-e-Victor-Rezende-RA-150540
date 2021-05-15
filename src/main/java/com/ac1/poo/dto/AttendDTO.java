package com.ac1.poo.dto;

import com.ac1.poo.entities.Attend;

public class AttendDTO {
    private Long id;
    private String name;
    private String email;
    private Double balance;
    public AttendDTO() {
    }
    public AttendDTO(Long id, String name, String email, Double balance) {
        setId(id);
        setName(name);
        setEmail(email);
        setPhoneNumber(balance);   
    }

    public AttendDTO(Attend attend){
        this.id = attend.getId();
        this.name = attend.getName();
        this.email = attend.getEmail();
        this.balance = attend.getBalance();
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Double getBalance() {
        return balance;
    }
    public void setPhoneNumber(Double balance) {
        this.balance = balance;
    }
    
}
