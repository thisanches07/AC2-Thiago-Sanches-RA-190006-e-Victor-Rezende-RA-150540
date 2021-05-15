package com.ac1.poo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.ac1.poo.dto.AdminInsertDTO;

@Entity
@Table(name="TB_ADMIN")
@PrimaryKeyJoinColumn(name="BASEUSER_ID")
public class Admin extends BaseUser {

    private String phoneNumber;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


    @OneToMany(mappedBy = "Admin")
    private List<Event> events = new ArrayList<>();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    public Admin(AdminInsertDTO admin){
        this.name = admin.getName();
        this.email = admin.getEmail();
        this.phoneNumber = admin.getPhoneNumber();
    }
    
}
