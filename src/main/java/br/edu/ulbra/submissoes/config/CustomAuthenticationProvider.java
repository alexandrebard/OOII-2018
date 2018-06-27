package br.edu.ulbra.submissoes.config;

import br.edu.ulbra.submissoes.model.User;
import br.edu.ulbra.submissoes.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider {

    private final Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("authenticate >>>>>>>>>>>>> "+authentication.getName()+" - "+authentication.getCredentials().toString());
        User user = userService.login(authentication.getName(), authentication.getCredentials().toString());
        if(user != null){
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(user.getRoles().contains("ROLE_ADMIN") ? "ROLE_ADMIN" : "ROLE_USER"));

            log.warn(" ROLE ? "+(user.getRoles().contains("ROLE_ADMIN") ? "ROLE_WRITER" : "ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), grantedAuths);
        }else{
            throw new BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
