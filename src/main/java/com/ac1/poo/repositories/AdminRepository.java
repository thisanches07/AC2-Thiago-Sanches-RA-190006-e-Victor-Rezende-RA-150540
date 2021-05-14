package com.ac1.poo.repositories;

import com.ac1.poo.entities.Admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository <Admin,Long>{

    @Query("SELECT a FROM Admin a " +
            "WHERE" +
             "(a.name LIKE CONCAT('%', :name,'%')) AND " +
             "(a.place LIKE CONCAT('%', :email,'%')) AND "+
             "(a.phoneNumber LIKE CONCAT('%', :phoneNumber,'%')) "
    )
    public Page<Admin> find(Pageable pagerequest,String name,String email, String phoneNumber);
}

