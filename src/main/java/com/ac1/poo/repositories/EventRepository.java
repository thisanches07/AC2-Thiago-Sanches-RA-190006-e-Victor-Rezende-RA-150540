package com.ac1.poo.repositories;



import java.time.LocalDate;

import com.ac1.poo.entities.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository <Event,Long>{

    @Query("SELECT e FROM Event e " +
            "WHERE" +
             "(e.name LIKE CONCAT('%', :name,'%')) AND " +
             "(e.place LIKE CONCAT('%', :place,'%')) AND "+
             "(e.description LIKE CONCAT('%', :description,'%')) AND " +
             "(e.start_date >= :start_date)"
    )
    public Page<Event> find(Pageable pagerequest,String name,String place, String description, LocalDate start_date);
}
