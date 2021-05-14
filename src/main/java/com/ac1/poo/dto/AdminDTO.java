package com.ac1.poo.dto;

import com.ac1.poo.entities.Admin;

public class AdminDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    public AdminDTO() {
    }
    public AdminDTO(Long id, String name, String email, String phoneNumber) {
        setId(id);
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);   
    }

    public AdminDTO(Admin admin){
        this.id = admin.getId();
        this.name = admin.getName();
        this.email = admin.getEmail();
        this.phoneNumber = admin.getPhoneNumber();
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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
