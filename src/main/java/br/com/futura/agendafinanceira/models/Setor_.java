package br.com.futura.agendafinanceira.models;

import br.com.futura.agendafinanceira.models.enums.Ativo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-21T01:37:50.323-0300")
@StaticMetamodel(Setor.class)
public class Setor_ {
	public static volatile SingularAttribute<Setor, Long> idSetor;
	public static volatile SingularAttribute<Setor, String> descricao;
	public static volatile SingularAttribute<Setor, Ativo> ativo;
}
