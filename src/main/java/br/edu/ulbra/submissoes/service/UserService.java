package br.edu.ulbra.submissoes.service;

import br.edu.ulbra.submissoes.exception.UserException;
import br.edu.ulbra.submissoes.input.UserInput;
import br.edu.ulbra.submissoes.model.Role;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.repository.RoleRepository;
import br.edu.ulbra.submissoes.repository.UserRepository;
import liquibase.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(
            BCryptPasswordEncoder encoder,
            ModelMapper modelMapper,
            UserRepository userRepository,
            RoleRepository roleRepository
    ){
        this.encoder = encoder;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }

    public User save(UserInput userInput, boolean isUpdate) throws UserException {

        if (userInput == null){
            throw new UserException("Dados não informados");
        }
        if (StringUtils.isEmpty(userInput.getUsername())){
            throw new UserException("Username não informado");
        }
        if (StringUtils.isEmpty(userInput.getName())){
            throw new UserException("Nome não informado");
        }
        if (StringUtils.isEmpty(userInput.getEmail())){
            throw new UserException("E-mail não informado");
        }
        if ((!StringUtils.isEmpty(userInput.getPassword())) && (!userInput.getPassword().equals(userInput.getPasswordConfirm()))){
            throw new UserException("Senhas nao conferem");
        }

        User user;
        if (isUpdate){
            user = this.findById(userInput.getId());
        } else {
            user = new User();
            //O role padrão é o 2, de usuários
            user.setRoles(Arrays.asList(roleRepository.findOne(2L)));
        }

        modelMapper.map(userInput, user);

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);

    }

    public User findById(Long userId) throws UserException {
        User user = userRepository.findOne(userId);
        if (user == null )
            throw new UserException("Usuário não encontrado");
        return user;
    }

    public void delete(Long userId) throws UserException{
        User user = findById(userId);
        userRepository.delete(user);
    }

    public UserInput loadToEdit(Long userId) throws UserException{
        User user = this.findById(userId);
        return modelMapper.map(user, UserInput.class);
    }
}
