package br.com.futura.agendafinanceira.models.enums;

public enum FormaPagamento {
	
	DINHEIRO("Dinheiro"), 
	CARTAO("Cartao"), 
	CHEQUE("Cheque"),
	DEPOSITO("Depósito"), 
	BOLETO("Boleto"), 
	OUTROS("Outros");
	
	private String descricao;
	
	private FormaPagamento(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}
