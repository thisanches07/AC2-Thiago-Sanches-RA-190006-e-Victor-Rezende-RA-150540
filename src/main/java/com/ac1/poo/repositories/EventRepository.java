package com.ac1.poo.repositories;


import com.ac1.poo.entities.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository <Event,Long>{

}
