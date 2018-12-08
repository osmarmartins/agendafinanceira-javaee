package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "operacao")
public class Operacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_operacao")
	private Integer idOperacao;

	private String classificacao;

	private String descricao;

	@Version
	private Integer versao;

	// bi-directional many-to-one association to UsuarioOperacao
	@OneToMany(mappedBy = "operacao")
	private List<UsuarioOperacao> usuarios;

	public Operacao() {
	}

	public Integer getIdOperacao() {
		return this.idOperacao;
	}

	public void setIdOperacao(Integer idOperacao) {
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

	public Integer getVersao() {
		return this.versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public List<UsuarioOperacao> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<UsuarioOperacao> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioOperacao addUsuario(UsuarioOperacao usuario) {
		getUsuarios().add(usuario);
		usuario.setOperacao(this);
		return usuario;
	}

	public UsuarioOperacao removeUsuario(UsuarioOperacao usuario) {
		getUsuarios().remove(usuario);
		usuario.setOperacao(null);
		return usuario;
	}

}