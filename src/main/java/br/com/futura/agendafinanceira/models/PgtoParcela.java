package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import javax.persistence.*;

import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pgto_parcela database table.
 * 
 */
@Entity
@Table(name="pgto_parcela")
@NamedQuery(name="PgtoParcela.findAll", query="SELECT p FROM PgtoParcela p")
public class PgtoParcela implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pgto_parcela")
	private int idPgtoParcela;

	private Timestamp atualizacao;

	private BigDecimal desconto;

	@Temporal(TemporalType.DATE)
	@Column(name="dt_vcto")
	private Date dtVcto;

	private BigDecimal juros;

	private BigDecimal mora;

	private BigDecimal outros;

	private String parcela;

	@Enumerated
	private SituacaoParcela situacao;

	private BigDecimal valor;

	@Version
	private int versao;

	//bi-directional many-to-one association to Pgto
	@ManyToOne
	@JoinColumn(name="id_pgto")
	private Pgto pgto;

	//bi-directional many-to-one association to PgtoQuitacao
	@OneToMany(mappedBy="pgtoParcela")
	private List<PgtoQuitacao> pgtoQuitacaos;

	public PgtoParcela() {
	}

	public int getIdPgtoParcela() {
		return this.idPgtoParcela;
	}

	public void setIdPgtoParcela(int idPgtoParcela) {
		this.idPgtoParcela = idPgtoParcela;
	}

	public Timestamp getAtualizacao() {
		return this.atualizacao;
	}

	public void setAtualizacao(Timestamp atualizacao) {
		this.atualizacao = atualizacao;
	}

	public BigDecimal getDesconto() {
		return this.desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public Date getDtVcto() {
		return this.dtVcto;
	}

	public void setDtVcto(Date dtVcto) {
		this.dtVcto = dtVcto;
	}

	public BigDecimal getJuros() {
		return this.juros;
	}

	public void setJuros(BigDecimal juros) {
		this.juros = juros;
	}

	public BigDecimal getMora() {
		return this.mora;
	}

	public void setMora(BigDecimal mora) {
		this.mora = mora;
	}

	public BigDecimal getOutros() {
		return this.outros;
	}

	public void setOutros(BigDecimal outros) {
		this.outros = outros;
	}

	public String getParcela() {
		return this.parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public SituacaoParcela getSituacao() {
		return this.situacao;
	}

	public void setSituacao(SituacaoParcela situacao) {
		this.situacao = situacao;
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

	public Pgto getPgto() {
		return this.pgto;
	}

	public void setPgto(Pgto pgto) {
		this.pgto = pgto;
	}

	public List<PgtoQuitacao> getPgtoQuitacaos() {
		return this.pgtoQuitacaos;
	}

	public void setPgtoQuitacaos(List<PgtoQuitacao> pgtoQuitacaos) {
		this.pgtoQuitacaos = pgtoQuitacaos;
	}

	public PgtoQuitacao addPgtoQuitacao(PgtoQuitacao pgtoQuitacao) {
		getPgtoQuitacaos().add(pgtoQuitacao);
		pgtoQuitacao.setPgtoParcela(this);

		return pgtoQuitacao;
	}

	public PgtoQuitacao removePgtoQuitacao(PgtoQuitacao pgtoQuitacao) {
		getPgtoQuitacaos().remove(pgtoQuitacao);
		pgtoQuitacao.setPgtoParcela(null);

		return pgtoQuitacao;
	}

}