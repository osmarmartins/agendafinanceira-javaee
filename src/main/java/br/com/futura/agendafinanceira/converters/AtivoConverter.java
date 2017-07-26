package br.com.futura.agendafinanceira.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.futura.agendafinanceira.models.enums.Ativo;

@FacesConverter(forClass=Ativo.class)
public class AtivoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {

		System.out.println(string);
		
		return Ativo.INATIVO;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Ativo resultado = (Ativo) object;
		
		System.out.println(resultado.isValor());

		return "on";
	}



}
