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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUsuarioOperacao;
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
		UsuarioOperacao other = (UsuarioOperacao) obj;
		if (idUsuarioOperacao != other.idUsuarioOperacao)
			return false;
		return true;
	}

}