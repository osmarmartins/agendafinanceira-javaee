package br.com.futura.agendafinanceira.models;

import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoPessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-21T01:37:50.317-0300")
@StaticMetamodel(Fornecedor.class)
public class Fornecedor_ {
	public static volatile SingularAttribute<Fornecedor, Long> idFornecedor;
	public static volatile SingularAttribute<Fornecedor, String> nomeFantasia;
	public static volatile SingularAttribute<Fornecedor, String> razaoSocial;
	public static volatile SingularAttribute<Fornecedor, TipoPessoa> tipo;
	public static volatile SingularAttribute<Fornecedor, String> documento;
	public static volatile SingularAttribute<Fornecedor, Ativo> ativo;
	public static volatile ListAttribute<Fornecedor, Contato> contatos;
}
