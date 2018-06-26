package br.edu.ulbra.submissoes.service;

import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	private UserRepository userRepository;

	@Autowired
	private void autenticationManager(AuthenticationManager autenticationManager){
		this.authenticationManager = autenticationManager;
	}

	@Autowired
	private void userDetailsService(UserDetailsService userDetailsService){
		this.userDetailsService = userDetailsService;
	}

	@Autowired
	private void userRepository(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	public String findLoggedInUsername(){
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails instanceof UserDetails){
			return ((UserDetails) userDetails).getUsername();
		}
		return null;
	}

	public User findLoggedInUser(){
		String currentUser = this.findLoggedInUsername();
		return userRepository.findByUsername(currentUser);
	}

	public void autologin(String username, String password){
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()){
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}


}
