package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
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
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.futura.agendafinanceira.models.enums.SituacaoPagamento;

/**
 * The persistent class for the pgto database table.
 * 
 */
@Entity
@Table(name = "pgto")
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pgto")
	private Integer idPagamento;

	private String documento;

	@Temporal(TemporalType.DATE)
	private Date emissao;

	private String historico;

	@Enumerated
	private SituacaoPagamento situacao;

	@Version
	private int versao;

	private BigDecimal total;

	@Column(name="total_pg")
	private BigDecimal totalPago;
	
	@Transient
	private BigDecimal saldoDevedor;
	
	public Pagamento() {
		this.total = BigDecimal.ZERO;
		this.totalPago = BigDecimal.ZERO;
		this.emissao = new Date();
		this.situacao = SituacaoPagamento.EMABERTO;
	}
	
	// bi-directional many-to-one association to Conta
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;

	// bi-directional many-to-one association to Fornecedor
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;

	// bi-directional many-to-one association to Setor
	@ManyToOne
	@JoinColumn(name = "id_setor")
	private Setor setor;

	// bi-directional many-to-one association to PgtoParcela
	@OneToMany(mappedBy = "pagamento")
	private List<PagamentoParcela> parcelas;

	public Integer getIdPagamento() {
		return this.idPagamento;
	}

	public void setIdPagamento(Integer idPgto) {
		this.idPagamento = idPgto;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Date getEmissao() {
		return this.emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public String getHistorico() {
		return this.historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public SituacaoPagamento getSituacao() {
		return this.situacao;
	}

	public void setSituacao(SituacaoPagamento situacao) {
		this.situacao = situacao;
	}

	public int getVersao() {
		return this.versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Setor getSetor() {
		return this.setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<PagamentoParcela> getParcelas() {
		return this.parcelas == null ?  Collections.emptyList() : this.parcelas;
	}

	public void setParcelas(List<PagamentoParcela> pgtoParcelas) {
		this.parcelas = pgtoParcelas;
	}

	public PagamentoParcela addParcela(PagamentoParcela parcela) {
		getParcelas().add(parcela);
		parcela.setPagamento(this);
		return parcela;
	}

	public PagamentoParcela removeParcela(PagamentoParcela parcela) {
		getParcelas().remove(parcela);
		parcela.setPagamento(null);
		return parcela;
	}

	private void calcularTotais() {
		BigDecimal totalParcelas = BigDecimal.ZERO;
		BigDecimal totalPago = BigDecimal.ZERO;
		
		if (parcelas ==null || parcelas.size() == 0) {
			this.total = totalParcelas;
			this.totalPago = totalPago;
			return; 
		}
		
		for (PagamentoParcela parcela : this.parcelas) {
			totalParcelas = totalParcelas.add(parcela.getTotalParcela());
			totalPago = totalPago.add(parcela.calcularTotalPago());
		}
		this.total = totalParcelas;
		this.totalPago = totalPago;
	}
	
	public BigDecimal getTotal() {
		calcularTotais();
		return this.total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public BigDecimal getTotalPago() {
		calcularTotais();
		return this.totalPago;
	}
	
	public void setTotalPago(BigDecimal totalPago) {
		this.totalPago = totalPago;
	}
	
	public BigDecimal getSaldoDevedor() {
		return this.total.subtract(this.totalPago);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPagamento == null) ? 0 : idPagamento.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (idPagamento == null) {
			if (other.idPagamento != null)
				return false;
		} else if (!idPagamento.equals(other.idPagamento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pagamento [idPagamento=" + idPagamento + ", documento=" + documento + ", emissao=" + emissao
				+ ", historico=" + historico + ", situacao=" + situacao + ", total=" + total + ", totalPg=" + totalPago
				+ ", versao=" + versao + ", conta=" + conta + ", fornecedor=" + fornecedor + ", setor=" + setor
				+ ", parcelas=" + parcelas + "]";
	}
	

}