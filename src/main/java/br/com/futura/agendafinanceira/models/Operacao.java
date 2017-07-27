package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * The persistent class for the operacao database table.
 * 
 */
@Entity
@Table(name = "operacao")
@NamedQuery(name = "Operacao.findAll", query = "SELECT o FROM Operacao o")
public class Operacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_operacao")
	private int idOperacao;

	private String classificacao;

	private String descricao;

	@Version
	private int versao;

	// bi-directional many-to-one association to UsuarioOperacao
	@OneToMany(mappedBy = "operacao")
	private List<UsuarioOperacao> usuarioOperacaos;

	public Operacao() {
	}

	public int getIdOperacao() {
		return this.idOperacao;
	}

	public void setIdOperacao(int idOperacao) {
		this.idOperacao = idOperacao;
	}

	public String getClassificacao() {
		return this.classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
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

	public List<UsuarioOperacao> getUsuarioOperacaos() {
		return this.usuarioOperacaos;
	}

	public void setUsuarioOperacaos(List<UsuarioOperacao> usuarioOperacaos) {
		this.usuarioOperacaos = usuarioOperacaos;
	}

	public UsuarioOperacao addUsuarioOperacao(UsuarioOperacao usuarioOperacao) {
		getUsuarioOperacaos().add(usuarioOperacao);
		usuarioOperacao.setOperacao(this);

		return usuarioOperacao;
	}

	public UsuarioOperacao removeUsuarioOperacao(UsuarioOperacao usuarioOperacao) {
		getUsuarioOperacaos().remove(usuarioOperacao);
		usuarioOperacao.setOperacao(null);

		return usuarioOperacao;
	}

}