package aplication;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class MateriasProperty {
	private SimpleStringProperty nome;
	private SimpleDoubleProperty notaA1;
	private SimpleDoubleProperty notaA2;
	private SimpleDoubleProperty notaAf;
	private SimpleDoubleProperty notaTotal;
	private SimpleStringProperty situacao;

	public MateriasProperty(String nome, double notaA1, double notaA2,double notaAf, double notaTotal,String situacao) {
		this.nome = new SimpleStringProperty(nome);
		this.notaA1 =  new SimpleDoubleProperty(notaA1);
		this.notaA2 =  new SimpleDoubleProperty(notaA2);
		this.notaAf =  new SimpleDoubleProperty(notaAf);
		this.notaTotal =  new SimpleDoubleProperty(notaTotal);
		this.situacao =  new SimpleStringProperty(situacao);
	}
	public String getNome() {
		return nome.get();
	}
	public double getNotaA1() {
		return notaA1.get();
	}

	public void setNotaA1(double notaA1) {
		this.notaA1.set(notaA1);
	}

	public double getNotaA2() {
		return notaA2.get();
	}

	public void setNotaA2(double notaA2) {
		this.notaA2.set(notaA2);
	}

	public double getNotaAf() {
		return notaAf.get();
	}

	public void setNotaAF(double notaAF) {
		this.notaAf.set(notaAF);
	}

	public double getNotaTotal() {
		return notaTotal.get();
	}

	public void setNotaTotal(double notaTotal) {
		this.notaTotal.set(notaTotal);
	}

	public String getSituacao() {
		return situacao.get();
	}
}
