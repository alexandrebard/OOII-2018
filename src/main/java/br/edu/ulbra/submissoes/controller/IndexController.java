package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.service.EventService;
import br.edu.ulbra.submissoes.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController{

    private EventService eventService;

    public IndexController(UserService userService, EventService eventService) {
        super(userService);
        this.eventService = eventService;
    }

    @GetMapping("/")
    @ApiOperation(value="Página inicial do site. Deve exibir os eventos que o usuário criou e os eventos aos quais o usuário submeteu os arquivos. Além disso, deve exibir links para: edição do perfil, busca de eventos, criação de eventos.")
    public ModelAndView index(){
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/home")
    public ModelAndView homePage(Principal principal){
        User user = getCurrentUser(principal);

        ModelAndView mv =  new ModelAndView("home");
        mv.addObject("events", eventService.findByUser(user));

        return mv;
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
