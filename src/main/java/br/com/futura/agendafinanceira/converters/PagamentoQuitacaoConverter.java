package br.com.futura.agendafinanceira.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.PagamentoQuitacaoDao;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;

@FacesConverter(forClass = PagamentoQuitacao.class)
public class PagamentoQuitacaoConverter implements Converter {

	@Inject
	private PagamentoQuitacaoDao quitacaoDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			return (PagamentoQuitacao) quitacaoDao.pesquisaPorId(Integer.parseInt(value));  
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && ((PagamentoQuitacao) value).getIdPgtoQuitacao() != null) {
			return ((PagamentoParcela) value).getIdPagamentoParcela().toString();
		}
		return null;
	}

}
