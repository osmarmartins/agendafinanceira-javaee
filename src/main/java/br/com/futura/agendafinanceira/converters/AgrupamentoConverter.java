package br.com.futura.agendafinanceira.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.AgrupamentoDao;
import br.com.futura.agendafinanceira.models.Agrupamento;

@FacesConverter(forClass = Agrupamento.class)
public class AgrupamentoConverter implements Converter {
	
	@Inject
	private AgrupamentoDao dao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			return dao.pesquisarPorId(Integer.valueOf(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && ((Agrupamento) value).getId() != null) {
			return ((Agrupamento) value).getId().toString();
		}
		return "";
	}

}
