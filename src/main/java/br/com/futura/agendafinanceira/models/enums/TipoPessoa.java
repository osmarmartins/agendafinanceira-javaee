package br.com.futura.agendafinanceira.models.enums;

public enum TipoPessoa {
	
	PF("PF", "Pessoa Física", "999.999.999-99", "CPF"),
	PJ("PJ", "Pessoa Jurídica", "99.999.999/9999-99", "CNPJ");
	
	private String tipoPessoa;
	private String descricao;
	private String mascara;
	private String rotulo;
	
	private TipoPessoa(String tipoPessoa, String descricao, String mascara, String rotulo){
		this.tipoPessoa = tipoPessoa;
		this.descricao = descricao;
		this.mascara = mascara;
		this.rotulo = rotulo;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getMascara() {
		return mascara;
	}
	
	public String getRotulo() {
		return rotulo;
	}
	
}
