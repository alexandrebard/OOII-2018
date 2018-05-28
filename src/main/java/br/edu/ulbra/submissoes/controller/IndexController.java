package br.edu.ulbra.submissoes.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    //GET / - Página inicial do site. Deve exibir os eventos que o usuário criou e os eventos aos quais o usuário submeteu os arquivos. Além disso, deve exibir links para: edição do perfil, busca de eventos, criação de eventos
    @GetMapping("/")
    @ApiOperation(value="Página inicial do site. Deve exibir os eventos que o usuário criou e os eventos aos quais o usuário submeteu os arquivos. Além disso, deve exibir links para: edição do perfil, busca de eventos, criação de eventos.")
    public String index(){
        return "Exibir eventos e submissões do usuário";
    }
}
