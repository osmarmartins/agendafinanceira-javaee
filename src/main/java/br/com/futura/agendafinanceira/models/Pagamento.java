package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import br.com.futura.agendafinanceira.models.enums.SituacaoPagamento;

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
	private Integer versao;

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
	private Set<PagamentoParcela> parcelas;
	
	public Pagamento() {
	}
	
	public Pagamento(Date emissao, SituacaoPagamento situacao, String documento){
		this.emissao = emissao;
		this.situacao = situacao;
		this.documento = documento;

	}

	public Pagamento(Pagamento pagamento) {
		this.documento = pagamento.getDocumento();
		this.emissao = pagamento.getEmissao();
		this.historico = pagamento.getHistorico();
		this.conta = pagamento.getConta();
		this.fornecedor = pagamento.getFornecedor();
		this.setor = pagamento.getSetor();
		this.situacao = SituacaoPagamento.NOVO;
	}

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

	public Integer getVersao() {
		return this.versao;
	}

	public void setVersao(Integer versao) {
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
		List<PagamentoParcela> parcelas = new ArrayList<>();
		if (this.parcelas == null) {
			Collections.emptyList();
		}else {
			this.parcelas.forEach(p -> parcelas.add(p));
			parcelas.sort((p1, p2)-> p1.getVencimento().compareTo(p2.getVencimento()) );
		}
		return parcelas;
	}

	public void setParcelas(List<PagamentoParcela> pgtoParcelas) {
		this.parcelas = new HashSet<PagamentoParcela>(pgtoParcelas);
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

	public BigDecimal totalParcelas() {
		BigDecimal totalParcela = BigDecimal.ZERO;

		if (parcelas == null || getParcelas().size() == 0) {
			return BigDecimal.ZERO;
		}

		for (PagamentoParcela parcela : this.parcelas) {
			totalParcela = totalParcela.add(parcela.totalParcela());
		}
		return totalParcela;
	}
	
	public BigDecimal totalPago() {
		BigDecimal pagamento = BigDecimal.ZERO;

		if (parcelas == null || getParcelas().size() == 0) {
			return BigDecimal.ZERO;
		}

		for (PagamentoParcela parcela : this.parcelas) {
			pagamento = pagamento.add(parcela.totalPago());
		}
		return pagamento;
	}
	
	public BigDecimal saldoDevedor() {
		return totalParcelas().subtract(totalPago());
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
				+ ", historico=" + historico + ", situacao=" + situacao + ", versao=" + versao + ", conta=" + conta
				+ ", fornecedor=" + fornecedor + ", setor=" + setor + "]";
	}



}