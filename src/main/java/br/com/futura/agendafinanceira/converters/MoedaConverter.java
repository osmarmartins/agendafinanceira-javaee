package br.com.futura.agendafinanceira.converters;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("moedaConverter")
public class MoedaConverter implements Converter {

	private DecimalFormat df = new DecimalFormat("#,##0.00");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}

		System.out.println("CONVESOR DE STRIG para MOEDA" + value);
		
		String valor = value.replace("R$ ", "").replace(".", "").replace(",", ".");
		
		return new BigDecimal(valor);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		
		System.out.println("CONVESOR DE MOEDA para STRING" + value);
		
		String valorMoeda = "R$ " + df.format(value);
		return valorMoeda;
	}

}
