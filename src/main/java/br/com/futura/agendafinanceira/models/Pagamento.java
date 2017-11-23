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

	private BigDecimal total;

	@Column(name = "total_pg")
	private BigDecimal totalPg;

	@Version
	private int versao;

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

	public Pagamento() {
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

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotalPg() {
		return this.totalPg;
	}

	public void setTotalPg(BigDecimal totalPg) {
		this.totalPg = totalPg;
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
		return this.parcelas;
	}

	public void setParcelas(List<PagamentoParcela> pgtoParcelas) {
		this.parcelas = pgtoParcelas;
	}

	public PagamentoParcela addPgtoParcela(PagamentoParcela parcela) {
		getParcelas().add(parcela);
		parcela.setPagamento(this);

		return parcela;
	}

	public PagamentoParcela removePgtoParcela(PagamentoParcela parcela) {
		getParcelas().remove(parcela);
		parcela.setPagamento(null);

		return parcela;
	}

}