package br.com.futura.agendafinanceira.models.enums;

public enum TipoPessoa {
	
	PF("PF", "Pessoa Física"),
	PJ("PJ", "Pessoa Jurídica");
	
	private String tipoPessoa;
	private String descricao;
	
	private TipoPessoa(String tipoPessoa, String descricao){
		this.tipoPessoa = tipoPessoa;
		this.descricao = descricao;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public String getDescricao() {
		return descricao;
	}

	
}
