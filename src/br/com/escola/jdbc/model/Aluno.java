package br.com.escola.jdbc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.escola.dao.NotaDao;
import br.com.escola.jdbc.ConnectionPool;

public class Aluno extends Pessoa {
	private Integer id;
	private Integer turma;
	private String numeroDaMatricula;
	private Map<String, Professor> professores = new HashMap<>();
	private String senha;

	
	public Aluno(String nome, String cpf, String email, String tell, Date dataDeCadastro, Integer turma,String numeroDaMatricula) {
		super(nome, cpf, email, tell, dataDeCadastro);
		this.turma = turma;
		this.numeroDaMatricula = numeroDaMatricula;
	}
	public Aluno(Integer id, String nome, String cpf, String email, String tell, String numeroDaMatricula,Integer turma) {
		super(nome, cpf, email, tell);
		this.numeroDaMatricula = numeroDaMatricula;
		this.turma=turma;
		this.id=id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTurma() {
		return turma;
	}

	public String getNumeroDaMatricula() {
		return numeroDaMatricula;
	}

	public Map<String, Professor> getProfessores() {
		return professores;
	}

	public void setNota(Materia materia,GerenciadorDeNotas gerencia ) {
	   gerencia.setNotas(materia,this);
	}

	public void consultarMinhasNotas() throws SQLException {
		try (Connection con = new ConnectionPool().getConnection()) {
			 System.out.println(new NotaDao(con).buscaMinhasNotas(this));
		}

	}

	public String print() {
		return "Nome "+this.getNome()+"\n"+"  Numero Da Matricular  "+this.numeroDaMatricula+"\n"
		+"  Cpf  "+this.getCpf()+"\n"+"  Data De Cadastro "+this.getDataDeCadastro();
	}
	@Override
	public String toString() {
		return "id :"+this.id+" Nome "+this.getNome()+"   Cpf "+this.getCpf()+"  matricula  "+this.numeroDaMatricula+"\n";
	}
	public void setSenha(String senha) {
       this.senha=senha;		
	}
}
