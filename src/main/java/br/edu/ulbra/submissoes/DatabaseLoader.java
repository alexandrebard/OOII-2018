package br.edu.ulbra.submissoes;

import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.model.Role;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.repository.EventRepository;
import br.edu.ulbra.submissoes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class DatabaseLoader {

    private EventRepository repository;
    private UserRepository userRepository;

    @Autowired
    public DatabaseLoader(EventRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.initializeDB();
    }

    public void initializeDB(){
        User user = userRepository.saveAndFlush(new User("teste", "teste@teste.com", "Nome Teste", "1234", Arrays.asList(new Role("ROLE_USER"))));

        if(user != null ) {
            repository.save(Arrays.asList(
                    new Event("Evento 1", new Date(), new Date(), user),
                    new Event("Evento 2", new Date(), new Date(), user),
                    new Event("Evento 3", new Date(), new Date(), user),
                    new Event("Evento 4", new Date(), new Date(), user),
                    new Event("Evento 5", new Date(), new Date(), user),
                    new Event("Evento 6", new Date(), new Date(), user)
            ));
        }
    }
}
