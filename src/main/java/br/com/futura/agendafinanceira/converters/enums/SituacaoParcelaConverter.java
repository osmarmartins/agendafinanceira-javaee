package br.com.futura.agendafinanceira.converters.enums;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;

@FacesConverter(forClass=SituacaoParcela.class)
public class SituacaoParcelaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		SituacaoParcela[] valores = SituacaoParcela.values();
		for (SituacaoParcela situacaoParcela : valores) {
			if (value.equals(situacaoParcela.getDescricao())){
				return situacaoParcela;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((SituacaoParcela) value).getDescricao();
	}

}
