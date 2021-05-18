package com.ac1.poo.repositories;


import com.ac1.poo.entities.Place;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository <Place,Long>{

    @Query("SELECT p FROM Place p ")
    public Page<Place> find(Pageable pagerequest);
}

