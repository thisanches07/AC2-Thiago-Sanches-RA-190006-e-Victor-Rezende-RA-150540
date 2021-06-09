package com.ac1.poo.entities;

public class TicketAttendxType {
    String name;
    TicketType type;


    public TicketAttendxType(String name,TicketType type){
        setName(name);
        setType(type);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public TicketType getType() {
        return type;
    }
    public void setType(TicketType type) {
        this.type = type;
    }
}