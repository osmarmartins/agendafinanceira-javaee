package br.com.futura.agendafinanceira.security;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.futura.agendafinanceira.models.Usuario;

@Named
public class AutenticadorUsuario implements AuthenticationProvider {
	
	@Inject
	private SecurityUserDetaisService usuarioService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String login = authentication.getName();
		String senha = (String) authentication.getCredentials();
		
		Usuario usuario = usuarioService.loadUserByUsername(login);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
	    if (usuario == null || !encoder.matches(senha, usuario.getPassword()) ) {
	    	throw new BadCredentialsException("Login ou senha inválidos!");
		}

	    Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();
	    
	    return new UsernamePasswordAuthenticationToken(login, senha, authorities);		
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
