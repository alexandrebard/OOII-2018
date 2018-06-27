package br.edu.ulbra.submissoes;

import br.edu.ulbra.submissoes.model.Event;
import br.edu.ulbra.submissoes.model.Role;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.repository.EventRepository;
import br.edu.ulbra.submissoes.repository.RoleRepository;
import br.edu.ulbra.submissoes.repository.SubmissionRepository;
import br.edu.ulbra.submissoes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DatabaseLoader {

    private EventRepository eventRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private SubmissionRepository submissionRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public DatabaseLoader(
            RoleRepository roleRepository,
            UserRepository userRepository,
            EventRepository eventRepository,
            SubmissionRepository submissionRepository,
            BCryptPasswordEncoder encoder
    ) {

        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.submissionRepository = submissionRepository;

        this.encoder = encoder;

        this.initializeDB();
    }

    public void initializeDB(){

        List<Role> roles = Arrays.asList(
                new Role("ROLE_ADMIN"),
                new Role("ROLE_USER")
        );

        roleRepository.save(roles);

        User user = userRepository.saveAndFlush(new User("admin", "admin@admin.com", "Administrador", encoder.encode("admin"), roles));

        if(user != null ) {
            eventRepository.save(Arrays.asList(
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
