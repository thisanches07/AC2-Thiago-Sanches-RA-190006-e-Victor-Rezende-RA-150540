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

    @OneToMany(mappedBy = "Admin")
    private List<Event> events = new ArrayList<>();

    

    
    public Admin(AdminInsertDTO admin){
        super(admin.getName(),admin.getEmail());
        this.phoneNumber = admin.getPhoneNumber();
    }
    
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
