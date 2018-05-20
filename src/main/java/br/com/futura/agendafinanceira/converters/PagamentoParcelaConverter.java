package br.com.futura.agendafinanceira.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.PagamentoParcelaDao;
import br.com.futura.agendafinanceira.models.PagamentoParcela;

@FacesConverter(forClass=PagamentoParcela.class)
public class PagamentoParcelaConverter implements Converter {
	
	@Inject
	private PagamentoParcelaDao parcelaDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value!=null && value.trim().length()>0){
			System.out.println(">>>>>>>>>>>>> "+component.getClass().getName()+"-" + value);
			return (PagamentoParcela) parcelaDao.pesquisaPorId(Integer.parseInt(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && ((PagamentoParcela) value).getIdPagamentoParcela()!=null){
			return ((PagamentoParcela) value).getIdPagamentoParcela().toString();
		}
		return null;
	}

}
