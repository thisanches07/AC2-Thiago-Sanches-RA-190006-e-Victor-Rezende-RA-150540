package com.ac1.poo.controllers;

import java.net.URI;
import java.util.List;


import com.ac1.poo.dto.EventDTO;
import com.ac1.poo.dto.EventInsertDTO;
import com.ac1.poo.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService service;
    
    @GetMapping
    public ResponseEntity<Page<EventDTO>> getEvents(
      @RequestParam(value = "page",         defaultValue = "0")Integer page,          //pagina corrente
      @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage, //linhas por pagina
      @RequestParam(value = "direction",    defaultValue = "ASC") String direction,   //direcao
      @RequestParam(value = "orderBy",      defaultValue = "id") String orderBy,      //ordenacao
      @RequestParam(value = "name",      defaultValue = "") String name
    ){

      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
      Page<EventDTO> list = service.getEvents(pageRequest,name);
      return ResponseEntity.ok(list);
        
    }

    
 @PostMapping()
 public ResponseEntity<EventDTO> salvar(@Validated @RequestBody EventInsertDTO eventDTO) {
     EventDTO event = service.insert(eventDTO);
     URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(event.getId()).toUri();
     //UriComponents uriComponents = builder.path(request.getRequestURI()+ "/" +newEvent.getId()).build();                                       
      return ResponseEntity.created(uri).body(event);                             
                                        }
@GetMapping("/{id}")
public ResponseEntity<EventDTO> getEventById(@PathVariable long id){
  EventDTO event = service.getEventById(id);
  return ResponseEntity.ok(event);
}
@PutMapping("/{id}")
public ResponseEntity<EventDTO> update(@RequestBody EventDTO eventUpdateDTO,
                                    @PathVariable long id)
{
  //Event event = service.fromDTO(eventDTO);
  //event.setId(id);
  //event = service.update(event);
  //return ResponseEntity.ok(event); 
  EventDTO event = service.update(eventUpdateDTO, id);
  return ResponseEntity.ok().body(event);
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> remover(@PathVariable long id)
{
    service.delete(id);
    return ResponseEntity.noContent().build();
}
}



