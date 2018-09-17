package br.com.escola.jdbc.model;

import java.util.Calendar;
import java.util.Date;

public abstract class Pessoa {
	private String nome;
	private String cpf;
	private String tell;
	private String email;
	private Date dataDeCadastro;

	public Pessoa(String nome, String cpf,String email,String tell) {
		this.nome = nome;
		this.cpf = cpf;
		this.email=email;
		this.tell=tell;
	}
    public Pessoa(String nome, String cpf, String email, String tell, Date dataDeCadastro) {
    	this.nome = nome;
		this.cpf = cpf;
		this.email=email;
		this.tell=tell;
		this.dataDeCadastro=dataDeCadastro;
	}
	
	public Pessoa(String nome, Date dataDeCadastro) {
		this.nome = nome;
		this.dataDeCadastro = dataDeCadastro;  
     }
	


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public String getTell() {
		return tell;
	}
	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

}
