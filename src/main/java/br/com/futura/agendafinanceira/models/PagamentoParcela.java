package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	@OneToMany(mappedBy = "parcela")
	private Set<PagamentoQuitacao> quitacoes;

	public PagamentoParcela() {
		this.situacao = SituacaoParcela.NOVO;
		this.vencimento = Calendar.getInstance().getTime();
		this.quitacoes = Collections.emptySet();
		this.valor = BigDecimal.ZERO;
		this.desconto = BigDecimal.ZERO;
		this.juros = BigDecimal.ZERO;
		this.mora = BigDecimal.ZERO;
		this.outros = BigDecimal.ZERO;
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

	public List<PagamentoQuitacao> getQuitacoes() {
		return (this.quitacoes == null) ? Collections.emptyList() : new ArrayList<PagamentoQuitacao>(this.quitacoes);
	}

	public void setQuitacoes(List<PagamentoQuitacao> pgtoQuitacaos) {
		this.quitacoes = new HashSet<PagamentoQuitacao>(pgtoQuitacaos);
	}

	public BigDecimal totalParcela() {
		return this.valor.subtract(this.desconto).add(this.juros).add(this.mora).add(this.outros);
	}

	public PagamentoQuitacao addQuitacao(PagamentoQuitacao quitacao) {
		getQuitacoes().add(quitacao);
		quitacao.setParcela(this);
		return quitacao;
	}

	public PagamentoQuitacao removeQuitacao(PagamentoQuitacao quitacao) {
		getQuitacoes().remove(quitacao);
		quitacao.setParcela(null);
		return quitacao;
	}

	public BigDecimal totalPago() {
		BigDecimal pagamentos = BigDecimal.ZERO;

		if (this.quitacoes == null || this.quitacoes.size() == 0) {
			return pagamentos;
		}

		for (PagamentoQuitacao quitacao : this.quitacoes) {
			pagamentos = pagamentos.add(quitacao.getValor());
		}
		return pagamentos;
	}

	@Override
	public String toString() {
		return "PagamentoParcela [idPagamentoParcela=" + idPagamentoParcela + ", parcela=" + parcela + ", vencimento="
				+ vencimento + ", valor=" + valor + ", desconto=" + desconto + ", juros=" + juros + ", mora=" + mora
				+ ", outros=" + outros + ", situacao=" + situacao + ", versao=" + versao + ", pagamento=" + pagamento
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPagamentoParcela == null) ? 0 : idPagamentoParcela.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagamentoParcela other = (PagamentoParcela) obj;
		if (idPagamentoParcela == null) {
			if (other.idPagamentoParcela != null)
				return false;
		} else if (!idPagamentoParcela.equals(other.idPagamentoParcela))
			return false;
		return true;
	}

}