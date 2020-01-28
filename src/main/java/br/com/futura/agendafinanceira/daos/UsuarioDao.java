package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Usuario;
import br.com.futura.agendafinanceira.utils.NumberConversionUtil;

public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Usuario> listarTodos() {
		return manager.createQuery("SELECT u FROM Usuario u ", Usuario.class).getResultList();
	}

	public List<Usuario> listarPor(String filtro) {
		String argumentoPesquisa = "%" + filtro + "%";
		return manager
				.createQuery("SELECT u FROM Usuario u "
						+ "WHERE u.idUsuario = :pIdUsuario "
						+ "OR u.nome like :pNome "
						+ "OR u.login like :pLogin "
						+ "OR u.email like :pEmail", Usuario.class)
				.setParameter("pIdUsuario", NumberConversionUtil.getIntegerOrZero(filtro))
				.setParameter("pNome", argumentoPesquisa)
				.setParameter("pLogin", argumentoPesquisa)
				.setParameter("pEmail", argumentoPesquisa)
				.getResultList();
	}

	public Usuario pesquisaPor(Integer idUsuario) {
		return manager.find(Usuario.class, idUsuario);
	}

	public Usuario pesquisarPor(String login) {
		String sql = "select u from Usuario u "
				+ "join fetch u.permissoes p "
				+ "where u.login=:pLogin ";
		return manager.createQuery(sql , Usuario.class)
				.setParameter("pLogin", login)
				.getSingleResult();
	}
	
	public void excluir(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			manager.remove(manager.getReference(Usuario.class, usuario.getIdUsuario()));
		}
		
	}

	public void salvar(Usuario usuario) {
		if (usuario.getIdUsuario() == null) {
			manager.persist(usuario);
		} else {
			manager.merge(usuario);
		}
	}
		
}
