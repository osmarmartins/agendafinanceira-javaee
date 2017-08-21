package br.com.futura.agendafinanceira.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.models.Usuario;

public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Usuario> listarTodos(){
		return manager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
	}
	
	public List<Usuario> listarPorPesquisa(String pesquisa){
		return manager.createQuery("SELECT u FROM Usuario u WHERE u.nome like :pNome || u.login like :pLogin || u.email like :pEmail", Usuario.class)
				.setParameter("pNome", pesquisa)
				.setParameter("pLogin", pesquisa)
				.setParameter("pEmail", pesquisa)
				.getResultList();
	}

	@Transactional
	public void excluir(Usuario usuario) {
		manager.remove(manager.getReference(Usuario.class, usuario.getIdUsuario()));
	}
}
