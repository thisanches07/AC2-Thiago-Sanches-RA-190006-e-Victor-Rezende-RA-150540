package com.ac1.poo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.ac1.poo.dto.AdminDTO;
import com.ac1.poo.dto.AdminInsertDTO;
import com.ac1.poo.dto.AdminUpdateDTO;
import com.ac1.poo.entities.Admin;
import com.ac1.poo.repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repo;

    public Page<AdminDTO> getAdmins(PageRequest pageRequest) {
        
            Page<Admin> list = repo.find(pageRequest);
            return list.map(e -> new AdminDTO(e) );
    }

    public AdminDTO getAdminById(long id) {
        Optional<Admin> op = repo.findById(id);
        Admin admin = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin não está cadastrado!"));
        return new AdminDTO(admin);
    }

    public AdminDTO insert(AdminInsertDTO admin){
        Admin entity = new Admin(admin);
        entity = repo.save(entity);
        return new AdminDTO(entity);
    }

    public AdminDTO update(AdminUpdateDTO adminUpdateDTO,long id)
    {
        try
        {
            Admin admin = repo.getOne(id);
            admin.setName(adminUpdateDTO.getName());   
            admin.setEmail(adminUpdateDTO.getEmail());
            admin.setPhoneNumber(adminUpdateDTO.getPhoneNumber());
            admin = repo.save(admin);         
            return new AdminDTO(admin);
        }
        catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
        }
    } 

    public List<AdminDTO> toDTOList(List<Admin> list) {

        List<AdminDTO> listDTO = new ArrayList<>();

        for (Admin a : list) {
            AdminDTO admin = new AdminDTO(a.getId(), a.getName(),a.getEmail(),a.getPhoneNumber());
            listDTO.add(admin);
        }
        return listDTO;
    }

    public void delete(Long id){
        try{
            repo.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
        }
    }
}
