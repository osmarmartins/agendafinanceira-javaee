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
	private int idSetor;

	@Enumerated
	private Ativo ativo;

	@NotBlank(message = "Informe um valor para o campo")
	private String descricao;

	@Version
	private int versao;

	// bi-directional many-to-one association to Pgto
	@OneToMany(mappedBy = "setor")
	private List<Pgto> pgtos;

	public Setor() {
	}

	public int getIdSetor() {
		return this.idSetor;
	}

	public void setIdSetor(int idSetor) {
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

	public int getVersao() {
		return this.versao;
	}

	public void setVersao(int versao) {
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

	@Override
	public String toString() {
		return "Setor [idSetor=" + idSetor + ", ativo=" + ativo + ", descricao=" + descricao + "]";
	}

}