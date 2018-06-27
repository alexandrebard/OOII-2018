package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.exception.EventException;
import br.edu.ulbra.submissoes.input.EventInput;
import br.edu.ulbra.submissoes.input.SubmissionInput;
import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.model.Submission;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.repository.EventRepository;
import br.edu.ulbra.submissoes.repository.UserRepository;
import br.edu.ulbra.submissoes.service.EventService;
import br.edu.ulbra.submissoes.service.SecurityService;
import br.edu.ulbra.submissoes.service.SubmissionService;
import br.edu.ulbra.submissoes.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("evento")
public class EventController extends BaseController {

    private EventService eventService;
    private EventRepository eventRepository;
    private UserService userService;
    private SecurityService securityService;
    private SubmissionService submissionService;

    public EventController(UserService userService,
                           EventService eventService,
                           EventRepository eventRepository,
                           UserService userService1,
                           SecurityService securityService,
                           SubmissionService submissionService) {
        super(userService);
        this.eventService = eventService;
        this.eventRepository = eventRepository;
        this.userService = userService1;
        this.securityService = securityService;
        this.submissionService = submissionService;
    }

    @Autowired
    private void setEventService(EventService eventService){
        this.eventService = eventService;
    }

    @Autowired
    private void setEventRepository(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    private void setSecurityService(SecurityService securityService){
        this.securityService = securityService;
    }

    @GetMapping
    @ApiOperation(value="Página que lista todos os eventos que o usuário criou.")
    public ModelAndView listUserEvents(){
        ModelAndView  view = new ModelAndView("event/evento");
        view.addObject("eventos", eventRepository.findAll());
        return view;
    }

    @GetMapping("/{eventId}")
    public ModelAndView showEvent(@PathVariable("eventId") Long eventId){
        Event event = eventRepository.findOne(eventId);

        if(event == null)
            return new ModelAndView("/event_notfound");

        ModelAndView  view = new ModelAndView("event/edit");
        view.addObject("evento", event);
        return view;
    }

    @GetMapping("/{eventId}/delete")
    @ApiOperation(value="Exclui um evento e redireciona para a página de listagem de eventos do usuário.")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        return "Excluir o evento " + eventId;
    }

    @GetMapping("/{eventId}/submissoes")
    @ApiOperation(value="Exclui um evento e redireciona para a página de listagem de eventos do usuário.")
    public ModelAndView getSubmissions(@PathVariable("eventId") Long eventId,
                                Principal principal){
        ModelAndView view = new ModelAndView("event/submissoes");
        view.addObject("result", submissionService.findAllByEvent(eventService.findById(eventId)));
        return view;
    }


    @PostMapping("/{eventId}")
    @ApiOperation(value="Atualiza os detalhes de um evento e redireciona para a página de detalhes do evento.")
    public ModelAndView updateEvent(@PathVariable("eventId") Long eventId, EventInput eventInput){

        Date dataSalvamento = new Date();

        ModelAndView mv;
        try {
            eventInput.setId(eventId);
            eventService.update(eventInput);
            mv = new ModelAndView("redirect:/home");
        } catch (EventException e){
            mv = this.showEvent(eventId);
            mv.addObject("error", e.getMessage());
        }
        return mv;
    }


    @PostMapping("/{eventId}/submissoes/add")
    @ApiOperation(value="Atualiza os detalhes de um evento e redireciona para a página de detalhes do evento.")
    public ModelAndView saveSubmission(@PathVariable("eventId") Long eventId, SubmissionInput input,
                                       Principal principal){
        submissionService.save(new Submission(eventService.findById(eventId), getCurrentUser(principal), input));
        return new ModelAndView("redirect:/home");
    }


    @GetMapping("/{eventId}/submissoes/add")
    public ModelAndView loadNewSubmission(@PathVariable("eventId") Long eventId,
                                          SubmissionInput input){
        ModelAndView view = new ModelAndView("event/add_submissoes");
        view.addObject("submission", input);
        view.addObject("event", eventId);
        return view;
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
    public ModelAndView insertEvent(EventInput eventInput, BindingResult bindingResult,
                                    Principal principal){

        ModelAndView mv;

        try {
            Event event = new Event(eventInput, getCurrentUser(principal));
            event.setCreationDate(new Date());
            eventRepository.save(event);
            mv = new ModelAndView("redirect:/home");
        } catch (Exception e){
            mv = this.showEventForm(eventInput);
            mv.addObject("error", e.getMessage());
        }
        return mv;
    }
}
