package com.ac1.poo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.ac1.poo.dto.AttendDTO;
import com.ac1.poo.dto.AttendInsertDTO;
import com.ac1.poo.dto.AttendUpdateDTO;
import com.ac1.poo.entities.Attend;
import com.ac1.poo.repositories.AttendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AttendService {
    @Autowired
    private AttendRepository repo;



    public Page<AttendDTO> getAttendees(PageRequest pageRequest) {
        
        Page<Attend> list = repo.find(pageRequest);
        return list.map(e -> new AttendDTO(e) );
    }
    
    public AttendDTO getAttendeeById(long id) {
        Optional<Attend> op = repo.findById(id);
        Attend attend = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend não está cadastrado!"));
        return new AttendDTO(attend);
    }

    public AttendDTO insert(AttendInsertDTO attend){
        Optional<Attend> repetido = repo.findEmail(attend.getEmail());
        if( !repetido.isPresent()){
            Attend entity = new Attend(attend);
            entity = repo.save(entity);
            return new AttendDTO(entity);
        }
        else{
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Já existe um usuário com esse email!!!");
        }
        
        
    }

    public AttendDTO update(AttendUpdateDTO attendUpdateDTO,long id)
    {
        try
        {
            Attend attend = repo.getOne(id);
            attend.setName(attendUpdateDTO.getName());   
            attend.setEmail(attendUpdateDTO.getEmail());
            attend = repo.save(attend);         
            return new AttendDTO(attend);
        }
        catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found");
        }
    }

    public List<AttendDTO> toDTOList(List<Attend> list) {

        List<AttendDTO> listDTO = new ArrayList<>();

        for (Attend a : list) {
            AttendDTO attend = new AttendDTO(a.getId(), a.getName(),a.getEmail(),a.getBalance());
            listDTO.add(attend);
        }
        return listDTO;
    }

    public void delete(Long id){
        try{
                    if(repo.getOne(id).getTickets().size()!=0)
                    {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend possui tickets comprados!");
                    }
                    else
                    {
                        repo.deleteById(id);
                    }
                }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend not found");
        }
    }
}
