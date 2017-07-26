package br.com.futura.agendafinanceira.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.models.enums.Ativo;

@Model
public class SetorCadastroBean {
	
	@Inject
	private Setor setor;
	
	public Setor getSetor() {
		return this.setor;
	}
	
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	@PostConstruct
	private void init(){
		this.setor.setAtivo(Ativo.ATIVO);
	}
	
	public void salvar(ActionEvent event){
		System.out.println("Salvar setor: " + this.setor.toString());
	}
}
