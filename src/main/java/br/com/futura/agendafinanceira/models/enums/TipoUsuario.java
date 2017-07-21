package br.com.futura.agendafinanceira.models.enums;

public enum TipoUsuario {
	
	USUARIO(0, "Usuário"),
	ADMINISTRADOR(1, "Administrador");
	
	private int tipoUsuario;
	private String descricaoUsuario;
	
	private TipoUsuario(int tipoUsuario, String descricaoUsuario) {
		this.tipoUsuario = tipoUsuario;
		this.descricaoUsuario = descricaoUsuario;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}
	
	public String getDescricaoUsuario() {
		return descricaoUsuario;
	}
		
}
