package com.ac1.poo.repositories;

import com.ac1.poo.entities.Attend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendRepository extends JpaRepository <Attend,Long>{

    @Query("SELECT a FROM Admin a " +
            "WHERE" +
             "(a.name LIKE CONCAT('%', :name,'%')) AND " +
             "(a.email LIKE CONCAT('%', :email,'%')) AND "+
             "(a.phoneNumber = :balance) "
    )
    public Page<Attend> find(Pageable pagerequest,String name,String email, Double balance);
}

