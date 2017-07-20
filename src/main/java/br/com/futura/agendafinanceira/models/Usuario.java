package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;

	private byte administrador;

	private byte ativo;

	@Column(name="atualizado_data")
	private Timestamp atualizadoData;

	private String email;

	private String login;

	private String nome;

	private String senha;

	//bi-directional many-to-one association to UsuarioOperacao
	@OneToMany(mappedBy="usuario")
	private List<UsuarioOperacao> usuarioOperacaos;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public byte getAdministrador() {
		return this.administrador;
	}

	public void setAdministrador(byte administrador) {
		this.administrador = administrador;
	}

	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
		this.ativo = ativo;
	}

	public Timestamp getAtualizadoData() {
		return this.atualizadoData;
	}

	public void setAtualizadoData(Timestamp atualizadoData) {
		this.atualizadoData = atualizadoData;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<UsuarioOperacao> getUsuarioOperacaos() {
		return this.usuarioOperacaos;
	}

	public void setUsuarioOperacaos(List<UsuarioOperacao> usuarioOperacaos) {
		this.usuarioOperacaos = usuarioOperacaos;
	}

	public UsuarioOperacao addUsuarioOperacao(UsuarioOperacao usuarioOperacao) {
		getUsuarioOperacaos().add(usuarioOperacao);
		usuarioOperacao.setUsuario(this);

		return usuarioOperacao;
	}

	public UsuarioOperacao removeUsuarioOperacao(UsuarioOperacao usuarioOperacao) {
		getUsuarioOperacaos().remove(usuarioOperacao);
		usuarioOperacao.setUsuario(null);

		return usuarioOperacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUsuario;
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
		Usuario other = (Usuario) obj;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}

}