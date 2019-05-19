package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
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
import br.com.futura.agendafinanceira.utils.HashMD5Util;

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
	public void salvar(Usuario usuario) throws NoSuchAlgorithmException {
		if (usuario.getIdUsuario() == null) {
			usuario.setSenha(HashMD5Util.getMD5(usuario.getSenha()));
		}
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

	@Transactional
	public Usuario adicionarAutorizacao(Usuario usuario, Autorizacao autorizacao) {
		Usuario user = usuarioDao.pesquisaPor(usuario.getIdUsuario());
		user.addRole(autorizacao);
		usuarioDao.salvar(user);
		
		return user;
	}

	@Transactional
	public Usuario removerAutorizacao(Usuario usuario, Autorizacao autorizacao) {
		Usuario user = usuarioDao.pesquisaPor(usuario.getIdUsuario());
		Autorizacao auth = autorizacaoDao.pesquisarPorId(autorizacao.getIdRole());
		user.removeRole(auth);
		auth.removeUsuario(user);
		usuarioDao.salvar(user);
		
		return user;
	} 

}
