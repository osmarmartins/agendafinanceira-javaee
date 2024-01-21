package br.com.futura.agendafinanceira.models.enums;

public enum TipoLancamento {
	MENSAL_LANCAMENTO_UMA_PARCELA("Mensal com uma parcela por lançamento"), 
	MENSAL_LANCAMENTO_VARIAS_PARCELAS("Mensal com várias parcelas por lançamento"), 
	INTERVALO_DIAS("Intervalo de dias entre as parcelas");
	
	private String descricao;

	private TipoLancamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
