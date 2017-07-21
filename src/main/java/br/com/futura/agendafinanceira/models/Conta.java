package br.com.futura.agendafinanceira.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import br.com.futura.agendafinanceira.models.enums.Ativo;

@Entity
@Table(name = "conta")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta")
	private Long idConta;

	@NotBlank
	@Size(max = 100, message = "Descrição não pode ultrapassar 100 caracteres.")
	private String descricao;

	@Enumerated
	@NotNull(message = "Informe se a conta está ativa ou não.")
	private Ativo ativo;

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
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
		result = prime * result + ((idConta == null) ? 0 : idConta.hashCode());
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
		Conta other = (Conta) obj;
		if (idConta == null) {
			if (other.idConta != null)
				return false;
		} else if (!idConta.equals(other.idConta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContaModel [idConta=" + idConta + ", descricao=" + descricao + ", ativo=" + ativo + "]";
	}

}
