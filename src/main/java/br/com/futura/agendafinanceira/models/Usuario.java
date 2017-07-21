package br.com.futura.agendafinanceira.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoUsuario;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;

	@Size(max = 100, message = "Não é permitido mais de 100 caracteres para o nome")
	@NotEmpty(message = "Informe o nome do usuário")
	private String nome;

	@Size(max = 150, message = "Não é permitido mais de 150 caracteres para o e-mail")
	@Email(message = "e-mail inválido")
	private String email;

	@Size(max = 50, message = "Não é permitido mais de 50 caracteres para o login")
	@NotBlank(message = "Informe o login")
	private String login;

	private String senha;

	@Transient
	private String confirmaSenha;

	@Enumerated
	@NotNull(message = "Informe o tipo de usuário")
	private TipoUsuario administrador;

	@Enumerated
	@NotNull(message = "Informe se o usuário está ativo ou não")
	private Ativo ativo;

	public boolean isNovo() {
		return idUsuario == null;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public TipoUsuario getAdministrador() {
		return administrador;
	}

	public void setAdministrador(TipoUsuario administrador) {
		this.administrador = administrador;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioModel [idUsuario=" + idUsuario + ", nome=" + nome + ", email=" + email + ", login=" + login
				+ ", senha=" + senha + ", confirmaSenha=" + confirmaSenha + ", administrador=" + administrador
				+ ", ativo=" + ativo + "]";
	}

}
