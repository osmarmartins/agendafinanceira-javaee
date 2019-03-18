package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.futura.agendafinanceira.daos.UsuarioDao;
import br.com.futura.agendafinanceira.models.Usuario;

public class UsuarioService implements Serializable, UserDetailsService {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDao usuarioDao;

	public List<Usuario> listarTodos() {
		return usuarioDao.listarTodos();
	}

	@Transactional
	public void excluir(List<Usuario> usuarios) {
		// TODO Validar exclusão de usuarios
		usuarioDao.excluir(usuarios);
	}

	public List<Usuario> listarPor(String filtro) {
		return usuarioDao.listarPor(filtro);
	}

	@Transactional
	public void salvar(Usuario usuario) {
		usuarioDao.salvar(usuario);
	}

	@Override
	public Usuario loadUserByUsername(String login) throws UsernameNotFoundException {
		return usuarioDao.pesquisarPor(login);
	}

}
