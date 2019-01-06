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

	private SecurityUserDetailsDao() {
		criaEntityManager();
	}

	private void criaEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("agendafinanceiraPU");
		em = emf.createEntityManager();
	}

	public Usuario pesquisarPor(String login) {
		Usuario usuarioLogado = new Usuario();
		try {
			usuarioLogado = em.createQuery("select u from Usuario u where u.login = :pLogin ", Usuario.class)
					.setParameter("pLogin", login)
					.getSingleResult();
		} catch (Exception e) {
			throw new UsernameNotFoundException("Usuário não localizado!");
		}

		return usuarioLogado;
	}

}