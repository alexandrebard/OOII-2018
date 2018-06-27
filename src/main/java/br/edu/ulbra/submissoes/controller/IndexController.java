package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.service.SecurityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    @ApiOperation(value="Página inicial do site. Deve exibir os eventos que o usuário criou e os eventos aos quais o usuário submeteu os arquivos. Além disso, deve exibir links para: edição do perfil, busca de eventos, criação de eventos.")
    public String index(){
        return "Exibir eventos e submissões do usuário";
    }

    @GetMapping("/login")
    public ModelAndView loginForm(){
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public String login () {
        return "redirect:/";
    }

    @GetMapping("/denied")
    public ModelAndView denied(){
        return new ModelAndView("denied");
    }

}
