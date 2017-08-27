package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.FornecedorDao;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoPessoa;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class FornecedorCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor;
	
	private TipoPessoa[] tipoPessoas;
	
	private String documentoMascara;
	
	private String documentoRotulo;
	
	@Inject
	private FornecedorDao fornecedorDao;
	
	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.fornecedor = new Fornecedor();
		this.tipoPessoas = TipoPessoa.values();
		this.fornecedor.setAtivo(Ativo.ATIVO);
		this.fornecedor.setPfPj(TipoPessoa.PJ);
	}
	
	public void salvar(){
		fornecedorDao.salvar(fornecedor);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();		
	}

	public void definirDocumento() {
		this.documentoMascara = ((TipoPessoa) this.fornecedor.getPfPj()).getMascara();
		this.documentoRotulo = ((TipoPessoa) this.fornecedor.getPfPj()).getRotulo();
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
	
	public String getDocumentoMascara() {
		return documentoMascara;
	}
	
	public String getDocumentoRotulo() {
		return documentoRotulo;
	}
	
}
