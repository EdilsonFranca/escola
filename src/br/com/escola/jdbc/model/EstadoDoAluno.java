package br.com.escola.jdbc.model;

public enum EstadoDoAluno {
	APROVADO("Aprovado"), 
	NULL("aguardando a nota A2"),
	AVALIACAO_FINAL("Avaliação final"), 
	REPROVADO("Reprovado");
	
	private String descricao;

	private EstadoDoAluno(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return this.getDescricao();
	}
	
}
