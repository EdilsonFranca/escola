package br.com.escola.jdbc.model;

import java.util.ArrayList;
import java.util.List;

public class Turma {
	private int numero;
	private List<Aluno> alunos = new ArrayList<>();

	public Turma(int numero) {
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

    public List<Aluno> getAlunos() {
		return alunos;
	}
    

//	public void printTurma() {
//		System.out.println("Turma " + this.numero);
//		System.out.print("Professores\n");
//		this.listarProfessores();
//		System.out.print("Alunos\n");
//		this.listaAlunos();
//	}
//
//	private void listaAlunos() {
//		for (Aluno aluno : alunos) {
//			System.out.println("--"+aluno.getNome() +"  cpf " + aluno.getCpf());
//		}
//	}

//	private void listarProfessores() {
//		for (Professor professor : professores) {
//			System.out.println("--"+professor.getNome()+" Materia "+professor.getMateria().getNome());
//		}
//	}
}

