package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the pgtos_quitacao database table.
 * 
 */
@Entity
@Table(name="pgtos_quitacao")
@NamedQuery(name="PgtosQuitacao.findAll", query="SELECT p FROM PgtosQuitacao p")
public class PgtosQuitacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pgto_quitacao")
	private int idPgtoQuitacao;

	private Timestamp atualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="dt_pgto")
	private Date dtPgto;

	@Column(name="forma_pagamento")
	private byte formaPagamento;

	private BigDecimal valor;

	//bi-directional many-to-one association to PgtosParcela
	@ManyToOne
	@JoinColumn(name="id_pgto_parcela")
	private PgtosParcela pgtosParcela;

	public PgtosQuitacao() {
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

	public byte getFormaPagamento() {
		return this.formaPagamento;
	}

	public void setFormaPagamento(byte formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public PgtosParcela getPgtosParcela() {
		return this.pgtosParcela;
	}

	public void setPgtosParcela(PgtosParcela pgtosParcela) {
		this.pgtosParcela = pgtosParcela;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPgtoQuitacao;
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
		PgtosQuitacao other = (PgtosQuitacao) obj;
		if (idPgtoQuitacao != other.idPgtoQuitacao)
			return false;
		return true;
	}

}