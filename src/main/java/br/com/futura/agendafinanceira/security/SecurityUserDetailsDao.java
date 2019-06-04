package br.com.futura.agendafinanceira.security;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.futura.agendafinanceira.models.Usuario;

@Named
public class SecurityUserDetailsDao {

	private EntityManager em;
	private EntityManagerFactory emf;

	private void createEntityManager() {
		emf = Persistence.createEntityManagerFactory("agendafinanceiraPU");
		em = emf.createEntityManager();
	}

	private void destroyEntityManager() {
		em.close();
		emf.close();
	}

	public Usuario pesquisarPor(String login) {
		createEntityManager();
		
		Usuario usuarioLogado = new Usuario();
		try {
			usuarioLogado = em.createQuery("select u from Usuario u where u.ativo=1 and u.login = :pLogin ", Usuario.class)
					.setParameter("pLogin", login)
					.getSingleResult();
			destroyEntityManager();
		} catch (Exception e) {
			destroyEntityManager();
			throw new UsernameNotFoundException("Usuário não localizado!");
		}
		
		return usuarioLogado;
	}

}
