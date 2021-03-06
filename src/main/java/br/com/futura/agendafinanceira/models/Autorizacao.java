package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import br.com.futura.agendafinanceira.models.enums.Ativo;

@Entity
@Table(name="login_role")
public class Autorizacao implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_role")
	private Integer idRole;
	
	private String role;
	
	private String classificacao;
	
	private String descricao;
	
	
	@Enumerated
	private Ativo ativo;

	@ManyToMany(mappedBy = "permissoes")
	private List<Usuario> usuarios;
	
	public Usuario addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		usuario.getPermissoes().add(this);
		return usuario;
	}
	
	public Usuario removeUsuario(Usuario usuario) {
		this.usuarios.remove(usuario);
		usuario.getPermissoes().remove(this);
		return usuario;
	}
	
	@Override
	public String getAuthority() {
		return this.role;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
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
		Autorizacao other = (Autorizacao) obj;
		if (idRole == null) {
			if (other.idRole != null)
				return false;
		} else if (!idRole.equals(other.idRole))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Autorizacao [idRole=" + idRole + ", role=" + role + ", classificacao=" + classificacao + ", descricao="
				+ descricao + ", ativo=" + ativo + "]";
	}


}
