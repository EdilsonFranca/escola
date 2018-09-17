package br.com.escola.jdbc.model;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.escola.dao.NotaDao;
import br.com.escola.jdbc.ConnectionPool;

public class NotaAf implements GerenciadorDeNotas {
	private EstadoDoAluno situacao;
	private Nota nota;

	public NotaAf(Nota nota) {
		this.nota = nota;
	}

	@Override
	public void setNotas(Materia materia, Aluno aluno) {
		if (isValida(nota.getValor())) {
			try (Connection con = new ConnectionPool().getConnection()) {
				NotaDao notaD = new NotaDao(con);
				notaD.inserirNotaAf(aluno, materia, this);
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		} else
			throw new IllegalArgumentException("nota invalida !!");
	}

	public void verificaSituacao(double nota) {
		if (nota >= 6)
			this.situacao=EstadoDoAluno.APROVADO;
		else
			this.situacao=EstadoDoAluno.REPROVADO;
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

	

}
