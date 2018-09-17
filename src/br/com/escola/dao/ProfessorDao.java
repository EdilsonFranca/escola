package br.com.escola.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.escola.jdbc.model.Aluno;
import br.com.escola.jdbc.model.Materia;
import br.com.escola.jdbc.model.Nota;
import br.com.escola.jdbc.model.NotaA2;
import br.com.escola.jdbc.model.NotaAf;
import br.com.escola.jdbc.model.Professor;

public class ProfessorDao {
	private Connection con;

	public ProfessorDao(Connection con) {
		this.con = con;
	}

	public void salvar(Professor professor) throws SQLException {
		String sql = "insert into professor(nome,cpf,id_turma,data_cadastro)values(?,?,?,CURRENT_DATE)";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, professor.getNome());
			stmt.setString(2, professor.getCpf());
			stmt.setInt(3, professor.getTurma());
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt("id");
					professor.setId(id);
					System.out.println(professor.getNome() + "  cadastrado com sucesso !" + "com id:" + id);
				}
			}

		}

	}

	public List<Professor> listar() throws SQLException {
		List<Professor> professores = new ArrayList<>();
		String sql = "select p.id ,p.id_turma,p.data_cadastro,p.nome as nome_Professor,m.nome as nome_Materia from professor p join materia m on  p.id=m.id_professor";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					Integer id = rs.getInt("id");
					String nome = rs.getString("nome_Professor");
					Integer turma = rs.getInt("id_turma");
					String nomeMateria = rs.getString("nome_Materia");
					Date dataCadastro = rs.getDate("data_cadastro");
					Materia materia = new Materia(nomeMateria);
					Professor professor = new Professor(id, nomeMateria, turma, dataCadastro, materia);
					professores.add(professor);
				}
			}
		}
		return professores;
	}

	
}
