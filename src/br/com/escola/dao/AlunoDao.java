package br.com.escola.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;


import br.com.escola.jdbc.model.Aluno;

public class AlunoDao {
	private Connection con;

	public AlunoDao(Connection con) {
		this.con = con;
	}

	public void salvar(Aluno aluno) throws SQLException {
		String sql = "insert into aluno(nome,cpf,num_matricula,id_turma,data_cadastro)values(?,?,?,?,CURRENT_DATE)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getCpf());
			stmt.setString(3, aluno.getNumeroDaMatricula());
			stmt.setInt(4, aluno.getTurma());
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					aluno.setId(id);
					System.out.println(aluno.getNome() + "cadastrado com sucesso");
				}
			}
		}
	}

	public List<Aluno> listarAlunos() throws SQLException {
		List<Aluno> alunos = new ArrayList<>();
		String sql = "select * from aluno ";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int id = rs.getInt("ID");
					String nome = rs.getString("NOME");
					String numeroDaMatricula = rs.getString("NUM_MATRICULA");
					String cpf = rs.getString("CPF");
					Date dataCadastro = rs.getDate("data_cadastro");
					String tell=rs.getString("tell");
					Integer turma=rs.getInt("id_turma");
					String email=rs.getString("email");
					Aluno aluno = new Aluno(nome,cpf,email,tell,dataCadastro,turma,numeroDaMatricula);
					aluno.setId(id);
					alunos.add(aluno);
				}
			}
		}
		// java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:escola --dbname.0 escola
		// java -cp hsqldb.jar org.hsqldb.util.DatabaseManager
		// url = jdbc:hsqldb:hsql://localhost/escola

		return alunos;
	}

	public Aluno buscaAluno(String login,String senha) throws SQLException {
		Aluno aluno = null;
	    String sql="select * from aluno where nome=? and senha=?";
	    try(PreparedStatement stmt=con.prepareStatement(sql)){
	      stmt.setString(1, login);
	      stmt.setString(2, senha);
	      stmt.execute();
	      try(ResultSet rs=stmt.getResultSet()){
	    	  if(rs.next()) {
	    		  int id = rs.getInt("ID");
					String nome = rs.getString("NOME");
					String numeroDaMatricula = rs.getString("NUM_MATRICULA");
					String cpf = rs.getString("CPF");
					Date dataCadastro = rs.getDate("data_cadastro");
					String tell=rs.getString("tell");
					Integer turma=rs.getInt("id_turma");
					String email=rs.getString("email");
					aluno = new Aluno(nome,cpf,email,tell,dataCadastro,turma,numeroDaMatricula);
	    	  }
	       }
	    }
		return aluno;
}
}
