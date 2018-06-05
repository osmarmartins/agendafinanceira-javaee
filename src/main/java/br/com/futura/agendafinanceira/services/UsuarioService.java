package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.UsuarioDao;
import br.com.futura.agendafinanceira.models.Usuario;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDao usuarioDao;

	public List<Usuario> listarTodos() {
		return usuarioDao.listarTodos();
	}

	public void excluir(Usuario usuario) {
		usuarioDao.excluir(usuario);
	}

	public List<Usuario> listarPor(String filtro) {
		return usuarioDao.listarPor(filtro);
	}

	public void salvar(Usuario usuario) {
		usuarioDao.salvar(usuario);
	}

}
