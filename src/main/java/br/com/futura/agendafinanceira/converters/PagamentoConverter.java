package br.com.futura.agendafinanceira.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.ContatoDao;
import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.models.Contato;
import br.com.futura.agendafinanceira.models.Pagamento;

@FacesConverter(forClass = Pagamento.class)
public class PagamentoConverter implements Converter {

	@Inject
	private PagamentoDao pagamentoDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value!=null && value.trim().length()>0){
			return pagamentoDao.pesquisarPorId(Integer.parseInt(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && ((Pagamento) value).getIdPagamento() != null) {
			return ((Pagamento) value).getIdPagamento().toString();
		}
		return "";
	}

}
