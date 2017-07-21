package br.com.futura.agendafinanceira.models;

import br.com.futura.agendafinanceira.models.enums.SituacaoPagamento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-07-21T01:37:50.318-0300")
@StaticMetamodel(Pagamento.class)
public class Pagamento_ {
	public static volatile SingularAttribute<Pagamento, Long> idPgto;
	public static volatile SingularAttribute<Pagamento, Setor> setor;
	public static volatile SingularAttribute<Pagamento, Conta> conta;
	public static volatile SingularAttribute<Pagamento, Fornecedor> fornecedor;
	public static volatile SingularAttribute<Pagamento, String> documento;
	public static volatile SingularAttribute<Pagamento, Date> emissao;
	public static volatile SingularAttribute<Pagamento, String> historico;
	public static volatile SingularAttribute<Pagamento, SituacaoPagamento> situacao;
	public static volatile ListAttribute<Pagamento, PagamentoParcela> parcelas;
}
