package br.com.escola.jdbc.model;

public interface GerenciadorDeNotas {

	void setNotas(Materia materia,Aluno aluno);

	boolean isValida(double valor);

	EstadoDoAluno getEstado();

	Nota getNota();

}
