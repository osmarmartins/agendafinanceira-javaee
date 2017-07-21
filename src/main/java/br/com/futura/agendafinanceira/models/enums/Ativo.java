package br.com.futura.agendafinanceira.models.enums;

public enum Ativo {
	
	INATIVO("Inativo", false), 
	ATIVO("Ativo", true);
	
	private String descricao;
	private boolean valor;
	
	private Ativo(String descricao, boolean valor){
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getDescricao(){
		return descricao;
	}

	public boolean isValor() {
		return valor;
	}
	
	
		
}
