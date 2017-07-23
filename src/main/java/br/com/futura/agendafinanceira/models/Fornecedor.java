package br.com.futura.agendafinanceira.models;

import java.io.Serializable;
import javax.persistence.*;

import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoPessoa;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the fornecedor database table.
 * 
 */
@Entity
@Table(name="fornecedor")
@NamedQuery(name="Fornecedor.findAll", query="SELECT f FROM Fornecedor f")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fornecedor")
	private int idFornecedor;

	@Enumerated
	private Ativo ativo;

	private Timestamp atualizacao;

	@Column(name="cpf_cnpj")
	private String cpfCnpj;

	@Column(name="nome_fantasia")
	private String nomeFantasia;

	@Enumerated
	@Column(name="pf_pj")
	private TipoPessoa pfPj;

	@Column(name="razao_social")
	private String razaoSocial;

	@Version
	private int versao;

	//bi-directional many-to-one association to Contato
	@OneToMany(mappedBy="fornecedor")
	private List<Contato> contatos;

	//bi-directional many-to-one association to Pgto
	@OneToMany(mappedBy="fornecedor")
	private List<Pgto> pgtos;

	public Fornecedor() {
	}

	public int getIdFornecedor() {
		return this.idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Ativo getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public Timestamp getAtualizacao() {
		return this.atualizacao;
	}

	public void setAtualizacao(Timestamp atualizacao) {
		this.atualizacao = atualizacao;
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

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public List<Contato> getContatos() {
		return this.contatos;
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

	public List<Pgto> getPgtos() {
		return this.pgtos;
	}

	public void setPgtos(List<Pgto> pgtos) {
		this.pgtos = pgtos;
	}

	public Pgto addPgto(Pgto pgto) {
		getPgtos().add(pgto);
		pgto.setFornecedor(this);

		return pgto;
	}

	public Pgto removePgto(Pgto pgto) {
		getPgtos().remove(pgto);
		pgto.setFornecedor(null);

		return pgto;
	}

}