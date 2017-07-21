package br.com.futura.agendafinanceira.models.enums;

public enum SituacaoPagamento {
	
	NOVO("Novo"), EMABERTO("Em Aberto"), FINALIZADO("Finalizado");
	
	private String descricao;
	
	private SituacaoPagamento(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}
