package br.com.futura.agendafinanceira.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "usuario_operacao")
public class UsuarioOperacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_operacao")
	private Integer idUsuarioOperacao;

	@Version
	private Integer versao;

	// bi-directional many-to-one association to Operacao
	@ManyToOne
	@JoinColumn(name = "id_operacao")
	private Operacao operacao;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public UsuarioOperacao() {
	}

	public Integer getIdUsuarioOperacao() {
		return this.idUsuarioOperacao;
	}

	public void setIdUsuarioOperacao(Integer idUsuarioOperacao) {
		this.idUsuarioOperacao = idUsuarioOperacao;
	}

	public Integer getVersao() {
		return this.versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public Operacao getOperacao() {
		return this.operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}