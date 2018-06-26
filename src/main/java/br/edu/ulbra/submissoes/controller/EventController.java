package br.edu.ulbra.submissoes.controller;

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

@RestController
@RequestMapping("evento")
public class EventController {

    private EventService eventService;
    private EventRepository eventRepository;
    private UserService userService;
    private SecurityService securityService;

    @Autowired
    private void securityService(EventService eventService){
        this.eventService = eventService;
    }

    @Autowired
    private void securityService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Autowired
    private void userService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    private void securityService(SecurityService securityService){
        this.securityService = securityService;
    }

    @GetMapping
    @ApiOperation(value="Página que lista todos os eventos que o usuário criou.")
    public ModelAndView listUserEvents(){
        ModelAndView  view = new ModelAndView("event");
        view.addObject("eventos", eventRepository.findAll());
        return view;
    }

    @GetMapping("/{eventId}")
    public ModelAndView showEvent(@PathVariable("eventId") Long eventId){
        Event event = eventRepository.findOne(eventId);

        if(event == null)
            return new ModelAndView("/event_notfound");

        ModelAndView  view = new ModelAndView("edit");
        view.addObject("evento", event);
        return view;
    }

    @GetMapping("/{eventId}/delete")
    @ApiOperation(value="Exclui um evento e redireciona para a página de listagem de eventos do usuário.")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        return "Excluir o evento " + eventId;
    }

    @PostMapping("/{eventId}")
    @ApiOperation(value="Atualiza os detalhes de um evento e redireciona para a página de detalhes do evento.")
    public String updateEvent(@PathVariable("eventId") Long eventId){
        return "Alterar o evento " + eventId;
    }

    @GetMapping("/cadastro")
    @ApiOperation(value="Página que exibe o formulário de cadastro de usuário (apenas se não está logado).")
    public ModelAndView showEventForm(EventInput event){

        ModelAndView mv = new ModelAndView("event/register");
        mv.addObject("event", event);
        return mv;

    }

    @PostMapping("/cadastro")
    @ApiOperation(value="Página que cadsatra um novo usuário (apenas se não está logado) e redireciona para a página inicial do site.")
    public ModelAndView insertEvent(EventInput eventInput, BindingResult bindingResult){

        ModelAndView mv;

        try {
            User user = userService.findById(Long.valueOf(1));
            eventRepository.save(new Event(eventInput, user));
            mv = new ModelAndView("redirect:/evento");
        } catch (Exception e){
            mv = this.showEventForm(eventInput);
            mv.addObject("error", e.getMessage());
        }
        return mv;
    }
}
