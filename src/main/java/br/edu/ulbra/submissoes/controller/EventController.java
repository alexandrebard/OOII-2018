package br.edu.ulbra.submissoes.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/evento")
public class EventController {

    //GET  /evento             - Página que lista todos os eventos que o usuário criou
    @GetMapping
    @ApiOperation(value="Página que lista todos os eventos que o usuário criou.")
    public String listUserEvents(){
        return "event/evento";
    }

    //GET  /evento/{id}        - Página que lista os detalhes de um determinado evento e um link para a submissão de artigo. Caso o evento tenha sido criado pelo usuário corrente, permite a edição do evento.
    @GetMapping("/{eventId}")
    @ApiOperation(value="Página que lista os detalhes de um determinado evento e um link para a submissão de artigo. Caso o evento tenha sido criado pelo usuário corrente, permite a edição do evento.")
    public String showEvent(@PathVariable("eventId") Long eventId){
        return "Detalhar o evento " + eventId;
    }

    //GET  /evento/{id}/delete - Exclui um evento e redireciona para a página de listagem de eventos do usuário
    @GetMapping("/{eventId}/delete")
    @ApiOperation(value="Exclui um evento e redireciona para a página de listagem de eventos do usuário.")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        return "Excluir o evento " + eventId;
    }

    //POST /evento             - Cria um novo evento e redireciona para a página de detalhes do evento
    @PostMapping("/")
    @ApiOperation(value="Cria um novo evento e redireciona para a página de detalhes do evento")
    public String insertEvent(){
        return "Criar um novo evento";
    }

    //POST /evento/{id}        - Atualiza os detalhes de um evento e redireciona para a página de detalhes do evento
    @PostMapping("/{eventId}")
    @ApiOperation(value="Atualiza os detalhes de um evento e redireciona para a página de detalhes do evento.")
    public String updateEvent(@PathVariable("eventId") Long eventId){
        return "Alterar o evento " + eventId;
    }
}
