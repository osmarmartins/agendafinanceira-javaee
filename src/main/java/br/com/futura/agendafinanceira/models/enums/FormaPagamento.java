package br.com.futura.agendafinanceira.models.enums;

public enum FormaPagamento {
	
	DINHEIRO(0), 
	CARTAO(1), 
	CHEQUE(2),
	DEPOSITO(3), 
	BOLETO(4), 
	OUTROS(5);
	
	private int formaPagamento;
	
	private FormaPagamento(int formaPagamento){
		this.formaPagamento = formaPagamento;
	}
	
	public int getFormaPagamento(){
		return formaPagamento;
	}

}
