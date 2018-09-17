package br.com.escola.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aplication.MateriasProperty;
import br.com.escola.jdbc.model.Aluno;
import br.com.escola.jdbc.model.Materia;
import br.com.escola.jdbc.model.NomeDaNota;
import br.com.escola.jdbc.model.Nota;
import br.com.escola.jdbc.model.NotaA2;
import br.com.escola.jdbc.model.NotaAf;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NotaDao {
	private Connection con;

	public NotaDao(Connection con) {
		this.con = con;
	}

	public void inserirNotaA1(Aluno aluno, Materia materia, Nota nota) throws SQLException {
		String sql = "insert into nota(nota_A1,id_materia,id_aluno,situacao)values(?,?,?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setDouble(1, nota.getValor());
			stmt.setInt(2, materia.getId());
			stmt.setInt(3, aluno.getId());
			stmt.setString(4, materia.getSituacao().getDescricao());
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt("id");
				}
			}
		}

	}

	public void inserirNotaA2(Aluno aluno, Materia materia, NotaA2 notaA2) throws SQLException {
		double nota_A1 = buscaNotaA1(aluno, materia);
		double nota_Total = nota_A1 + notaA2.getNota().getValor();
		notaA2.verificaSituacao(nota_Total);
		String sql = "update nota set nota_A2=?,nota_total=?,situacao=? where id_materia=? and id_aluno=?";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setDouble(1, notaA2.getNota().getValor());
			stmt.setDouble(2, nota_Total);
			stmt.setString(3, notaA2.getEstado().getDescricao());
			stmt.setInt(4, materia.getId());
			stmt.setInt(5, aluno.getId());
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt("id");
				}
			}
		}
	}

	public void inserirNotaAf(Aluno aluno, Materia materia, NotaAf notaAf) throws SQLException {
		double maior_Nota = buscaMaiorNota(aluno, materia);
		double notaTotal = maior_Nota + notaAf.getNota().getValor();
		notaAf.verificaSituacao(notaTotal);
		String sql = "update nota set nota_Af=?,nota_total=?,situacao=? where id_materia=? and id_aluno=?";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setDouble(1, notaAf.getNota().getValor());
			stmt.setDouble(2, notaTotal);
			stmt.setString(3, notaAf.getEstado().getDescricao());
			stmt.setInt(4, materia.getId());
			stmt.setInt(5, aluno.getId());
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt("id");
				}
			}
		}

	}

	private double buscaMaiorNota(Aluno aluno, Materia materia) throws SQLException {
		double maiorNota = 0;
		String sql = "select nota_a1,nota_a2 from nota where id_aluno=? and id_materia=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, aluno.getId());
			stmt.setInt(2, materia.getId());
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				if (rs.next()) {
					double nota1 = rs.getDouble("nota_a1");
					double nota2 = rs.getDouble("nota_a2");
					maiorNota = Math.max(nota1, nota2);
				}
			}
		}
		return maiorNota;
	}

	private double buscaNotaA1(Aluno aluno, Materia materia) throws SQLException {
		double notaA1 = 0;
		String sql = "select nota_A1 from nota where id_aluno=? and id_materia=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, aluno.getId());
			stmt.setInt(2, materia.getId());
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				if (rs.next()) {
					notaA1 = rs.getDouble("nota_A1");
				}
			}
		}
		return notaA1;
	}

	public ObservableList<MateriasProperty> buscaMinhasNotas(Aluno aluno) throws SQLException {
		ObservableList<MateriasProperty> materias = FXCollections.observableArrayList();
		String sql = "select m.nome,n.* from nota n  join materia m on n.id_materia=m.id where id_aluno=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, aluno.getId());
		stmt.execute();
		try (ResultSet rs = stmt.getResultSet()) {
			while (rs.next()) {
				String nome = rs.getString("nome");
				double notaA1 = rs.getDouble("nota_A1");
				double notaA2 = rs.getDouble("nota_A2");
				double notaAf = rs.getDouble("nota_Af");
				double notaTotal = rs.getDouble("nota_total");
				String situacao = rs.getString("situacao");
				MateriasProperty materia = new MateriasProperty(nome, notaA1, notaA2, notaAf, notaTotal, situacao);
				materias.add(materia);
			}

		}
		return materias;
	}

	public ObservableList<FaltasProperty> buscaMinhasFaltas(Aluno aluno) throws SQLException {
		ObservableList<FaltasProperty> faltas = FXCollections.observableArrayList();
		String sql = "SELECT m.nome, f.jan , f.fev, f.mar, f.abr, f.mai, f.jun, f.jul, f.ago, f.set_, f.out, f.nov, f.dez   from  faltas f\r\n"
				+ "  join materia m on f.id_materia=m.id    where id_aluno=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, aluno.getId());
		stmt.execute();
		try (ResultSet rs = stmt.getResultSet()) {
			while (rs.next()) {
				String nome = rs.getString("nome");
				int jan = rs.getInt("jan");
				int fev = rs.getInt("fev");
				int mar = rs.getInt("mar");
				int abr = rs.getInt("abr");
				int mai = rs.getInt("mai");
				int jun = rs.getInt("jun");
				int jul = rs.getInt("jul");
				int ago = rs.getInt("ago");
				int set = rs.getInt("set_");
				int out = rs.getInt("out");
				int nov = rs.getInt("nov");
				int dez = rs.getInt("dez");
				FaltasProperty faltasProperty = new FaltasProperty(nome, jan, fev, mar, abr, mai, jun, jul, ago, set,
						out, nov, dez);
				faltas.add(faltasProperty);
			}
		}
		return faltas;
	}

}
