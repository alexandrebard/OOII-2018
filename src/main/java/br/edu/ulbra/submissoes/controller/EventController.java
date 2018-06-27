package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.exception.EventException;
import br.edu.ulbra.submissoes.input.EventInput;
import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.repository.EventRepository;
import br.edu.ulbra.submissoes.repository.UserRepository;
import br.edu.ulbra.submissoes.service.EventService;
import br.edu.ulbra.submissoes.service.SecurityService;
import br.edu.ulbra.submissoes.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("evento")
public class EventController {

    private final EventService eventService;
    private final SecurityService securityService;

    @Autowired
    public EventController(
            EventService eventService,
            SecurityService securityService
    ){
        this.eventService = eventService;
        this.securityService = securityService;
    }

    @GetMapping
    @ApiOperation(value="Página que lista todos os eventos que o usuário criou.")
    public ModelAndView listUserEvents(){
        ModelAndView  view = new ModelAndView("event/evento");
        view.addObject("eventos", securityService.findLoggedInUser().getEvents());
        return view;
    }

    @GetMapping("/{eventId}")
    public ModelAndView showEvent(@PathVariable("eventId") Long eventId){

        Event event;
        ModelAndView mv;

        try {
            event = eventService.findById(eventId);
            mv = new ModelAndView("event/edit");
            mv.addObject("evento", event);
        } catch (EventException e){
            mv = new ModelAndView("/event_notfound");
            mv.addObject("error", e.getMessage());
        }

        return mv;
    }

    @GetMapping("/{eventId}/delete")
    @ApiOperation(value="Exclui um evento e redireciona para a página de listagem de eventos do usuário.")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        return "Excluir o evento " + eventId;
    }

    @PostMapping("/{eventId}")
    @ApiOperation(value="Atualiza os detalhes de um evento e redireciona para a página de detalhes do evento.")
    public ModelAndView updateEvent(@PathVariable("eventId") Long eventId, EventInput eventInput){

        ModelAndView mv;
        try {
            eventInput.setId(eventId);
            eventService.save(eventInput, true);
            mv = new ModelAndView("redirect:/evento");
        } catch (EventException e){
            mv = this.showEvent(eventId);
            mv.addObject("error", e.getMessage());
        }
        return mv;
    }

    @GetMapping("/cadastro")
    @ApiOperation(value="Página que exibe o formulário de cadastro de usuário (apenas se não está logado).")
    public ModelAndView showEventForm(EventInput event){

        ModelAndView mv = new ModelAndView("event/register");
        mv.addObject("event", event);
        return mv;

    }

    @PostMapping
    @ApiOperation(value="Página que cadsatra um novo evento (apenas se está logado) e redireciona para a lista de eventos.")
    public ModelAndView insertEvent(EventInput eventInput, BindingResult bindingResult){

        ModelAndView mv;

        try {
            eventService.save(eventInput, false);
            mv = new ModelAndView("redirect:/evento");
        } catch (Exception e){
            mv = this.showEventForm(eventInput);
            mv.addObject("error", e.getMessage());
        }

        return mv;
    }
}
