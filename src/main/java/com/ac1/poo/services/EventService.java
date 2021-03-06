package com.ac1.poo.services;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.ac1.poo.dto.TicketsConsultaDTO;
import com.ac1.poo.entities.Admin;
import com.ac1.poo.entities.Attend;
import com.ac1.poo.entities.Event;
import com.ac1.poo.entities.Place;
import com.ac1.poo.entities.RemoveTicket;
import com.ac1.poo.entities.Ticket;
import com.ac1.poo.entities.TicketAttendxType;
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
        Event event = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento n??o est?? cadastrado!"));
        return new EventDTO(event);
    }

    public EventDTO insert(EventInsertDTO event){
        Event entity = new Event(event);
        Optional<Admin> op = adminRepo.findById(event.getAdminId());
        Admin owner = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador n??o est?? cadastrado!"));
        if(event.getEnd_date().isBefore(event.getStart_date()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Data de fim menor que a data de inicio!");
        }
        if(event.getStart_date().isBefore(java.time.LocalDate.now()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Data do evento anterior ?? data atual!");
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
            if(repo.getOne(id).getTickets().size()!=0)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "N??o ?? poss??vel excluir o evento, pois ele possui tickets! ");
            }
            else
            {
                repo.deleteById(id);
            }
        }
        catch(EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
    }

    @Transactional
    public Boolean addLocal(long id, long idLocal) {
        Optional<Event> op = repo.findById(id);
        Event event = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento n??o est?? cadastrado!"));
        Optional<Place> op2 = placeRepo.findById(idLocal);
        Place place = op2.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Place n??o est?? cadastrado!"));
        for(Event e: repo.findAll())
        {   
            for (Place p:e.getPlaces())
            {
                if(p.getId() == idLocal)
                {

                    if(event.getEnd_date().isAfter(e.getStart_date()) && event.getEnd_date().isBefore(e.getEnd_date()))
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Local do evento j?? alugado neste per??odo");
                    }
                   
                    if(event.getStart_date().isAfter(e.getStart_date()) && event.getStart_date().isBefore(e.getEnd_date()))
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Local do evento j?? alugado neste per??odo");
                    }
                    if(event.getStart_date().isEqual(e.getStart_date()) || event.getEnd_date().isEqual(e.getEnd_date()))
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Local do evento j?? alugado neste per??odo" );
                    }
                    if(event.getStart_date().isEqual(e.getEnd_date()) || event.getEnd_date().isEqual(e.getStart_date()))
                    {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Local do evento j?? alugado neste per??odo" );
                    }
                }
            }
        }
        event.addPlace(place);
        return true;
    }
    @Transactional
    public Boolean removeLocal(long id, long idLocal) {
        Optional<Event> op = repo.findById(id);
        Event event = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento n??o est?? cadastrado!"));
        Optional<Place> op2 = placeRepo.findById(idLocal);
        Place place = op2.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Place n??o est?? cadastrado!"));
        event.removePlace(place);
        return true;
    }

    public TicketDTO insertTicket(@Valid TicketInsertDTO ticketDTO, Long id) {
        Optional<Event> op = repo.findById(id);
        Event event = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento n??o est?? cadastrado!"));
        Optional<Attend> op2 = attendRepo.findById(ticketDTO.getAttendId());
        Attend attend = op2.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend n??o est?? cadastrado!"));

        if(LocalDate.now().isAfter(event.getEnd_date())&&LocalTime.now().isAfter(event.getEnd_time()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"O evento j?? acabou!" );


        List<Ticket> tickets = new ArrayList<>();
        tickets = event.getTickets();
        Integer counter = 0;

        if(ticketDTO.getType()==TicketType.PAGO){
            
            for(Ticket t : tickets){
                if (t.getType()==TicketType.PAGO)
                    counter++;
            }
            if(counter>=event.getAmountPayedTickets()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tickets pagos est??o esgotados!" );
            }
        
        }
        else{

            for(Ticket t : tickets){
                if (t.getType()==TicketType.GRATUITO)
                    counter++;
            }
            if(counter>=event.getAmountFreeTickets()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tickets gratuitos est??o esgotados!" );
            }

        }

        tickets = attend.getTickets();
        for(Ticket t : tickets){
            if (t.getEvent().getId()==id)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Visitante j?? possui ticket para o evento!" );
        }

        Ticket entity = new Ticket(ticketDTO);

        if(ticketDTO.getType()==TicketType.PAGO){
            if(attend.getBalance()<event.getPriceTicket()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Visitante n??o possui cr??ditos suficientes!" );
            }
            else{
                attend.setBalance(attend.getBalance() - event.getPriceTicket());
                entity.setPrice(event.getPriceTicket());
            }
        } 
        else{
            entity.setPrice(0.0);
        }

        entity.setDate(LocalDate.now());
        entity.setType(ticketDTO.getType());

        attend.addTickets(entity);
        event.addTickets(entity);
        
        ticketRepo.save(entity);
        attendRepo.save(attend);
        repo.save(event);

        return new TicketDTO(entity);
    }

    public void deleteTicket(long id, RemoveTicket removeTicket) {
        long idAttendee = removeTicket.getIdAttendee();
        Optional<Event> op2 = repo.findById(id);
        Event event = op2.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento n??o est?? cadastrado!"));
        Optional<Attend> op3 = attendRepo.findById(idAttendee);
        Attend attend = op3.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Attend n??o est?? cadastrado!"));
        long variavelTicket = 0;
        for(Ticket t: repo.getOne(id).getTickets()){
            if(idAttendee == t.getAttend().getId())
            {
                variavelTicket = t.getId();               
            }
        }
        long idTicket = variavelTicket;
        Optional<Ticket> op = ticketRepo.findById(idTicket);
        Ticket ticket = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket n??o existe!"));

        if(LocalDate.now().isAfter(event.getStart_date())&&LocalTime.now().isAfter(event.getStart_time()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"N??o ?? poss??vel devolver um ingresso depois que o evento come??ou!" );

        if(ticket.getType()==TicketType.PAGO)
            attend.setBalance(attend.getBalance()+ticket.getPrice());

        event.removeTickets(ticket);
        attend.removeTickets(ticket);
        
        ticketRepo.deleteById(idTicket);

        attendRepo.save(attend);
        repo.save(event);
    }
    public TicketsConsultaDTO getTickets(long id){
        Optional<Event> op = repo.findById(id);
        Event event = op.orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento n??o est?? cadastrado!"));
     
        List<Ticket> tickets = new ArrayList<>();
        tickets = event.getTickets();
        Integer counterPayedTickets =0, counterFreeTickets=0;
        TicketAttendxType attendxType;
        List<TicketAttendxType> attendxTypeList = new ArrayList<>();
        for(Ticket t : tickets){
            attendxType = new TicketAttendxType(t.getAttend().getName(),t.getType());
            attendxTypeList.add(attendxType);
            if (t.getType()==TicketType.PAGO)
            {
                counterPayedTickets++;
            }
            else
            {
                counterFreeTickets++;
            }
        }
        return new TicketsConsultaDTO(event.getAmountPayedTickets(),event.getAmountFreeTickets(),counterPayedTickets,counterFreeTickets,attendxTypeList);
    } 
}
