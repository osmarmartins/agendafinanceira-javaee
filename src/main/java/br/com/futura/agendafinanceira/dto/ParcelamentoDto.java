package br.com.futura.agendafinanceira.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.enums.TipoLancamento;

public class ParcelamentoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pagamento pagamento;
	private Integer quantidadeParcelas;
	private BigDecimal valorParcela;
	private Date primeiroVencimento;
	private Integer intervaloDias;
	private TipoLancamento tipoLancamento;

	public ParcelamentoDto(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public ParcelamentoDto() {
	}
	
	public String obterParcela(Integer numeroParcela) {
		return numeroParcela.toString() + '/' + this.quantidadeParcelas.toString();
	}
	
	public Date calcularVencimento(Integer numeroParcela) {
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.setTime(this.primeiroVencimento);
		Integer dia = dataInicial.get(Calendar.DAY_OF_MONTH);
		Integer mes = dataInicial.get(Calendar.MONTH) - 1;
		Integer ano = dataInicial.get(Calendar.YEAR);
		
		ano = ano + numeroParcela / 12;
		mes = mes + numeroParcela % 12;
		
		Calendar vencimento = Calendar.getInstance(); 
		vencimento.set(Calendar.YEAR, ano);
		vencimento.set(Calendar.MONTH, mes);
		Integer ultimoDia = vencimento.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (dia > ultimoDia) {
			vencimento.set(Calendar.DAY_OF_MONTH, ultimoDia);
		}else {
			vencimento.set(Calendar.DAY_OF_MONTH, dia);
		}

		return vencimento.getTime();  
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Date getPrimeiroVencimento() {
		return primeiroVencimento;
	}

	public void setPrimeiroVencimento(Date primeiroVencimento) {
		this.primeiroVencimento = primeiroVencimento;
	}

	public Integer getIntervaloDias() {
		return intervaloDias;
	}

	public void setIntervaloDias(Integer intervaloDias) {
		this.intervaloDias = intervaloDias;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	@Override
	public String toString() {
		return "ParcelamentoDto [pagamento=" + pagamento + ", quantidadeParcelas=" + quantidadeParcelas
				+ ", valorParcela=" + valorParcela + ", primeiroVencimento=" + primeiroVencimento + ", intervaloDias="
				+ intervaloDias + ", tipoLancamento=" + tipoLancamento + "]";
	}
	
	public static void main(String[] args) {
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2019);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 31);
		
		ParcelamentoDto p = new ParcelamentoDto();
		p.setPrimeiroVencimento(c.getTime());
		p.setValorParcela(new BigDecimal(17.99));
		
		for (int i = 1; i < 10; i++) {
			c.setTime(p.calcularVencimento(i));
			System.out.println("Parcela nº " + i + ": " + c.getTime());
			System.out.println("DIA: " + c.get(Calendar.DAY_OF_MONTH));
			System.out.println("MES: " + c.get(Calendar.MONTH));
			System.out.println("ANO: " + c.get(Calendar.YEAR));
			System.out.println("--------------------------------------------");
		}
		
	}

}
