package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
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

import br.com.futura.agendafinanceira.models.enums.FormaPagamento;

@Entity
@Table(name = "pgto_quitacao")
public class PagamentoQuitacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pgto_quitacao")
	private Long idQuitacao;

	@ManyToOne
	@JoinColumn(name = "id_pgto_parcela")
	private PagamentoParcela parcela;

	@Temporal(TemporalType.DATE)
	@Column(name="dt_pgto")
	private Date dataPagamento;

	private Double valor;

	@Enumerated
	@Column(name="forma_pagamento")
	private FormaPagamento formaPagamento;

	public Long getIdQuitacao() {
		return idQuitacao;
	}

	public void setIdQuitacao(Long idQuitacao) {
		this.idQuitacao = idQuitacao;
	}

	public PagamentoParcela getParcela() {
		return parcela;
	}

	public void setParcela(PagamentoParcela parcela) {
		this.parcela = parcela;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idQuitacao == null) ? 0 : idQuitacao.hashCode());
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
		if (idQuitacao == null) {
			if (other.idQuitacao != null)
				return false;
		} else if (!idQuitacao.equals(other.idQuitacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PagamentoQuitacaoModel [idQuitacao=" + idQuitacao + ", parcela=" + parcela + ", dataPagamento="
				+ dataPagamento + ", valor=" + valor + ", formaPagamento=" + formaPagamento + "]";
	}

}
