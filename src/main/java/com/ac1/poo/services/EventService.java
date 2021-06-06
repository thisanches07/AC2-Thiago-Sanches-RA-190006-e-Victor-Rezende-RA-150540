package com.ac1.poo.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.ac1.poo.dto.EventDTO;
import com.ac1.poo.dto.EventInsertDTO;
import com.ac1.poo.dto.EventUpdateDTO;
import com.ac1.poo.dto.TicketDTO;
import com.ac1.poo.dto.TicketInsertDTO;
import com.ac1.poo.entities.Admin;
import com.ac1.poo.entities.Attend;
import com.ac1.poo.entities.Event;
import com.ac1.poo.entities.Place;
import com.ac1.poo.entities.Ticket;
import com.ac1.poo.entities.TicketType;
import com.ac1.poo.repositories.EventRepository;
import com.ac1.poo.repositories.PlaceRepository;
import com.ac1.poo.repositories.TicketRepository;
import com.ac1.poo.repositories.AdminRepository;
import com.ac1.poo.repositories.AttendRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {

    @Autowired
    private EventRepository repo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private AttendRepository attendRepo;

    @Autowired
    private PlaceRepository placeRepo;

    @Autowired
    private TicketRepository ticketRepo;
    
    public Page<EventDTO> getEvents(PageRequest pageRequest,String name, String description, String start_date) {
        if(start_date.isEmpty()){
            Page<Event> list = repo.find(pageRequest,name,description);
            return list.map(e -> new EventDTO(e) );
        }
        else{
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(start_date,formato);
            Page<Event> list = repo.find(pageRequest,name,description,date);
            return list.map(e -> new EventDTO(e) );
        }
    }

    public EventDTO getEventById(long id) {
        Optional<Event> op = repo.findById(id);
        Event event = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não está cadastrado!"));
        return new EventDTO(event);
    }

    public EventDTO insert(EventInsertDTO event){
        Event entity = new Event(event);
        Optional<Admin> op = adminRepo.findById(event.getAdminId());
        Admin owner = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador não está cadastrado!"));
        if(event.getEnd_date().isBefore(event.getStart_date()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Data de fim menor que a data de inicio!");
        }
        if(event.getStart_date().isBefore(java.time.LocalDate.now()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Data do evento anterior à data atual!");
        }
        else{
            entity.setAdmin(owner);
            entity = repo.save(entity);
            return new EventDTO(entity);
        }
    }

    public EventDTO update(EventUpdateDTO eventUpdateDTO,long id){
        try{
            Event event = repo.getOne(id);
            event.setDescription(eventUpdateDTO.getDescription());
            event.setStart_date(eventUpdateDTO.getStart_date());
            event.setStart_time(eventUpdateDTO.getStart_time());
            event.setEnd_date(eventUpdateDTO.getEnd_date());
            event.setEnd_time(eventUpdateDTO.getEnd_time());
            event.setAmountFreeTickets(eventUpdateDTO.getAmountFreeTickets());
            event.setAmountPayedTickets(eventUpdateDTO.getAmountPayedTickets());
            event.setPriceTicket(eventUpdateDTO.getPriceTicket());
            event = repo.save(event);
            return new EventDTO(event);
        }
        catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
    }

    public List<EventDTO> toDTOList(List<Event> list) {
        List<EventDTO> listDTO = new ArrayList<>();
        for (Event e : list) {
            EventDTO event = new EventDTO(e.getId(), e.getName(),e.getDescription(),e.getStart_date(),e.getEnd_date(),e.getStart_time(),e.getEnd_time(),e.getEmail_contact(),e.getAmountFreeTickets(),e.getAmountPayedTickets(),e.getPriceTicket());
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

    @Transactional
    public Boolean addLocal(long id, long idLocal) {
        Optional<Event> op = repo.findById(id);
        Event event = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não está cadastrado!"));
        Optional<Place> op2 = placeRepo.findById(idLocal);
        Place place = op2.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Place não está cadastrado!"));

        event.addPlace(place);

        return true;
    }

    public TicketDTO insertTicket(@Valid TicketInsertDTO ticketDTO, Long id) {
        Optional<Event> op = repo.findById(id);
        Event event = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não está cadastrado!"));
        Optional<Attend> op2 = attendRepo.findById(ticketDTO.getAttendId());
        Attend attend = op2.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend não está cadastrado!"));

        Ticket entity = new Ticket(ticketDTO);
        entity.setDate(LocalDate.now());
        if(ticketDTO.getType()==TicketType.PAGO)
            entity.setPrice(event.getPriceTicket());
        else{
            entity.setPrice(0.0);
        }
        entity.setType(ticketDTO.getType());

        attend.addTickets(entity);
        event.addTickets(entity);
        
        ticketRepo.save(entity);
        attendRepo.save(attend);
        repo.save(event);

        return new TicketDTO(entity);
    }
}
