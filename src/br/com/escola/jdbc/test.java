package br.com.escola.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.escola.dao.NotaDao;
import br.com.escola.jdbc.model.Aluno;
import br.com.escola.jdbc.model.Diretor;
import br.com.escola.jdbc.model.Materia;
import br.com.escola.jdbc.model.NomeDaNota;
import br.com.escola.jdbc.model.Nota;
import br.com.escola.jdbc.model.NotaA1;
import br.com.escola.jdbc.model.NotaA2;
import br.com.escola.jdbc.model.NotaAf;
import br.com.escola.jdbc.model.Professor;
import br.com.escola.jdbc.model.Turma;

public class test {
	public static void main(String[] args) throws SQLException {
//		Turma turma = new Turma(123);
//		Diretor diretor = new Diretor("Bruno", "3456566-7", Calendar.getInstance());
//
//		Aluno a1 = new Aluno(5,"Edilson", "8976332-98", "908", 123);
//		Aluno a2 = new Aluno(7, "Pedro", "9809480-63", "809", 123);
//
//		Materia materia = new Materia(2, "Banco de Dados");
//		Professor p1 = new Professor("Paulo Silveira", "03987638-28", 123, materia);
//		Materia materia2 = new Materia(4, "Java");
//		Professor p2 = new Professor("Guilherme Silveira", "95335453-74", 123, materia2);
//
//		diretor.matriculaAluno(turma, a2);
//		diretor.adicionaProfessor(a1, p1);
//		diretor.adicionaProfessor(a1, p2);
//		diretor.adicionaProfessor(a2, p1);
//		diretor.adicionaProfessor(a2, p2);
//		
//		Nota nota1 = new Nota(NomeDaNota.NOTA_A1, 2.1);
//		Nota nota2 = new Nota(NomeDaNota.NOTA_A2, 2.2);
//		Nota notaf = new Nota(NomeDaNota.NOTA_AF, 2.7);
//		
//		NotaA1 notaA1 = new NotaA1(nota1);
//		NotaA2 notaA2 = new NotaA2(nota2);
//		NotaAf notaAf = new NotaAf(notaf);
//
//		try (Connection con = new ConnectionPool().getConnection()) {
//		     a1.consultarMinhasNotas();
//		} 
	}
}