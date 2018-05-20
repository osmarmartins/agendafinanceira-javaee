package br.com.futura.agendafinanceira.converters.enums;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.futura.agendafinanceira.models.enums.SituacaoPagamento;

@FacesConverter(forClass=SituacaoPagamento.class)
public class SituacaoPagamentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		SituacaoPagamento[] valores = SituacaoPagamento.values();
		for (SituacaoPagamento situacaoPagamento : valores) {
			if (value.equals(situacaoPagamento.getDescricao())){
				return situacaoPagamento;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((SituacaoPagamento) value).getDescricao();
	}

}
