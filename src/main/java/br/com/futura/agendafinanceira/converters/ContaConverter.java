package br.com.futura.agendafinanceira.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.ContaDao;
import br.com.futura.agendafinanceira.models.Conta;

@FacesConverter(forClass = Conta.class)
public class ContaConverter implements Converter {

	@Inject
	private ContaDao contaDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			return contaDao.pesquisarPorId(Integer.valueOf(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && ((Conta) value).getIdConta() != null) {
			return ((Conta) value).getIdConta().toString();
		}
		return "";
	}

}
