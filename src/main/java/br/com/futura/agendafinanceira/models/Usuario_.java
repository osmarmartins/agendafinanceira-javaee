package br.com.futura.agendafinanceira.models;

import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoUsuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-21T01:37:50.324-0300")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, Long> idUsuario;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, String> login;
	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, TipoUsuario> administrador;
	public static volatile SingularAttribute<Usuario, Ativo> ativo;
}
