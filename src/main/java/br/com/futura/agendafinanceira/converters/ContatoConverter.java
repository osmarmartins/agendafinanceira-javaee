package br.com.futura.agendafinanceira.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.ContatoDao;
import br.com.futura.agendafinanceira.models.Contato;

@FacesConverter(forClass = Contato.class)
public class ContatoConverter implements Converter {

	@Inject
	private ContatoDao contatoDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value!=null && value.trim().length()>0){
			return contatoDao.pesquisarPorId(Integer.parseInt(value));
		}
		return new Contato();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && ((Contato) value).getIdContato() != null) {
			return ((Contato) value).getIdContato().toString();
		}
		return "";
	}

}
