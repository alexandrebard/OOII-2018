package br.edu.ulbra.submissoes.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UserController {

    //GET  /usuario/{id}                 - Página que lista os detalhes de um usuário e permite a edição mesmo.
    @GetMapping("/{userId}")
    @ApiOperation(value="Página que lista os detalhes de um usuário e permite a edição mesmo.")
    public String showUser(@PathVariable("userId") Long userId){
        return "Detalhar o usuário " + userId ;
    }

    //GET  /usuario/cadastro             - Página que exibe o formulário de cadastro de usuário (apenas se não está logado)
    @GetMapping("/cadastro")
    @ApiOperation(value="Página que exibe o formulário de cadastro de usuário (apenas se não está logado).")
    public String showUserForm(){
        return "Exibir formulário de cadastro de usuário";
    }

    //POST /usuario/cadastro             - Página que cadsatra um novo usuário (apenas se não está logado) e redireciona para a página inicial do site
    @PostMapping("/cadastro")
    @ApiOperation(value="Página que cadsatra um novo usuário (apenas se não está logado) e redireciona para a página inicial do site.")
    public String insertUser(){
        return "Criar um novo usuário";
    }
}
