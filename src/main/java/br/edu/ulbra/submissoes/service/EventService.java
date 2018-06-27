package br.edu.ulbra.submissoes.service;

import br.edu.ulbra.submissoes.exception.EventException;
import br.edu.ulbra.submissoes.input.EventInput;
import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public EventService(EventRepository eventRepository, ModelMapper modelMapper){
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    public Event update(EventInput eventInput) throws EventException {

        if (eventInput == null){
            throw new EventException("Dados não informados");
        }

        Event event = this.findById(eventInput.getId());

        eventInput.setCreationDate(event.getCreationDate());
        modelMapper.map(eventInput, event);
        return eventRepository.save(event);

    }

    public Event findById(Long eventId) {
        return eventRepository.findOne(eventId);
    }

    public void delete(Long userId) throws EventException{
        Event user = findById(userId);
        eventRepository.delete(user);
    }

    public EventInput loadToEdit(Long userId) throws EventException{
        Event user = this.findById(userId);
        return modelMapper.map(user, EventInput.class);
    }

    public List<Event> findByUser(User user) {
        return eventRepository.findByUser(user.getId());
    }
}