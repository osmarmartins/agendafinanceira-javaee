package br.com.futura.agendafinanceira.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioUtil {
	public static void imprimeRelatorio(String nomeRelatorio, HashMap parametros, List<?> dados) throws JRException, IOException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		ServletContext sc = (ServletContext) context.getExternalContext().getContext();
		
		String relPath = sc.getRealPath("/relatorios") + File.separator;
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
		
		JasperPrint print = JasperFillManager.fillReport(relPath + nomeRelatorio + ".jasper", parametros, dataSource);
		response.setContentType("application/pdf");
		
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		
		ServletOutputStream responseStream = response.getOutputStream();
		responseStream.flush();
		responseStream.close();
		FacesContext.getCurrentInstance().renderResponse();
		FacesContext.getCurrentInstance().responseComplete();
		
	}
}
