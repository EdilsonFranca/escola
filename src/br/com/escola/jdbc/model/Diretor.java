package br.com.escola.jdbc.model;

import java.util.Date;

public class Diretor extends Pessoa{

	public Diretor(String nome, String cpf,String email,String tell, Date dataDeCadastro) {
		super(nome, cpf, email, tell, dataDeCadastro);
	}
	public void matriculaAluno(Turma turma,Aluno aluno) {
		turma.getAlunos().add(aluno);
	}
	public void adicionaProfessor(Aluno aluno,Professor professor) {
		aluno.getProfessores().put(professor.getNome(), professor);
	}

}
