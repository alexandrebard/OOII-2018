package br.edu.ulbra.submissoes.service;

import br.edu.ulbra.submissoes.exception.UserException;
import br.edu.ulbra.submissoes.input.UserInput;
import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.repository.UserRepository;
import liquibase.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
        }
        modelMapper.map(userInput, user);
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

    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
