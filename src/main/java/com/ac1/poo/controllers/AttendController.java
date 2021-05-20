package com.ac1.poo.controllers;

import java.net.URI;

import javax.validation.Valid;

import com.ac1.poo.dto.AttendDTO;
import com.ac1.poo.dto.AttendInsertDTO;
import com.ac1.poo.dto.AttendUpdateDTO;
import com.ac1.poo.services.AttendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/attendees")
public class AttendController {
    
    @Autowired
    private AttendService service;
    
    @GetMapping
    public ResponseEntity<Page<AttendDTO>> getAttend(
        @RequestParam(value = "page",         defaultValue = "0")Integer page,          //pagina corrente
        @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage, //linhas por pagina
        @RequestParam(value = "direction",    defaultValue = "ASC") String direction,   //direcao
        @RequestParam(value = "orderBy",      defaultValue = "id") String orderBy     //ordenacao
      ){
      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
      Page<AttendDTO> list = service.getAttendees(pageRequest);
      return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<AttendDTO> salvar(@Valid @RequestBody AttendInsertDTO attendDTO) {
    AttendDTO attend = service.insert(attendDTO);
     URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(attend.getId()).toUri();                                      
      return ResponseEntity.created(uri).body(attend);                             
                                        }
@GetMapping("/{id}")
public ResponseEntity<AttendDTO> getAttendById(@PathVariable long id){
    AttendDTO attend = service.getAttendeeById(id);
  return ResponseEntity.ok(attend);
}
@PutMapping("/{id}")
public ResponseEntity<AttendDTO> update(@Valid @RequestBody AttendUpdateDTO attendUpdateDTO,
                                    @PathVariable long id)
{
    AttendDTO attend = service.update(attendUpdateDTO, id);
  return ResponseEntity.ok().body(attend);
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> remover(@PathVariable long id)
{
    service.delete(id);
    return ResponseEntity.noContent().build();
}
}
