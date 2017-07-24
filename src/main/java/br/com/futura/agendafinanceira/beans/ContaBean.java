package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.ContaDao;
import br.com.futura.agendafinanceira.models.Conta;

@Model
public class ContaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Conta conta;

	private List<Conta> contas = new ArrayList<Conta>();

	@Inject
	private ContaDao contaDao;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@PostConstruct
	private void init() {
		this.contas = contaDao.listarContas();
		for (Conta conta : contas) {
			System.out.println(conta.toString());
		}
	}

	public List<Conta> getContas() {
		return contas;
	}
}
