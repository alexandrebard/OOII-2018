package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.model.Submission;
import br.edu.ulbra.submissoes.repository.EventRepository;
import br.edu.ulbra.submissoes.repository.RoleRepository;
import br.edu.ulbra.submissoes.repository.SubmissionsRepository;

import br.edu.ulbra.submissoes.repository.UserRepository;
import br.edu.ulbra.submissoes.service.SecurityService;
import br.edu.ulbra.submissoes.service.SubmissionService;
import br.edu.ulbra.submissoes.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/submissoes")
public class SubmissionController {

    private SubmissionService submissionService;
    private UserRepository userRepository;
    private EventRepository eventRepository;
    private SubmissionsRepository submissionRepository;
    private SecurityService securityService;

    @Autowired
    private void securityService(SecurityService securityService){
        this.securityService = securityService;
    }

    @Autowired
    private void submissionService(SubmissionService submissionService){
        this.submissionService = submissionService;
    }

    @Autowired
    private void userRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    private void eventRepository(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Autowired
    private void eventRepository(SubmissionsRepository submissionRepository){
        this.submissionRepository = submissionRepository;
    }

    @GetMapping("/")
    @ApiOperation(value="Página que lista todos os eventos para os quais o usuário submeteu um artigo, em ordem decrescente de data de submissão.")
    public String listUserSubmissions(){
        return "Listar todos eventos que o usuário logado submeteu um artigo";
    }

    @GetMapping("/{submissionId}")
    @ApiOperation(value="Página que lista todos os detalhes de uma determinada edição e um link para ver os detalhes do evento para o qual foi submetido. Permite também a edição da submissão.")
    public String showSubmission(@PathVariable("submissionId") Long submissionId){
        return "Detalhar a submissão " + submissionId;
    }

    @GetMapping("/evento/{eventId}")
    @ApiOperation(value="Página com o formulário de submissão de artigo.")
    public String showSubmissionForm(@PathVariable("eventId") Long eventId){
        return "Exibir formulário de submissão de artigo para o evento " + eventId;
    }

    @PostMapping("/{submissionId}")
    @ApiOperation(value="Atualiza os detalhes de uma submissão e redireciona para a página e detalhes da submissão.")
    public String updateSubmission(@PathVariable("submissionId") Long submissionId){
        return "Atualizar submissão " + submissionId;
    }

    @PostMapping("/{submissionId}/delete")
    @ApiOperation(value="Exclui uma submissão e redireciona para a lista de submissões.")
    public String deleteSubmission(@PathVariable("submissionId") Long submissionId){
        return "Excluir a submissão " + submissionId;
    }

    @PostMapping("/evento/{eventId}")
    @ApiOperation(value="Cria uma nova submissão e redireciona para a página de detalhes da submissão.")
    public String insertSubmission(@PathVariable("eventId") Long eventId){
        return "Criar uma submissão para o evento " + eventId;
    }
}
