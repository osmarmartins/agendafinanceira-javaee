package br.com.futura.agendafinanceira.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.FornecedorDao;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.enums.TipoPessoa;

@Model
public class FornecedorCadastroBean {

	private Fornecedor fornecedor;
	
	private TipoPessoa[] tipoPessoas;
	
	@Inject
	private FornecedorDao fornecedorDao;

	@PostConstruct
	private void init() {
		this.fornecedor = new Fornecedor();
		this.tipoPessoas = TipoPessoa.values();
	}
	
	public void salvar(){
		fornecedorDao.salvar(fornecedor);
	}

	public Fornecedor getFornecedor() {
		if (fornecedor.getIdFornecedor() == null) {
			init();
		}
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public TipoPessoa[] getTipoPessoas() {
		return tipoPessoas;
	}
}
