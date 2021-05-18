package com.ac1.poo.controllers;

import java.net.URI;

import com.ac1.poo.dto.PlaceDTO;
import com.ac1.poo.dto.PlaceInsertDTO;
import com.ac1.poo.services.PlaceService;

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
@RequestMapping("/places")
public class PlaceController {
    @Autowired
    private PlaceService service;
    
    @GetMapping
    public ResponseEntity<Page<PlaceDTO>> getPlace(
      @RequestParam(value = "page",         defaultValue = "0")Integer page,          //pagina corrente
      @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage, //linhas por pagina
      @RequestParam(value = "direction",    defaultValue = "ASC") String direction,   //direcao
      @RequestParam(value = "orderBy",      defaultValue = "id") String orderBy       //ordenacao
      ){
     
      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
      Page<PlaceDTO> list = service.getPlaces(pageRequest);
      return ResponseEntity.ok(list);
        
    }
    @PostMapping()
 public ResponseEntity<PlaceDTO> salvar(@Validated @RequestBody PlaceInsertDTO placeDTO) {
  PlaceDTO place = service.insert(placeDTO);
     URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(place.getId()).toUri();                                      
      return ResponseEntity.created(uri).body(place);                             
                                        }
@GetMapping("/{id}")
public ResponseEntity<PlaceDTO> getPlaceById(@PathVariable long id){
  PlaceDTO event = service.getPlaceById(id);
  return ResponseEntity.ok(event);
}
@PutMapping("/{id}")
public ResponseEntity<PlaceDTO> update(@RequestBody PlaceDTO placeUpdateDTO,
                                    @PathVariable long id)
{
  PlaceDTO event = service.update(placeUpdateDTO, id);
  return ResponseEntity.ok().body(event);
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> remover(@PathVariable long id)
{
    service.delete(id);
    return ResponseEntity.noContent().build();
}
}
