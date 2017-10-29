package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.ContatoDao;
import br.com.futura.agendafinanceira.models.Contato;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class ContatoCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Contato contato;

	private Fornecedor fornecedor;

	@Inject
	private ContatoDao contatoDao;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.contato = new Contato();
		this.contato.setFornecedor(fornecedor);
		this.contato.setAtivo(Ativo.ATIVO);
	}

	public String voltar() {
		return "/fornecedorcadastro.xhtml?fornecedor=" + fornecedor.getIdFornecedor();
	}

	public void salvar() {
		contato.setFornecedor(this.fornecedor);
		contatoDao.salvar(contato);
		messagesHelper.addFlash(new FacesMessage("Operação concluida com sucesso."));
	}

	public void excluir(Contato contato) {
		contatoDao.excluir(contato);
		contato.getFornecedor().removeContato(contato);
		messagesHelper.addFlash(new FacesMessage("Operação concluida com sucesso."));
	}

	public Contato getContato() {
		if (contato == null) {
			init();
		}
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
