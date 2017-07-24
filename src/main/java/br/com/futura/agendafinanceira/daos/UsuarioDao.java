package br.com.futura.agendafinanceira.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Usuario;

public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Usuario> listarUsuarios(){
		return manager.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
	}
}
