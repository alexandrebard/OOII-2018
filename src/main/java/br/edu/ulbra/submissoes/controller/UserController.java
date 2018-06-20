package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.exception.UserException;
import br.edu.ulbra.submissoes.input.UserInput;
import br.edu.ulbra.submissoes.repository.RoleRepository;
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

    private RoleRepository roleRepository;

    @Autowired
    private void userService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    private void roleRepository(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    //GET  /usuario/{id}                 - Página que lista os detalhes de um usuário e permite a edição mesmo.
    @GetMapping("/{userId}")
    @ApiOperation(value="Página que lista os detalhes de um usuário e permite a edição mesmo.")
    public String showUser(@PathVariable("userId") Long userId){
        return "Detalhar o usuário " + userId ;
    }

    //GET  /usuario/cadastro             - Página que exibe o formulário de cadastro de usuário (apenas se não está logado)
    @GetMapping("/cadastro")
    @ApiOperation(value="Página que exibe o formulário de cadastro de usuário (apenas se não está logado).")
    public ModelAndView showUserForm(UserInput userInput){

        ModelAndView mv = new ModelAndView("user/register");
        mv.addObject("user", userInput);
        mv.addObject("roles", roleRepository.findAll());
        return mv;

    }

    //POST /usuario/cadastro             - Página que cadsatra um novo usuário (apenas se não está logado) e redireciona para a página inicial do site
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
