package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.input.EventInput;
import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.repository.EventRepository;
import br.edu.ulbra.submissoes.repository.UserRepository;
import br.edu.ulbra.submissoes.service.EventService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.events.EventException;

import javax.validation.Valid;

@RestController
@RequestMapping("evento")
public class EventController {
    private EventService eventService;

    @Autowired
    private EventRepository repository;

    @Autowired
    private UserRepository userRepository;

    //GET  /evento             - Página que lista todos os eventos que o usuário criou
    @GetMapping
    @ApiOperation(value="Página que lista todos os eventos que o usuário criou.")
    public ModelAndView listUserEvents(){
        ModelAndView  view = new ModelAndView("event/evento");
        view.addObject("eventos", repository.findAll());
        return view;
    }

    //GET  /evento/{id}        - Página que lista os detalhes de um determinado evento e um link para a submissão de artigo. Caso o evento tenha sido criado pelo usuário corrente, permite a edição do evento.
    @GetMapping("/{eventId}")
    public ModelAndView showEvent(@PathVariable("eventId") Long eventId){
        Event event = repository.findOne(eventId);

        if(event == null)
            return new ModelAndView("/event_notfound");

        ModelAndView  view = new ModelAndView("event/eventoid");
        view.addObject("evento", event);
        return view;
    }

    @PostMapping("/luana")
    public ModelAndView editEvent(Event evento){
        User user = userRepository.findOne(1L);
        repository.save(evento);
        return listUserEvents();
    }

    //GET  /evento/{id}/delete - Exclui um evento e redireciona para a página de listagem de eventos do usuário
    @GetMapping("/{eventId}/delete")
    @ApiOperation(value="Exclui um evento e redireciona para a página de listagem de eventos do usuário.")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        return "Excluir o evento " + eventId;
    }




    //POST /evento/cadastro - Cria um novo evento e redireciona para a página de detalhes do evento
    @PostMapping("/{eventId}")
    @ApiOperation(value="Atualiza os detalhes de um evento e redireciona para a página de detalhes do evento.")
    public String updateEvent(@PathVariable("eventId") Long eventId){
        return "Alterar o evento " + eventId;
    }


    @GetMapping("/cadastro")
    @ApiOperation(value="Página que exibe o formulário de cadastro de usuário (apenas se não está logado).")
    public ModelAndView showUserForm(EventInput event){

        ModelAndView mv = new ModelAndView("event/cadastro");
        mv.addObject("event", event);
        return mv;

    }

    //POST /evento/cadastro             - Página que cadsatra um novo evento
    @PostMapping("/cadastro")
    @ApiOperation(value="Página que cadsatra um novo usuário (apenas se não está logado) e redireciona para a página inicial do site.")
    public ModelAndView insertUser(EventInput eventInput, BindingResult bindingResult){

        ModelAndView mv;
        User user = userRepository.findOne(1L);
        try {
            repository.save(new Event(eventInput, user));
            mv = new ModelAndView("redirect:/evento");
        } catch (RuntimeException e){
            mv = this.showUserForm(eventInput);
            mv.addObject("error", e.getMessage());
        }
        return mv;
    }
}
