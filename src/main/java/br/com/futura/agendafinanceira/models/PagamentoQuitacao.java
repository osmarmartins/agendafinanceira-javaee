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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import br.com.futura.agendafinanceira.models.enums.FormaPagamento;

/**
 * The persistent class for the pgto_quitacao database table.
 * 
 */
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
	private int versao;

	// bi-directional many-to-one association to PgtoParcela
	@ManyToOne
	@JoinColumn(name = "id_pgto_parcela")
	private PagamentoParcela parcela;

	public PagamentoQuitacao() {
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

	public int getVersao() {
		return this.versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public PagamentoParcela getParcela() {
		return this.parcela;
	}

	public void setParcela(PagamentoParcela parcela) {
		this.parcela = parcela;
	}

	@Override
	public String toString() {
		return "PagamentoQuitacao [idPgtoQuitacao=" + idPgtoQuitacao + ", dtPgto=" + dtPgto + ", formaPagamento="
				+ formaPagamento + ", valor=" + valor + ", versao=" + versao + ", parcela=" + parcela + "]";
	}

	
}