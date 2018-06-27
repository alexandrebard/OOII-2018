package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.exception.UserException;
import br.edu.ulbra.submissoes.input.UserInput;
import br.edu.ulbra.submissoes.repository.RoleRepository;
import br.edu.ulbra.submissoes.repository.UserRepository;
import br.edu.ulbra.submissoes.service.SecurityService;
import br.edu.ulbra.submissoes.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    @ApiOperation(value="Atualiza os detalhes de um usuário e redireciona para a página de detalhes do evento")
    public ModelAndView updateUser(@PathVariable("userId") Long userId, UserInput userInput){

        ModelAndView mv;

        try {
            userService.save(userInput, true);
            mv = new ModelAndView("redirect:/evento");
        } catch (UserException e){
            mv = this.showUser(userId);
            mv.addObject("error", e.getMessage());
        }
        return mv;
    }

    @GetMapping("/{userId}")
    @ApiOperation(value="Página que lista os detalhes de um usuário e permite a edição mesmo.")
    public ModelAndView showUser(@PathVariable("userId") Long userId){

        ModelAndView mv;
        mv = new ModelAndView("edit");

        try {
            mv.addObject("user", userService.loadToEdit(userId));
        } catch (UserException e){
            mv.addObject("error", e.getMessage());
        }
        return mv;

    }

    @GetMapping("/cadastro")
    @ApiOperation(value="Página que exibe o formulário de cadastro de usuário (apenas se não está logado).")
    public ModelAndView showUserForm(UserInput userInput){

        ModelAndView mv = new ModelAndView("user/register");
        mv.addObject("user", userInput);
        return mv;

    }

    @PostMapping("/cadastro")
    @ApiOperation(value="Página que cadsatra um novo usuário (apenas se não está logado) e redireciona para a página inicial do site.")
    public ModelAndView insertUser(UserInput userInput){

        ModelAndView mv;

        try {
            userService.save(userInput, false);
            mv = new ModelAndView("redirect:/");
        } catch (UserException e){
            mv = this.showUserForm(userInput);
            mv.addObject("error", e.getMessage());
        }
        return mv;
    }
}
