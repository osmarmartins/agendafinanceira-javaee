package br.com.futura.agendafinanceira.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_preferencias")
public class UsuarioPreferencias implements Serializable {
	
	private static final long serialVersionUID = 4876122616603261727L;
	
	@Id
	@Column(name = "id_usuario")
	private Integer id;
	private String tema;
	private String estilo;
	private String margens;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario; 
	
	@Column(name = "submenu_flutuante")
	private String submenuFlutuante;
	
	@Column(name = "menu_compacto")
	private String menuCompacto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String getMargens() {
		return margens;
	}

	public void setMargens(String margens) {
		this.margens = margens;
	}

	public String getSubmenuFlutuante() {
		return submenuFlutuante;
	}

	public void setSubmenuFlutuante(String submenuFlutuante) {
		this.submenuFlutuante = submenuFlutuante;
	}

	public String getMenuCompacto() {
		return menuCompacto;
	}

	public void setMenuCompacto(String menuCompacto) {
		this.menuCompacto = menuCompacto;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UsuarioPreferencias other = (UsuarioPreferencias) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioPreferencias [id=" + id + ", tema=" + tema + ", estilo=" + estilo + ", margens=" + margens
				+ ", submenuFlutuante=" + submenuFlutuante + ", menuCompacto=" + menuCompacto + "]";
	}
	
}
