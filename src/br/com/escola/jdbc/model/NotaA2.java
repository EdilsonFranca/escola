package br.com.escola.jdbc.model;

import java.sql.Connection;
import java.sql.SQLException;
import br.com.escola.dao.NotaDao;
import br.com.escola.jdbc.ConnectionPool;

public class NotaA2 implements GerenciadorDeNotas {
	private EstadoDoAluno situacao;
	private Nota nota;

	public NotaA2(Nota nota) {
		this.nota = nota;
	}

	public void setNotas(Materia materia, Aluno aluno) {
		if (isValida(nota.getValor())) {
			try (Connection con = new ConnectionPool().getConnection()) {
				NotaDao notaD = new NotaDao(con);
				notaD.inserirNotaA2(aluno, materia, this);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else
			throw new IllegalArgumentException("nota invalida !!");
	}

	public void verificaSituacao(double nota) {
		if (nota >= 6)
			this.situacao = EstadoDoAluno.APROVADO;
		else
			this.situacao = EstadoDoAluno.AVALIACAO_FINAL;
	}

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
