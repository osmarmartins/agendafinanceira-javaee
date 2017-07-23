package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the usuario_operacao database table.
 * 
 */
@Entity
@Table(name="usuario_operacao")
@NamedQuery(name="UsuarioOperacao.findAll", query="SELECT u FROM UsuarioOperacao u")
public class UsuarioOperacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario_operacao")
	private int idUsuarioOperacao;

	private Timestamp atualizacao;

	@Version
	private int versao;

	//bi-directional many-to-one association to Operacao
	@ManyToOne
	@JoinColumn(name="id_operacao")
	private Operacao operacao;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public UsuarioOperacao() {
	}

	public int getIdUsuarioOperacao() {
		return this.idUsuarioOperacao;
	}

	public void setIdUsuarioOperacao(int idUsuarioOperacao) {
		this.idUsuarioOperacao = idUsuarioOperacao;
	}

	public Timestamp getAtualizacao() {
		return this.atualizacao;
	}

	public void setAtualizacao(Timestamp atualizacao) {
		this.atualizacao = atualizacao;
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