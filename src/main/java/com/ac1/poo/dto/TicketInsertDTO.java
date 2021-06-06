package com.ac1.poo.dto;

import javax.validation.constraints.NotNull;

import com.ac1.poo.entities.TicketType;

public class TicketInsertDTO {

    @NotNull(message="Ticket tem que ter um tipo")
    private TicketType type;

    @NotNull(message="Ticket tem que ter um dono")
    private Long attendId;
    

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    
    
   

    
}
