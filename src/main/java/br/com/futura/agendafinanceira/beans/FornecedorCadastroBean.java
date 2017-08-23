package br.com.futura.agendafinanceira.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.FornecedorDao;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoPessoa;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Model
public class FornecedorCadastroBean {

	private Fornecedor fornecedor;
	
	private TipoPessoa[] tipoPessoas;
	
	@Inject
	private FornecedorDao fornecedorDao;
	
	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.fornecedor = new Fornecedor();
		this.fornecedor.setAtivo(Ativo.ATIVO);
		this.fornecedor.setPfPj(TipoPessoa.PJ);
		this.tipoPessoas = TipoPessoa.values();
		System.out.println(fornecedor);
	}
	
	public void salvar(){
		fornecedorDao.salvar(fornecedor);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();		
	}

	public Fornecedor getFornecedor() {
		if (this.fornecedor == null) {
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
