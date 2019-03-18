package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.models.Pagamento;

@Named
public class PagamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PagamentoDao pagamentoDao;


	public Pagamento pesquisarPorId(Integer idPagamento) {
		return pagamentoDao.pesquisarPorId(idPagamento);
	}

	public List<Pagamento> listarTodos() {
		return pagamentoDao.listarTodos();
	}

	public List<Pagamento> listarPor(String filtro) {
		return pagamentoDao.listarPor(filtro);
	}

	@Transactional
	public void salvar(Pagamento pagamento) {
		pagamentoDao.salvar(pagamento);
	}

	@Transactional
	public void excluir(List<Pagamento> pagamentos) {
		// TODO: Validar exclusão dos pagamentos 
		pagamentoDao.excluir(pagamentos);
	}


}
