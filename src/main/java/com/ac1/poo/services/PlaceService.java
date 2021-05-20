package com.ac1.poo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.ac1.poo.dto.PlaceDTO;
import com.ac1.poo.dto.PlaceInsertDTO;
import com.ac1.poo.dto.PlaceUpdateDTO;
import com.ac1.poo.entities.Place;
import com.ac1.poo.repositories.PlaceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository repo;

    public Page<PlaceDTO> getPlaces(PageRequest pageRequest) {
        Page<Place> list = repo.find(pageRequest);
        return list.map(e -> new PlaceDTO(e) );
    }

    public PlaceDTO getPlaceById(long id) {
        Optional<Place> op = repo.findById(id);
        Place place = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Place não está cadastrado!"));
        return new PlaceDTO(place);
    }

    public PlaceDTO insert(PlaceInsertDTO place){
        Place entity = new Place(place);
        entity = repo.save(entity);
        return new PlaceDTO(entity);
    }

    public PlaceDTO update(PlaceUpdateDTO placeUpdateDTO,long id)
    {
        try
        {
            Place place = repo.getOne(id);
            place.setName(placeUpdateDTO.getName());
            place.setAddress(placeUpdateDTO.getAddress());
            place = repo.save(place);         
            return new PlaceDTO(place);
        }
        catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
          }
    }

    public List<PlaceDTO> toDTOList(List<Place> list) {
        List<PlaceDTO> listDTO = new ArrayList<>();
        for (Place a : list) {
            PlaceDTO place = new PlaceDTO(a.getId(), a.getName(),a.getAddress());
            listDTO.add(place);
        }
        return listDTO;
    }

    public void delete(Long id){
        try{
            repo.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
        }
    }
}
