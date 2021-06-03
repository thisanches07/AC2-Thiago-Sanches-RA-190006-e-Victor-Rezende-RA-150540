package com.ac1.poo.repositories;

import java.util.Optional;

import com.ac1.poo.entities.Admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository <Admin,Long>{

    @Query("SELECT a FROM Admin a ")
    public Page<Admin> find(Pageable pagerequest);

    @Query("SELECT a FROM Admin a WHERE a.email LIKE :email")
    public Optional<Admin> findEmail(String email);
}

