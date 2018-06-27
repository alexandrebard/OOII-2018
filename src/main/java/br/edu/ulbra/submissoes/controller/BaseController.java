package br.edu.ulbra.submissoes.controller;

import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.service.UserService;
import java.security.Principal;

public abstract class BaseController {

    protected UserService userService;

    public BaseController(UserService userService) {
        this.userService = userService;
    }

    protected User getCurrentUser(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        if(user == null)
            throw new RuntimeException("Usuário não localizado.....");
        return user;
    }


}
