package br.com.futura.agendafinanceira.models.enums;

public enum SituacaoParcela {

	NOVO("Novo"), AGENDADO("Agendado"), PROGRAMADO("Programado"), 
	LIQUIDADO("Liquidado"), BAIXADO("Baixado"), CANCELADO("Cancelado");

	private String descricao;

	private SituacaoParcela(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
