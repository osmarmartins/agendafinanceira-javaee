package br.com.futura.agendafinanceira.security;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.futura.agendafinanceira.models.Usuario;

@Named
public class SecurityUserDetaisService implements UserDetailsService {
	
	@Inject
	private SecurityUserDetailsDao dao;

	@Override
	public Usuario loadUserByUsername(String login) throws UsernameNotFoundException {
		return dao.pesquisarPor(login);
	}

}
