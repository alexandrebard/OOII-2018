package br.edu.ulbra.submissoes.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/submissoes")
public class SubmissionController {

    //GET  /submissoes                   - Página que lista todos os eventos para os quais o usuário submeteu um artigo, em ordem decrescente de data de submissão
    @GetMapping("/")
    @ApiOperation(value="Página que lista todos os eventos para os quais o usuário submeteu um artigo, em ordem decrescente de data de submissão.")
    public String listUserSubmissions(){
        return "Listar todos eventos que o usuário logado submeteu um artigo";
    }

    //GET  /submissoes/{id}              - Página que lista todos os detalhes de uma determinada edição e um link para ver os detalhes do evento para o qual foi submetido. Permite também a edição da submissão
    @GetMapping("/{submissionId}")
    @ApiOperation(value="Página que lista todos os detalhes de uma determinada edição e um link para ver os detalhes do evento para o qual foi submetido. Permite também a edição da submissão.")
    public String showSubmission(@PathVariable("submissionId") Long submissionId){
        return "Detalhar a submissão " + submissionId;
    }

    //GET  /submissoes/evento/{idEvento} - Página com o formulário de submissão de artigo
    @GetMapping("/evento/{eventId}")
    @ApiOperation(value="Página com o formulário de submissão de artigo.")
    public String showSubmissionForm(@PathVariable("eventId") Long eventId){
        return "Exibir formulário de submissão de artigo para o evento " + eventId;
    }

    //POST /submissoes/{id}              - Atualiza os detalhes de uma submissão e redireciona para a página e detalhes da submissão
    @PostMapping("/{submissionId}")
    @ApiOperation(value="Atualiza os detalhes de uma submissão e redireciona para a página e detalhes da submissão.")
    public String updateSubmission(@PathVariable("submissionId") Long submissionId){
        return "Atualizar submissão " + submissionId;
    }

    //POST /submissoes/{id}/delete       - Exclui uma submissão e redireciona para a lista de submissões
    @PostMapping("/{submissionId}/delete")
    @ApiOperation(value="Exclui uma submissão e redireciona para a lista de submissões.")
    public String deleteSubmission(@PathVariable("submissionId") Long submissionId){
        return "Excluir a submissão " + submissionId;
    }

    //POST /submissoes/evento/{idEvento} - Cria uma nova submissão e redireciona para a página de detalhes da submissão
    @PostMapping("/evento/{eventId}")
    @ApiOperation(value="Cria uma nova submissão e redireciona para a página de detalhes da submissão.")
    public String insertSubmission(@PathVariable("eventId") Long eventId){
        return "Criar uma submissão para o evento " + eventId;
    }
}
