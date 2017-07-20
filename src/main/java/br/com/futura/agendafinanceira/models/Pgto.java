package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pgtos database table.
 * 
 */
@Entity
@Table(name="pgtos")
@NamedQuery(name="Pgto.findAll", query="SELECT p FROM Pgto p")
public class Pgto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pgto")
	private int idPgto;

	private Timestamp atualizacao;

	private String documento;

	@Temporal(TemporalType.DATE)
	private Date emissao;

	private String historico;

	private byte situacao;

	private BigDecimal total;

	@Column(name="total_pg")
	private BigDecimal totalPg;

	//bi-directional many-to-one association to Conta
	@ManyToOne
	@JoinColumn(name="id_conta")
	private Conta conta;

	//bi-directional many-to-one association to Fornecedore
	@ManyToOne
	@JoinColumn(name="id_fornecedor")
	private Fornecedor fornecedore;

	//bi-directional many-to-one association to Setore
	@ManyToOne
	@JoinColumn(name="id_setor")
	private Setor setore;

	//bi-directional many-to-one association to PgtosParcela
	@OneToMany(mappedBy="pgto")
	private List<PgtosParcela> pgtosParcelas;

	public Pgto() {
	}

	public int getIdPgto() {
		return this.idPgto;
	}

	public void setIdPgto(int idPgto) {
		this.idPgto = idPgto;
	}

	public Timestamp getAtualizacao() {
		return this.atualizacao;
	}

	public void setAtualizacao(Timestamp atualizacao) {
		this.atualizacao = atualizacao;
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

	public byte getSituacao() {
		return this.situacao;
	}

	public void setSituacao(byte situacao) {
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

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Fornecedor getFornecedore() {
		return this.fornecedore;
	}

	public void setFornecedore(Fornecedor fornecedore) {
		this.fornecedore = fornecedore;
	}

	public Setor getSetore() {
		return this.setore;
	}

	public void setSetore(Setor setore) {
		this.setore = setore;
	}

	public List<PgtosParcela> getPgtosParcelas() {
		return this.pgtosParcelas;
	}

	public void setPgtosParcelas(List<PgtosParcela> pgtosParcelas) {
		this.pgtosParcelas = pgtosParcelas;
	}

	public PgtosParcela addPgtosParcela(PgtosParcela pgtosParcela) {
		getPgtosParcelas().add(pgtosParcela);
		pgtosParcela.setPgto(this);

		return pgtosParcela;
	}

	public PgtosParcela removePgtosParcela(PgtosParcela pgtosParcela) {
		getPgtosParcelas().remove(pgtosParcela);
		pgtosParcela.setPgto(null);

		return pgtosParcela;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPgto;
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
		Pgto other = (Pgto) obj;
		if (idPgto != other.idPgto)
			return false;
		return true;
	}

}