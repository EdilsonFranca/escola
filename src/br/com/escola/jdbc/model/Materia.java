package br.com.escola.jdbc.model;

import java.sql.SQLException;
import java.util.List;

public class Materia {
	private Integer id;
	private String nome;
	private List<Nota> notas;
	private EstadoDoAluno situacao;

	public Materia(String nome) {
		this.nome = nome;
	}

	public Materia(String nome, List<Nota> notas) {
		this.nome = nome;
		this.notas = notas;
	}

	public Materia(Integer id, String nome) {
		this.nome = nome;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public List<Nota> getNotas(Aluno aluno) throws SQLException {
		return notas;
	}

	public EstadoDoAluno getSituacao() {
		return situacao;
	}

	public void setSituacao(EstadoDoAluno situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return " Materia " + this.nome + " Notas" + this.notas+" \n";
	}

}
