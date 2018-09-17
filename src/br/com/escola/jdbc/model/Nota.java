package br.com.escola.jdbc.model;

public class Nota {
	private Integer id;
	private double valor=0.0;	
	private NomeDaNota nome;
	
	public Nota(NomeDaNota nome,double valor) {
		this.nome=nome;
		this.valor=valor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	public NomeDaNota getNome() {
		return nome;
	}
	@Override
	public String toString() {
		return this.nome +" "+this.valor;
	}
	
}
