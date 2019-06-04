package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;

import br.com.futura.agendafinanceira.daos.DashBoardDao;
import br.com.futura.agendafinanceira.dto.GraficoDto;

public class DashBoardService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Date dataInicial = new Date();
	Date dataFinal = new Date();
	
	@PostConstruct
	private void init() {
		calcularUltimosDozeMeses();
	}
	
	private void calcularUltimosDozeMeses() {
		LocalDate hoje = LocalDate.now();
		int ano = hoje.getYear();
		int mes = hoje.getMonthValue();
		int ultimoDiaDoMes = hoje.getMonth().maxLength();
		
		LocalDate inicio = LocalDate.of(ano-1, mes, 1);
		LocalDate fim = LocalDate.of(ano, mes, ultimoDiaDoMes);
		
		dataInicial = Date.from(inicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
		dataFinal = Date.from(fim.atStartOfDay(ZoneId.systemDefault()).toInstant());

	}

	@Inject
	private DashBoardDao dao;
	
	public void gerarGraficoDespesas(LineChartModel model) {
		model.setTitle("DESPESAS POR MÊS");
		model.setAnimate(true);
		
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Meses"));
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Total");
        yAxis.setMin(0);

        model.addSeries(processarDespesas());
	}

	private ChartSeries processarDespesas() {

		List<GraficoDto> meses = dao.totalDespesasPorMes(dataInicial, dataFinal);
		ChartSeries series = new ChartSeries();

		for (GraficoDto mes : meses) {
			series.set(mes.getLegenda(), mes.getValor());
		}
		
		return series;
	}

	public void gerarGraficoContas(HorizontalBarChartModel model) {

		model.setTitle("DESPESAS POR CLASSIFICAÇÃO DE CONTAS");
		model.setAnimate(true);
		model.setStacked(true);
        
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Total");
        
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Contas");
        
        model.addSeries(processarContas());
	}

	private ChartSeries processarContas() {

		List<GraficoDto> contas = dao.totalDespesasPorContas(dataInicial, dataFinal);
		ChartSeries series = new ChartSeries();

		for (GraficoDto conta : contas) {
			series.set(conta.getLegenda(), conta.getValor());
		}
		
		return series;
	}	

}
