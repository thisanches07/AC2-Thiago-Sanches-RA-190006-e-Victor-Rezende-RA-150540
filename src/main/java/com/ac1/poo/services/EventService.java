package com.ac1.poo.services;

import java.util.List;
import java.util.Optional;

import com.ac1.poo.dto.EventDTO;
import com.ac1.poo.entities.Event;
import com.ac1.poo.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {
    @Autowired
    private EventRepository repo;

    public Event fromDTO(EventDTO objDTO){
        Event event = new Event();
        event.setName(objDTO.getName());
        event.setDescription(objDTO.getDescription());
        event.setPlace(objDTO.getPlace());
        event.setStart_date(objDTO.getStart_date());
        event.setEnd_date(objDTO.getEnd_date());
        event.setStart_time(objDTO.getStart_time());
        event.setEnd_time(objDTO.getEnd_time());
        event.setEmail_contact(objDTO.getEmail_contact());
        return event;
    }
    public Event save(Event event)
    {
        return repo.save(event);
    }
    public List<Event> getEvents(){
       
        return repo.getEvents();
    }


    public Event getEventById(long id) {
        Optional<Event> op = repo. getEventById(id);
        return op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não está cadastrado!"));
    }
    public Event update(Event event)
    {
        getEventById(event.getId());
        return repo.update(event);
    }
}
