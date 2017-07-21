package br.com.futura.agendafinanceira.models;

import br.com.futura.agendafinanceira.models.enums.FormaPagamento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-21T01:37:50.322-0300")
@StaticMetamodel(PagamentoQuitacao.class)
public class PagamentoQuitacao_ {
	public static volatile SingularAttribute<PagamentoQuitacao, Long> idQuitacao;
	public static volatile SingularAttribute<PagamentoQuitacao, PagamentoParcela> parcela;
	public static volatile SingularAttribute<PagamentoQuitacao, Date> dataPagamento;
	public static volatile SingularAttribute<PagamentoQuitacao, Double> valor;
	public static volatile SingularAttribute<PagamentoQuitacao, FormaPagamento> formaPagamento;
}
