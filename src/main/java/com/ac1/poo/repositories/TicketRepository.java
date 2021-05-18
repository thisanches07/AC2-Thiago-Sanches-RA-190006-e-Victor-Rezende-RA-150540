package com.ac1.poo.repositories;



import java.time.LocalDate;

import com.ac1.poo.entities.Ticket;
import com.ac1.poo.entities.TicketType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository <Ticket,Long>{

    @Query("SELECT t FROM Ticket t " +
            "WHERE" +
             "(t.type LIKE :type) AND " +
             "(t.date > :date) AND "+
             "(t.price = :price) "
    )
    public Page<Ticket> find(Pageable pagerequest,TicketType type, LocalDate date, Double price);
}

