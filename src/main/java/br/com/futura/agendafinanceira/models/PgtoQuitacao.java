package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import javax.persistence.*;

import br.com.futura.agendafinanceira.models.enums.FormaPagamento;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the pgto_quitacao database table.
 * 
 */
@Entity
@Table(name="pgto_quitacao")
@NamedQuery(name="PgtoQuitacao.findAll", query="SELECT p FROM PgtoQuitacao p")
public class PgtoQuitacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pgto_quitacao")
	private int idPgtoQuitacao;

	private Timestamp atualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="dt_pgto")
	private Date dtPgto;

	@Enumerated
	@Column(name="forma_pagamento")
	private FormaPagamento formaPagamento;

	private BigDecimal valor;

	@Version
	private int versao;

	//bi-directional many-to-one association to PgtoParcela
	@ManyToOne
	@JoinColumn(name="id_pgto_parcela")
	private PgtoParcela pgtoParcela;

	public PgtoQuitacao() {
	}

	public int getIdPgtoQuitacao() {
		return this.idPgtoQuitacao;
	}

	public void setIdPgtoQuitacao(int idPgtoQuitacao) {
		this.idPgtoQuitacao = idPgtoQuitacao;
	}

	public Timestamp getAtualizacao() {
		return this.atualizacao;
	}

	public void setAtualizacao(Timestamp atualizacao) {
		this.atualizacao = atualizacao;
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

	public PgtoParcela getPgtoParcela() {
		return this.pgtoParcela;
	}

	public void setPgtoParcela(PgtoParcela pgtoParcela) {
		this.pgtoParcela = pgtoParcela;
	}

}