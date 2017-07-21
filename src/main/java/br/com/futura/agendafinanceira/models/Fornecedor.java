package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoPessoa;

@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fornecedor")
	private Long idFornecedor;

	@Column(name = "nome_fantasia")
	@NotBlank(message = "Nome/Nome fantasia fantasia não informado.")
	private String nomeFantasia;

	@Column(name = "razao_social")
	@NotBlank(message = "Razão social não informada.")
	private String razaoSocial;

	@Enumerated
	@Column(name = "pf_pj")
	@NotNull(message = "Informe o tipo de pessoa PF/PJ.")
	private TipoPessoa tipo;

	@Column(name = "cpf_cnpj")
	private String documento;

	@Enumerated
	@NotNull(message = "Informe se o fornecedor está ativo ou não.")
	private Ativo ativo;

	@OneToMany(mappedBy = "fornecedor")
	private List<Contato> contatos;

	public Boolean isPossuiContatos() {
		return contatos.size() > 0;
	}

	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Contato addContato(Contato contato) {
		getContatos().add(contato);
		contato.setFornecedor(this);

		return contato;
	}

	// public Contato removeContato(Contato contato) {
	// getContatos().remove(contato);
	// contato.setFornecedor(null);
	//
	// return contato;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFornecedor == null) ? 0 : idFornecedor.hashCode());
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
		Fornecedor other = (Fornecedor) obj;
		if (idFornecedor == null) {
			if (other.idFornecedor != null)
				return false;
		} else if (!idFornecedor.equals(other.idFornecedor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FornecedorModel [idFornecedor=" + idFornecedor + ", nomeFantasia=" + nomeFantasia + ", razaoSocial="
				+ razaoSocial + ", tipo=" + tipo + ", documento=" + documento + ", ativo=" + ativo + "]";
	}

}
