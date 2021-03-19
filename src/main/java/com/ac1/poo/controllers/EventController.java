package com.ac1.poo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ac1.poo.dto.EventDTO;
import com.ac1.poo.entities.Event;
import com.ac1.poo.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService service;
    
    @GetMapping()
    public List<Event> getEvents(){
    
      return service.getEvents();
        
    }

    
 @PostMapping()
 public ResponseEntity<Event> salvar(@Validated @RequestBody EventDTO eventDTO,
                                        HttpServletRequest request,
                                        UriComponentsBuilder builder
                                        ) {
     Event event = service.fromDTO(eventDTO);
     Event newEvent = service.save(event);
     UriComponents uriComponents = builder.path(request.getRequestURI()+ "/" +newEvent.getId()).build();                                       
      return ResponseEntity.created(uriComponents.toUri()).build();                             
                                        }
@GetMapping("/{id}")
public ResponseEntity<Event> getEventById(@PathVariable long id){
  Event event = service.getEventById(id);
  return ResponseEntity.ok(event);
}
@PutMapping("/{id}")
public ResponseEntity<Event> update(@RequestBody EventDTO eventDTO,
                                    @PathVariable long id)
{
  Event event = service.fromDTO(eventDTO);
  event.setId(id);
  event = service.update(event);
  return ResponseEntity.ok(event); 
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> remover(@PathVariable long id)
{
    service.removerById(id);
    return ResponseEntity.noContent().build();
}
}



