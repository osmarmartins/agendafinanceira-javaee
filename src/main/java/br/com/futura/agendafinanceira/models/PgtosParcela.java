package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pgtos_parcela database table.
 * 
 */
@Entity
@Table(name="pgtos_parcela")
@NamedQuery(name="PgtosParcela.findAll", query="SELECT p FROM PgtosParcela p")
public class PgtosParcela implements Serializable {
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

	private byte situacao;

	private BigDecimal valor;

	//bi-directional many-to-one association to Pgto
	@ManyToOne
	@JoinColumn(name="id_pgto")
	private Pgto pgto;

	//bi-directional many-to-one association to PgtosQuitacao
	@OneToMany(mappedBy="pgtosParcela")
	private List<PgtosQuitacao> pgtosQuitacaos;

	public PgtosParcela() {
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

	public byte getSituacao() {
		return this.situacao;
	}

	public void setSituacao(byte situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Pgto getPgto() {
		return this.pgto;
	}

	public void setPgto(Pgto pgto) {
		this.pgto = pgto;
	}

	public List<PgtosQuitacao> getPgtosQuitacaos() {
		return this.pgtosQuitacaos;
	}

	public void setPgtosQuitacaos(List<PgtosQuitacao> pgtosQuitacaos) {
		this.pgtosQuitacaos = pgtosQuitacaos;
	}

	public PgtosQuitacao addPgtosQuitacao(PgtosQuitacao pgtosQuitacao) {
		getPgtosQuitacaos().add(pgtosQuitacao);
		pgtosQuitacao.setPgtosParcela(this);

		return pgtosQuitacao;
	}

	public PgtosQuitacao removePgtosQuitacao(PgtosQuitacao pgtosQuitacao) {
		getPgtosQuitacaos().remove(pgtosQuitacao);
		pgtosQuitacao.setPgtosParcela(null);

		return pgtosQuitacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPgtoParcela;
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
		PgtosParcela other = (PgtosParcela) obj;
		if (idPgtoParcela != other.idPgtoParcela)
			return false;
		return true;
	}

}