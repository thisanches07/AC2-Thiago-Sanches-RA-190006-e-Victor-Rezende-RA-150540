package com.ac1.poo.repositories;


import com.ac1.poo.entities.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository <Event,Long>{
    /*    private List<Event> events;
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
            if(event.getDescription()!=null)
                aux.setDescription(event.getDescription());
            if(event.getStart_date()!=null)    
                aux.setStart_date(event.getStart_date());
            if(event.getStart_time()!=null)
                aux.setStart_time(event.getStart_time());
            if(event.getEnd_date()!=null)    
                aux.setEnd_date(event.getEnd_date());
            if(event.getEnd_time()!=null)    
                aux.setEnd_time(event.getEnd_time());
            if(event.getPlace()!=null)
                aux.setPlace(event.getPlace());
        }
        return aux;
    }
    public void delete(Event event)
    {
            events.remove(event);
    }*/
}
