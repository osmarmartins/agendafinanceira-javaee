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

/**
 * The persistent class for the usuario_operacao database table.
 * 
 */
@Entity
@Table(name = "usuario_operacao")
@NamedQuery(name = "UsuarioOperacao.findAll", query = "SELECT u FROM UsuarioOperacao u")
public class UsuarioOperacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_operacao")
	private int idUsuarioOperacao;

	@Version
	private int versao;

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

	public int getIdUsuarioOperacao() {
		return this.idUsuarioOperacao;
	}

	public void setIdUsuarioOperacao(int idUsuarioOperacao) {
		this.idUsuarioOperacao = idUsuarioOperacao;
	}

	public int getVersao() {
		return this.versao;
	}

	public void setVersao(int versao) {
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