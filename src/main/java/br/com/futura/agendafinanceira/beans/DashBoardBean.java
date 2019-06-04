package br.com.futura.agendafinanceira.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;

import br.com.futura.agendafinanceira.services.DashBoardService;

@Model
public class DashBoardBean {

	@Inject
	private DashBoardService service;

	private LineChartModel graficoDespesas;
	private HorizontalBarChartModel graficoContas;

	@PostConstruct
	private void init() {
		this.graficoDespesas = new LineChartModel();
		this.graficoContas = new HorizontalBarChartModel();
		service.gerarGraficoDespesas(graficoDespesas);
		service.gerarGraficoContas(graficoContas);
	}

	public LineChartModel getGraficoDespesas() {
		return graficoDespesas;
	}

	public HorizontalBarChartModel getGraficoContas() {
		return graficoContas;
	}

}
