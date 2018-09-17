package br.com.escola.jdbc.model;

public enum NomeDaNota {
	NOTA_A1("nota_A1"), NOTA_A2("nota_A2"), NOTA_AF("nota_AF"), NOTA_TOTAL("nota Total");
	private String nome;

	private NomeDaNota(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}

}
