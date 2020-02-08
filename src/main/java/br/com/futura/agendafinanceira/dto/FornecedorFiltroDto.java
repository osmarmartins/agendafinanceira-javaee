package br.com.futura.agendafinanceira.dto;

public class FornecedorFiltroDto {

	private String descricao;
	private PaginacaoDto paginacao = new PaginacaoDto();

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public PaginacaoDto getPaginacao() {
		return paginacao;
	}

	public void setPaginacao(PaginacaoDto paginacao) {
		this.paginacao = paginacao;
	}

}
