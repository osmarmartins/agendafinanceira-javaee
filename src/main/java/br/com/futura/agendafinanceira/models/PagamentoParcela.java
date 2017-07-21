package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
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

import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;

@Entity
@Table(name = "pgto_parcela")
public class PagamentoParcela implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pgto_parcela")
	private Long idParcela;

	@ManyToOne
	@JoinColumn(name = "id_pgto")
	private Pagamento pagamento;

	private String parcela;

	@Column(name = "dt_vcto")
	@Temporal(TemporalType.DATE)
	private Date vencimento;

	private Double valor;

	private Double desconto;

	private Double juros;

	private Double mora;

	private Double outros;

	@Enumerated
	private SituacaoParcela situacao;

	@OneToMany(mappedBy = "parcela")
	private List<PagamentoQuitacao> pagamentos;

	public Long getIdParcela() {
		return idParcela;
	}

	public void setIdParcela(Long idParcela) {
		this.idParcela = idParcela;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getJuros() {
		return juros;
	}

	public void setJuros(Double juros) {
		this.juros = juros;
	}

	public Double getMora() {
		return mora;
	}

	public void setMora(Double mora) {
		this.mora = mora;
	}

	public Double getOutros() {
		return outros;
	}

	public void setOutros(Double outros) {
		this.outros = outros;
	}

	public SituacaoParcela getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoParcela situacao) {
		this.situacao = situacao;
	}

	public List<PagamentoQuitacao> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<PagamentoQuitacao> pagamentos) {
		this.pagamentos = pagamentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idParcela == null) ? 0 : idParcela.hashCode());
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
		if (idParcela == null) {
			if (other.idParcela != null)
				return false;
		} else if (!idParcela.equals(other.idParcela))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PagamentoParcelaModel [idParcela=" + idParcela + ", pagamento=" + pagamento + ", parcela=" + parcela
				+ ", vencimento=" + vencimento + ", valor=" + valor + ", desconto=" + desconto + ", juros=" + juros
				+ ", mora=" + mora + ", outros=" + outros + ", situacao=" + situacao + ", pagamentos=" + pagamentos
				+ "]";
	}

}
