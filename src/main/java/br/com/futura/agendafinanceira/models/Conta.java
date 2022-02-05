package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.futura.agendafinanceira.models.enums.Ativo;

@Entity
@Table(name = "conta")
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta")
	private Integer idConta;

	@Enumerated
	private Ativo ativo;

	// @Length(min=3, message="Descrição não pode ter menos que três caracteres")
	private String descricao;

	@Version
	private Integer versao;

	@Transient
	private Boolean status;

	// bi-directional many-to-one association to Pgto
	@OneToMany(mappedBy = "conta")
	private List<Pagamento> pgtos;
	
	@OneToMany(mappedBy = "conta", fetch = FetchType.LAZY)
	private List<Fornecedor> forncedores = new ArrayList<Fornecedor>();

	public Conta() {
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public List<Pagamento> getPgtos() {
		return this.pgtos;
	}

	public void setPgtos(List<Pagamento> pgtos) {
		this.pgtos = pgtos;
	}

	public Pagamento addPgto(Pagamento pgto) {
		getPgtos().add(pgto);
		pgto.setConta(this);

		return pgto;
	}

	public Pagamento removePgto(Pagamento pgto) {
		getPgtos().remove(pgto);
		pgto.setConta(null);

		return pgto;
	}

	public boolean isStatus() {
		if (this.ativo == null) {
			return false;
		}
		return (this.ativo.equals(Ativo.ATIVO));
	}

	public void setStatus(boolean status) {
		this.setAtivo((status ? Ativo.ATIVO : Ativo.INATIVO));
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idConta;
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
		Conta other = (Conta) obj;
		if (idConta != other.idConta)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Conta [idConta=" + idConta + ", ativo=" + ativo + ", descricao=" + descricao + ", versao=" + versao
				+ "]";
	}

}