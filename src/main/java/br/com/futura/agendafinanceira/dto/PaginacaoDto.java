package br.com.futura.agendafinanceira.dto;

public class PaginacaoDto {

	private Integer pagina;
	private Integer registrosPorPagina;
	private String ordenacao;
	private boolean ascendente;

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public Integer getRegistrosPorPagina() {
		return registrosPorPagina;
	}

	public void setRegistrosPorPagina(Integer registrosPorPagina) {
		this.registrosPorPagina = registrosPorPagina;
	}

	public String getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(String ordenacao) {
		this.ordenacao = ordenacao;
	}

	public boolean isAscendente() {
		return ascendente;
	}

	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}

	@Override
	public String toString() {
		return "PaginacaoDto [pagina=" + pagina + ", registrosPorPagina=" + registrosPorPagina + ", ordenacao="
				+ ordenacao + ", ascendente=" + ascendente + "]";
	}

}
