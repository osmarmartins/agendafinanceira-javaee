package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.futura.agendafinanceira.models.enums.FormaPagamento;
import br.com.futura.agendafinanceira.utils.DataUtil;

@Entity
@Table(name = "pgto_quitacao")
public class PagamentoQuitacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pgto_quitacao")
	private Integer idPgtoQuitacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_pgto")
	private Date dtPgto;

	@Enumerated
	@Column(name = "forma_pagamento")
	private FormaPagamento formaPagamento;

	private BigDecimal valor;

	@Version
	private Integer versao;
	
	@Transient
	private String diaDaSemana;

	@PostLoad
	private void init() {
		this.diaDaSemana = retornaDiaDaSemana();
	}
	
	// bi-directional many-to-one association to PgtoParcela
	@ManyToOne
	@JoinColumn(name = "id_pgto_parcela")
	private PagamentoParcela parcela;

	public PagamentoQuitacao() {
	}
	
	public PagamentoQuitacao(BigDecimal valor, Date dataPgto, FormaPagamento formaPagamento) {
		this.valor = valor;
		this.dtPgto = dataPgto;
		this.formaPagamento = formaPagamento;
	}

	public Integer getIdPgtoQuitacao() {
		return this.idPgtoQuitacao;
	}

	public void setIdPgtoQuitacao(Integer idPgtoQuitacao) {
		this.idPgtoQuitacao = idPgtoQuitacao;
	}

	public Date getDtPgto() {
		return this.dtPgto;
	}

	public void setDtPgto(Date dtPgto) {
		this.dtPgto = dtPgto;
	}

	public FormaPagamento getFormaPagamento() {
		return this.formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getVersao() {
		return this.versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public PagamentoParcela getParcela() {
		return this.parcela;
	}

	public void setParcela(PagamentoParcela parcela) {
		this.parcela = parcela;
	}
	
	public String getDiaDaSemana() {
		return retornaDiaDaSemana();
	}
	
	private String retornaDiaDaSemana() {
		return DataUtil.diaDaSemana(this.dtPgto);
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPgtoQuitacao == null) ? 0 : idPgtoQuitacao.hashCode());
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
		PagamentoQuitacao other = (PagamentoQuitacao) obj;
		if (idPgtoQuitacao == null) {
			if (other.idPgtoQuitacao != null)
				return false;
		} else if (!idPgtoQuitacao.equals(other.idPgtoQuitacao))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "PagamentoQuitacao [idPgtoQuitacao=" + idPgtoQuitacao + ", dtPgto=" + dtPgto + ", formaPagamento="
				+ formaPagamento + ", valor=" + valor + ", versao=" + versao + ", parcela=" + parcela + "]";
	}

	
}
