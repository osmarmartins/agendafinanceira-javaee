package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import javax.persistence.*;

import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoUsuario;

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

	@Enumerated
	private TipoUsuario administrador;

	@Enumerated
	private Ativo ativo;

	@Column(name="atualizado_data")
	private Timestamp atualizadoData;

	private String email;

	private String login;

	private String nome;

	private String senha;

	@Version
	private int versao;

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

	public TipoUsuario getAdministrador() {
		return this.administrador;
	}

	public void setAdministrador(TipoUsuario administrador) {
		this.administrador = administrador;
	}

	public Ativo getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Ativo ativo) {
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
		usuarioOperacao.setUsuario(this);

		return usuarioOperacao;
	}

	public UsuarioOperacao removeUsuarioOperacao(UsuarioOperacao usuarioOperacao) {
		getUsuarioOperacaos().remove(usuarioOperacao);
		usuarioOperacao.setUsuario(null);

		return usuarioOperacao;
	}

}