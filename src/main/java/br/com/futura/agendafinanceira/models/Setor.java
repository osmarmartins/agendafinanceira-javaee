package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotBlank;

import br.com.futura.agendafinanceira.models.enums.Ativo;

/**
 * The persistent class for the setor database table.
 * 
 */
@Entity
@Table(name = "setor")
@NamedQuery(name = "Setor.findAll", query = "SELECT s FROM Setor s")
public class Setor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_setor")
	private Integer idSetor;

	@Enumerated
	private Ativo ativo;

	@NotBlank(message = "Informe um valor para o campo")
	private String descricao;

	@Version
	private Integer versao;

	@Transient
	private boolean status;

	// bi-directional many-to-one association to Pgto
	@OneToMany(mappedBy = "setor")
	private List<Pgto> pgtos;

	public Setor() {
	}

	public Integer getIdSetor() {
		return this.idSetor;
	}

	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}

	public Ativo getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getVersao() {
		return this.versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public List<Pgto> getPgtos() {
		return this.pgtos;
	}

	public void setPgtos(List<Pgto> pgtos) {
		this.pgtos = pgtos;
	}

	public Pgto addPgto(Pgto pgto) {
		getPgtos().add(pgto);
		pgto.setSetor(this);

		return pgto;
	}

	public Pgto removePgto(Pgto pgto) {
		getPgtos().remove(pgto);
		pgto.setSetor(null);

		return pgto;
	}

	public boolean isStatus() {
		if (this.ativo == null) {
			return false;
		}
		return (this.ativo.equals(Ativo.ATIVO));
	}

	public void setStatus(boolean status) {
		this.setAtivo(status ? Ativo.ATIVO : Ativo.INATIVO);
		this.status = status;
	}

	@Override
	public String toString() {
		return "Setor [idSetor=" + idSetor + ", ativo=" + ativo + ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSetor == null) ? 0 : idSetor.hashCode());
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
		Setor other = (Setor) obj;
		if (idSetor == null) {
			if (other.idSetor != null)
				return false;
		} else if (!idSetor.equals(other.idSetor))
			return false;
		return true;
	}

}