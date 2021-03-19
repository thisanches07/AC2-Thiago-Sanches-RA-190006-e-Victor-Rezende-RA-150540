package com.ac1.poo.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.ac1.poo.entities.Event;

import org.springframework.stereotype.Component;
@Component
public class EventRepository {
        private List<Event> events;
        private long nextId;

    @PostConstruct
    public void init(){
        nextId = 1;
        events = new ArrayList<Event>();
    }
    
    public List<Event> getevents()
    {
        return events;
    }
    public Optional<Event> getEventById(long id) {
        for(Event cont : events)
            if(cont.getId() == id)
                return Optional.of(cont);
        return Optional.empty();        
    }
    public Event save(Event event) {
        event.setId(nextId);
        event.setName(event.getName());
        event.setDescription(event.getDescription());
        event.setPlace(event.getPlace());
        event.setStart_date(event.getStart_date());
        event.setEnd_date(event.getEnd_date());
        event.setStart_time(event.getStart_time());
        event.setEnd_time(event.getEnd_time());
        event.setEmail_contact(event.getEmail_contact());
        events.add(event);
        nextId++;
        return event;
    }

    public List<Event> getEvents() {
        return events;
    }
    public Event update(Event event)
    {
        Event aux = getEventById(event.getId()).get();
        if(aux!=null)
        {
            aux.setDescription(event.getDescription());
            aux.setStart_date(event.getStart_date());
            aux.setStart_time(event.getStart_time());
            aux.setEnd_date(event.getEnd_date());
            aux.setEnd_time(event.getEnd_time());
            aux.setPlace(event.getPlace());
        }
        return aux;
    }
}
