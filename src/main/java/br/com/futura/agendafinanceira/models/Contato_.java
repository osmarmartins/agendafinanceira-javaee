package br.com.futura.agendafinanceira.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-21T01:37:50.316-0300")
@StaticMetamodel(Contato.class)
public class Contato_ {
	public static volatile SingularAttribute<Contato, Long> idContato;
	public static volatile SingularAttribute<Contato, Fornecedor> fornecedor;
	public static volatile SingularAttribute<Contato, String> contato;
	public static volatile SingularAttribute<Contato, String> endereco;
	public static volatile SingularAttribute<Contato, String> numero;
	public static volatile SingularAttribute<Contato, String> complemento;
	public static volatile SingularAttribute<Contato, String> bairro;
	public static volatile SingularAttribute<Contato, String> cep;
	public static volatile SingularAttribute<Contato, String> cidade;
	public static volatile SingularAttribute<Contato, String> uf;
	public static volatile SingularAttribute<Contato, String> telefone1;
	public static volatile SingularAttribute<Contato, String> telefone2;
	public static volatile SingularAttribute<Contato, String> telefone3;
	public static volatile SingularAttribute<Contato, String> email;
	public static volatile SingularAttribute<Contato, String> site;
}
