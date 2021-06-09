package com.ac1.poo.dto;

import java.util.ArrayList;
import java.util.List;

import com.ac1.poo.entities.TicketAttendxType;

public class TicketsConsultaDTO {
    Long amountPayedTickets;
    Long amountFreeTickets;
    Integer amountPurchasedPayedTickets;
    Integer amountPurchasedFreeTickets;
    List<TicketAttendxType> tickets = new ArrayList<>();


public TicketsConsultaDTO(Long amountPayedTickets,Long amountFreeTickets,Integer amountPurchasedPayedTickets,Integer amountPurchasedFreeTickets,List<TicketAttendxType> tickets){
    setAmountPayedTickets(amountPayedTickets);
    setAmountFreeTickets(amountFreeTickets);
    setAmountPurchasedPayedTickets(amountPurchasedPayedTickets);
    setAmountPurchasedFreeTickets(amountPurchasedFreeTickets);
    setTickets(tickets);
}


public Long getAmountPayedTickets() {
    return amountPayedTickets;
}


public void setAmountPayedTickets(Long amountPayedTickets) {
    this.amountPayedTickets = amountPayedTickets;
}


public Long getAmountFreeTickets() {
    return amountFreeTickets;
}


public void setAmountFreeTickets(Long amountFreeTickets) {
    this.amountFreeTickets = amountFreeTickets;
}


public Integer getAmountPurchasedPayedTickets() {
    return amountPurchasedPayedTickets;
}


public void setAmountPurchasedPayedTickets(Integer amountPurchasedPayedTickets) {
    this.amountPurchasedPayedTickets = amountPurchasedPayedTickets;
}


public Integer getAmountPurchasedFreeTickets() {
    return amountPurchasedFreeTickets;
}


public void setAmountPurchasedFreeTickets(Integer amountPurchasedFreeTickets) {
    this.amountPurchasedFreeTickets = amountPurchasedFreeTickets;
}


public List<TicketAttendxType> getTickets() {
    return tickets;
}


public void setTickets(List<TicketAttendxType> tickets) {
    this.tickets = tickets;
}


}

