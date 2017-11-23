package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;

/**
 * The persistent class for the pgto_parcela database table.
 * 
 */
@Entity
@Table(name = "pgto_parcela")
public class PagamentoParcela implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pgto_parcela")
	private Integer idPagamentoParcela;

	private String parcela;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_vcto")
	private Date vencimento;

	private BigDecimal valor;

	private BigDecimal desconto;
	
	private BigDecimal juros;

	private BigDecimal mora;

	private BigDecimal outros;

	@Enumerated
	private SituacaoParcela situacao;

	@Version
	private int versao;

	// bi-directional many-to-one association to Pgto
	@ManyToOne
	@JoinColumn(name = "id_pgto")
	private Pagamento pagamento;

	// bi-directional many-to-one association to PgtoQuitacao
	@OneToMany(mappedBy = "pgtoParcela")
	private List<PagamentoQuitacao> quitacoes;
	
	public PagamentoParcela() {
	}

	public Integer getIdPagamentoParcela() {
		return idPagamentoParcela;
	}
	
	public void setIdPagamentoParcela(Integer idPagamentoParcela) {
		this.idPagamentoParcela = idPagamentoParcela;
	}
	

	public BigDecimal getDesconto() {
		return this.desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	
	public Date getVencimento() {
		return vencimento;
	}
	
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public BigDecimal getJuros() {
		return this.juros;
	}

	public void setJuros(BigDecimal juros) {
		this.juros = juros;
	}

	public BigDecimal getMora() {
		return this.mora;
	}

	public void setMora(BigDecimal mora) {
		this.mora = mora;
	}

	public BigDecimal getOutros() {
		return this.outros;
	}

	public void setOutros(BigDecimal outros) {
		this.outros = outros;
	}

	public String getParcela() {
		return this.parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public SituacaoParcela getSituacao() {
		return this.situacao;
	}

	public void setSituacao(SituacaoParcela situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getVersao() {
		return this.versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public Pagamento getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<PagamentoQuitacao> getPgtoQuitacaos() {
		return this.quitacoes;
	}

	public void setPgtoQuitacaos(List<PagamentoQuitacao> pgtoQuitacaos) {
		this.quitacoes = pgtoQuitacaos;
	}

	public PagamentoQuitacao addPgtoQuitacao(PagamentoQuitacao pgtoQuitacao) {
		getPgtoQuitacaos().add(pgtoQuitacao);
		pgtoQuitacao.setPgtoParcela(this);

		return pgtoQuitacao;
	}

	public PagamentoQuitacao removePgtoQuitacao(PagamentoQuitacao pgtoQuitacao) {
		getPgtoQuitacaos().remove(pgtoQuitacao);
		pgtoQuitacao.setPgtoParcela(null);

		return pgtoQuitacao;
	}
	
	public BigDecimal getTotalParcela(){
		return this.valor.subtract(this.desconto).add(this.juros).add(this.mora).add(this.outros);
	}

}