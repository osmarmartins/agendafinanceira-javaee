package br.com.futura.agendafinanceira.models;

import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-21T01:37:50.320-0300")
@StaticMetamodel(PagamentoParcela.class)
public class PagamentoParcela_ {
	public static volatile SingularAttribute<PagamentoParcela, Long> idParcela;
	public static volatile SingularAttribute<PagamentoParcela, Pagamento> pagamento;
	public static volatile SingularAttribute<PagamentoParcela, String> parcela;
	public static volatile SingularAttribute<PagamentoParcela, Date> vencimento;
	public static volatile SingularAttribute<PagamentoParcela, Double> valor;
	public static volatile SingularAttribute<PagamentoParcela, Double> desconto;
	public static volatile SingularAttribute<PagamentoParcela, Double> juros;
	public static volatile SingularAttribute<PagamentoParcela, Double> mora;
	public static volatile SingularAttribute<PagamentoParcela, Double> outros;
	public static volatile SingularAttribute<PagamentoParcela, SituacaoParcela> situacao;
	public static volatile ListAttribute<PagamentoParcela, PagamentoQuitacao> pagamentos;
}
