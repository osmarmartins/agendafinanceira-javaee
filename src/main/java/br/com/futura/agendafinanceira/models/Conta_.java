package br.com.futura.agendafinanceira.models;

import br.com.futura.agendafinanceira.models.enums.Ativo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-21T01:37:50.209-0300")
@StaticMetamodel(Conta.class)
public class Conta_ {
	public static volatile SingularAttribute<Conta, Long> idConta;
	public static volatile SingularAttribute<Conta, String> descricao;
	public static volatile SingularAttribute<Conta, Ativo> ativo;
}
