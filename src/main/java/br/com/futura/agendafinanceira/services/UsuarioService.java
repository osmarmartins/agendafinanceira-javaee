package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.futura.agendafinanceira.daos.AutorizacaoDao;
import br.com.futura.agendafinanceira.daos.UsuarioDao;
import br.com.futura.agendafinanceira.models.Autorizacao;
import br.com.futura.agendafinanceira.models.Usuario;

public class UsuarioService implements Serializable, UserDetailsService {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private AutorizacaoDao autorizacaoDao;

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

	public List<Autorizacao> listarAutorizacoesDisponiveis(Usuario usuario) {
		List<Autorizacao> autorizacoes = autorizacaoDao.autorizacoes();
		List<Autorizacao> novasAutorizacoes = new ArrayList<>();
		
		if (autorizacoes.isEmpty()) {
			return Collections.EMPTY_LIST;
		}
		
		for (Autorizacao autorizacao : autorizacoes) {
			if (!usuario.getPermissoes().contains(autorizacao)) {
				novasAutorizacoes.add(autorizacao);
			}
		}
		
		return novasAutorizacoes.isEmpty() ? Collections.EMPTY_LIST : novasAutorizacoes;
	}

	public void adicionarAutorizacao(Usuario usuario, Autorizacao autorizacao) {
		usuario.addRole(autorizacao);
		autorizacao.addUsuario(usuario);
		this.salvar(usuario);
	}

	public void removerAutorizacao(Usuario usuario, Autorizacao autorizacao) {
		usuario.removeRole(autorizacao);
		autorizacao.removeUsuario(usuario);
		this.salvar(usuario);		
	} 

}
