package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Min;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoUsuario;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Min(value=3, message="Nome não pode ser menor que três letras")
	private String nome;

//	@Email
	private String email;

	@Min(value=3, message="Login não pode ter menos que três caracteres")
	private String login;

	@Min(value=3, message="O tamanho da senha não pode ser inferior a três caracteres")
	private String senha;

	@Enumerated
	private TipoUsuario administrador;

	@Enumerated
	private Ativo ativo;

	@Version
	private Integer versao;

	@Transient
	private boolean status;

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "login_role_usuario", 
		joinColumns = {@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario") }, 
		inverseJoinColumns = {@JoinColumn(name = "id_role", referencedColumnName = "id_role") })
	private List<Autorizacao> permissoes;
	
	public List<Autorizacao> getPermissoes() {
		if (this.permissoes.size() == 0) {
			return Collections.EMPTY_LIST;
		}
		
		// TODO: Ordenar por classificação
		
		return permissoes;
	}
	
	public Autorizacao addRole(Autorizacao role) {
		this.permissoes.add(role);
		role.getUsuarios().add(this);
		return role;
	}
	
	public Autorizacao removeRole(Autorizacao role) {
		this.permissoes.remove(role);
		role.getUsuarios().remove(this);
		return role;
	}
	
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
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

	public Integer getVersao() {
		return this.versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public boolean isStatus() {
		if (this.ativo == null) {
			return false;
		}
		return (this.ativo.equals(Ativo.ATIVO));
	}

	public void setStatus(boolean status) {
		this.setAtivo(status ? Ativo.ATIVO : Ativo.INATIVO);
		this.status = status;
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

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", administrador=" + administrador + ", ativo=" + ativo + ", email="
				+ email + ", login=" + login + ", nome=" + nome + ", senha=" + senha + ", versao=" + versao + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissoes;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.ativo==null? false : this.ativo==Ativo.ATIVO;
	}

}