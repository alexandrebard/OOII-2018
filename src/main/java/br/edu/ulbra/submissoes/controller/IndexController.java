package br.edu.ulbra.submissoes.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    @ApiOperation(value="Página inicial do site. Deve exibir os eventos que o usuário criou e os eventos aos quais o usuário submeteu os arquivos. Além disso, deve exibir links para: edição do perfil, busca de eventos, criação de eventos.")
    public ModelAndView index(){
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/home")
    public ModelAndView homePage(){
        return new ModelAndView("home");
    }

    @GetMapping("/login")
    public ModelAndView loginForm(){
        return new ModelAndView("login");
    }

    @GetMapping("/denied")
    public ModelAndView denied(){
        return new ModelAndView("denied");
    }
}
