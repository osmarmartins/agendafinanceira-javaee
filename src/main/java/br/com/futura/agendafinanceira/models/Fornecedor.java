package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoPessoa;

@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fornecedor")
	private Integer idFornecedor;
	
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;

	@Enumerated
	private Ativo ativo;

	@Column(name = "cpf_cnpj")
	private String cpfCnpj;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	@Enumerated
	@NotNull
	@Column(name = "pf_pj")
	private TipoPessoa pfPj;

	@Column(name = "razao_social")
	private String razaoSocial;

	@Version
	private Integer versao;

	@Transient
	private boolean status;

	// bi-directional many-to-one association to Contato
	@OneToMany(mappedBy = "fornecedor")
	private List<Contato> contatos;

	// bi-directional many-to-one association to Pgto
	@OneToMany(mappedBy = "fornecedor")
	private List<Pagamento> pagamentos;

	public Fornecedor() {
	}

	public Integer getIdFornecedor() {
		return this.idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Ativo getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public String getCpfCnpj() {
		return this.cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomeFantasia() {
		return this.nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public TipoPessoa getPfPj() {
		return this.pfPj;
	}

	public void setPfPj(TipoPessoa pfPj) {
		this.pfPj = pfPj;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public int getVersao() {
		return this.versao;
	}
	
	public Conta getConta() {
		return conta;
	}
	
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public List<Contato> getContatos() {
		return this.contatos == null? Collections.emptyList() : this.contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Contato addContato(Contato contato) {
		getContatos().add(contato);
		contato.setFornecedor(this);

		return contato;
	}

	public Contato removeContato(Contato contato) {
		getContatos().remove(contato);
		contato.setFornecedor(null);
		
		return contato;
	}
	
	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
	
	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
	public Pagamento addPagamento(Pagamento pagamento) {
		getPagamentos().add(pagamento);
		pagamento.setFornecedor(this);
		return pagamento;
	}
	
	public Pagamento removePagamento(Pagamento pagamento){
		getPagamentos().remove(pagamento);
		pagamento.setFornecedor(null);
		return pagamento;
	}

	public boolean isStatus() {
		if (this.ativo == null){
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
		return "Fornecedor [idFornecedor=" + idFornecedor + ", ativo=" + ativo + ", cpfCnpj=" + cpfCnpj
				+ ", nomeFantasia=" + nomeFantasia + ", pfPj=" + pfPj + ", razaoSocial=" + razaoSocial + ", versao="
				+ versao + ", status=" + status + "]";
	}

}