package com.ac1.poo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.ac1.poo.dto.EventDTO;
import com.ac1.poo.dto.EventInsertDTO;
import com.ac1.poo.entities.Event;
import com.ac1.poo.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {

    @Autowired
    private EventRepository repo;

    /*public Event fromDTO(EventDTO objDTO){
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
    }*/
    public List<EventDTO> getEvents() {
        List<Event> list = repo.findAll();
        return toDTOList(list);
    }
    public Event save(Event event)
    {
        return repo.save(event);
    }
    /*public List<Event> getEvents(){
       
        return repo.findAll();
    }*/


    public EventDTO getEventById(long id) {
        Optional<Event> op = repo.findById(id);
        Event event = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não está cadastrado!"));
        return new EventDTO(event);
    }
    public EventDTO insert(EventInsertDTO event){
        Event entity = new Event(event);
        entity = repo.save(entity);
        return new EventDTO(entity);
    }
    public EventDTO update(EventDTO eventUpdateDTO,long id)
    {
        //getEventById(event.getId());
        // return repo.update(event);
        try
        {

            Event event = repo.getOne(id);
           // if(event!=null)
           // {
                if(event.getDescription()!=null)
                event.setDescription(eventUpdateDTO.getDescription());
                if(event.getStart_date()!=null)    
                event.setStart_date(eventUpdateDTO.getStart_date());
                if(event.getStart_time()!=null)
                event.setStart_time(eventUpdateDTO.getStart_time());
                if(event.getEnd_date()!=null)    
                event.setEnd_date(eventUpdateDTO.getEnd_date());
                if(event.getEnd_time()!=null)    
                event.setEnd_time(eventUpdateDTO.getEnd_time());
                if(event.getPlace()!=null)
                event.setPlace(eventUpdateDTO.getPlace());
                event = repo.save(event);
           // }
            return new EventDTO(event);
        }
        catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
          }
    }
    public void remover(long id)
    {     try{

                repo.deleteById(id);       
            }
            catch(EmptyResultDataAccessException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
            }
    } 
    public List<EventDTO> toDTOList(List<Event> list) {

        List<EventDTO> listDTO = new ArrayList<>();

        for (Event e : list) {
            EventDTO event = new EventDTO(e.getId(), e.getName(),e.getDescription(),e.getPlace(),e.getStart_date(),e.getEnd_date(),e.getStart_time(),e.getEnd_time(),e.getEmail_contact());
            listDTO.add(event);
        }
        

        return listDTO;
    }
    public void delete(Long id){
        try{
            repo.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
    }
}
