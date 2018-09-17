package br.com.escola.jdbc.model;

import java.util.Calendar;
import java.util.Date;
public class Professor extends Pessoa {
	private Integer id;
	private Materia materia;
	private Integer turma;

	public Professor(String nome, String cpf,String tell,String email, Integer turma) {
		super(nome, cpf, tell, email);
		this.turma = turma;
	}
	
	public Professor(Integer id,String nome,Integer turma, Date dataDeCadastro, Materia materia) {
		super(nome,dataDeCadastro);
		this.id = id;
		this.materia = materia;
		this.turma = turma;
	}

	public Professor(String nome, String cpf,String tell,String email,Integer turma, Materia materia) {
		super(nome, cpf, tell, email);
		this.materia = materia;
	}



	public Materia getMateria() {
		return materia;
	}

	public void aplicaNota(Aluno aluno,GerenciadorDeNotas gerencia ) {
        aluno.setNota(this.materia,gerencia );
	}

	
	public Integer getTurma() {
		return turma;
	}


	public void setId(Integer id) {
		this.id = id;
	}
     @Override
	public String toString() {
		return "Professor " + this.getNome()+" Materia "+this.materia.getNome()+"\n";
	}
 


}
