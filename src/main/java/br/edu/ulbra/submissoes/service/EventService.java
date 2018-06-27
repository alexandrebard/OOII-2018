package br.edu.ulbra.submissoes.service;

import br.edu.ulbra.submissoes.exception.EventException;
import br.edu.ulbra.submissoes.input.EventInput;
import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.repository.EventRepository;
import liquibase.util.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final SecurityService securityService;

    @Autowired
    public EventService(
            EventRepository eventRepository,
            ModelMapper modelMapper,
            SecurityService securityService
    ){
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.securityService = securityService;
    }


    public Event save(EventInput eventInput, boolean isUpdate) throws EventException {

        if (eventInput == null){
            throw new EventException("Dados não informados");
        }
        if (StringUtils.isEmpty(eventInput.getName())){
            throw new EventException("Nome não informado");
        }
        if(eventInput.getStartingDate() == null){
            throw new EventException("Data de inicio não informada");
        }
        if(eventInput.getEndingDate() == null){
            throw new EventException("Data de fim não informada");
        }

        Date creationDate;
        Event event;
        if (isUpdate){
            event = this.findById(eventInput.getId());
            creationDate = event.getCreationDate();
        } else {
            event = new Event();
            creationDate = new Date();
            event.setUser(securityService.findLoggedInUser());
        }

        modelMapper.map(eventInput, event);

        event.setCreationDate(creationDate);

        return eventRepository.save(event);

    }
/*
    public Event update(EventInput eventInput) throws EventException {

        if (eventInput == null){
            throw new EventException("Dados não informados");
        }

        Event event = this.findById(eventInput.getId());

        eventInput.setCreationDate(event.getCreationDate());
        modelMapper.map(eventInput, event);
        return eventRepository.save(event);

    }
*/
    public Event findById(Long eventId) throws EventException {
        Event event = eventRepository.findOne(eventId);
        if (event == null )
            throw new EventException("Evento não encontrado");
        return event;
    }

    public void delete(Long eventId) throws EventException{
        Event event = findById(eventId);
        eventRepository.delete(event);
    }

    public EventInput loadToEdit(Long eventId) throws EventException{
        Event event = this.findById(eventId);
        return modelMapper.map(event, EventInput.class);
    }

}