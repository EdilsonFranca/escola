package br.com.escola.jdbc.model;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.escola.dao.NotaDao;
import br.com.escola.jdbc.ConnectionPool;

public class NotaA1 implements GerenciadorDeNotas {
	private EstadoDoAluno situacao;
	private Nota nota;

	public NotaA1(Nota nota) {
		this.nota = nota;
	}

	public void setNotas(Materia materia, Aluno aluno) {
		if (isValida(nota.getValor())) {
			this.situacao = EstadoDoAluno.NULL;
			materia.setSituacao(situacao);
			try (Connection con = new ConnectionPool().getConnection()) {
				NotaDao notaD = new NotaDao(con);
				notaD.inserirNotaA1(aluno, materia, nota);
			} catch (SQLException e) {
				System.out.println(e);
			}
		} else
			throw new IllegalArgumentException("nota invalida !!");
	}

	@Override
	public boolean isValida(double valor) {
		return valor >= 0 && valor <= 5;
	}

	@Override
	public EstadoDoAluno getEstado() {
		return this.situacao;
	}

	@Override
	public Nota getNota() {
		return this.nota;
	}

	@Override
	public String toString() {
		return "" + this.nota;
	}

}
