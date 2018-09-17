package aplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.escola.dao.FaltasProperty;
import br.com.escola.dao.NotaDao;
import br.com.escola.jdbc.ConnectionPool;
import br.com.escola.jdbc.model.Aluno;
import br.com.escola.jdbc.model.Materia;
import br.com.escola.jdbc.model.Nota;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaInicial extends Application {
	private Aluno aluno;
	private Label lbldata, lblnome, lblNomeAluno, lblMatriculaAluno, lblCpfAluno, lblBemVindo, lblTelefone, lblEmail;
	private AnchorPane pane, pane2, pane3;
	private Button btnListaNotas, btnConsultaFaltas, btnAvisos, btnServicos, btnFinanceiro;
	private Image financeiro, Servicos, Avisos, ConsultaFaltas, ListaNotas;
	private VBox vb;
	private TableView<MateriasProperty> tbMateria;
	private TableView<FaltasProperty> tbFaltas;
	private AnchorPane pane4;
	private static Stage stage;

	public TelaInicial() {
		this.aluno = new Aluno(5, "Edilson França", "9798", "9867", "79768742916", "434233290", 12);
	}

	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource("telaInicial.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Pagina Principal");
		stage.show();
		initLayout();
		TelaInicial.stage = stage;
	}

	public static Stage getStage() {
		return stage;
	}

	private void initComponents() {
		mostrarNomeEData();
		pane = new AnchorPane();
		pane.getStyleClass().add("pane");
		pane2 = new AnchorPane();
		vb = new VBox();
		criarBotaoListaNotas();
		criarBotaoFinancia();
		criarBotaoConsultaFaltas();
		criarBotaoAvisos();
		criarBotaoServicos();
		vb.getChildren().addAll(btnListaNotas, btnConsultaFaltas, btnAvisos, btnServicos, btnFinanceiro);
		pane4 = new AnchorPane();
		lblNomeAluno = new Label("Nome " + this.aluno.getNome());
		lbldata.getStyleClass().add("lblNomeAluno");
		lblMatriculaAluno = new Label(" Matricula  " + this.aluno.getNumeroDaMatricula());
		lbldata.getStyleClass().add("lblMatriculaAluno");
		lblCpfAluno = new Label("CPF " + this.aluno.getCpf());
		lbldata.getStyleClass().add("lblCpfAluno");
		lblBemVindo = new Label("Bem Vindo");
		lblBemVindo.getStyleClass().add("lblBemVindo");
		lblTelefone = new Label("Tell " + this.aluno.getTell());
		lblTelefone.getStyleClass().add("lblTelefone");
		lblEmail = new Label("Email " + this.aluno.getEmail());
		lblEmail.getStyleClass().add("lblEmail");
		pane4.getChildren().addAll(lblBemVindo, lblNomeAluno, lblMatriculaAluno, lblCpfAluno, lblTelefone,lblEmail);

		pane3 = new AnchorPane(pane4);
		pane3.setPrefSize(700, 350);
		pane3.getStyleClass().add("pane3");

		pane2.setPrefSize(990, 650);
		pane2.getChildren().addAll(pane3);
		pane2.getStyleClass().add("pane2");

		pane.setPrefSize(1300, 670);
		pane.getChildren().addAll(lbldata, lblnome, pane2, vb);

	}

	private void initLayout() {
		posicaoPane2();
		posicaoPane3();
		posicaoVb();
		posicaoData();
		posicaoNome();
		posicaoBtnListaNotas();
		posicaoBtnConsultarFaltas();
		posicaoBtnAvisos();
		posicaoBtnServicos();
		posicaoBtnFinanceiro();
		posicaoPane4();
		posicaoBemVindoPane4();
		posicaoNomePane4();
		posicaoMatriculaPane4();
		posicaoCpfPane4();
		posicaoTellPane4();
		posicaoEmailPane4();
	}

	private void initListeners() {
		btnListaNotas.setOnAction(new EventHandler<ActionEvent>() {
			private ObservableList<MateriasProperty> listDeNotas;

			@Override
			public void handle(ActionEvent event) {
				try {
					listDeNotas = criarLista();
					carregarTableViewNotas(listDeNotas);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnConsultaFaltas.setOnAction(new EventHandler<ActionEvent>() {
			private ObservableList<FaltasProperty> listaDeFaltas;

			@Override
			public void handle(ActionEvent event) {
				try {
					listaDeFaltas = buscaFaltas();
					carregarTableViewFaltas(listaDeFaltas);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void mostrarNomeEData() {
		Date dataAtual = Calendar.getInstance().getTime();
		SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");
		lbldata = new Label(sdff.format(dataAtual));
		lbldata.getStyleClass().add("lbldataEnome");
		lblnome = new Label(this.aluno.getNome());
		lblnome.getStyleClass().add("lbldataEnome");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void carregarTableViewNotas(ObservableList<MateriasProperty> listMateria) {
		tbMateria = new TableView<MateriasProperty>(listMateria);

		TableColumn columnNome = new TableColumn<Materia, String>("Materia ");
		columnNome.setCellValueFactory(new PropertyValueFactory("nome"));
		columnNome.getStyleClass().add("columnNota");

		TableColumn columnNotaA1 = new TableColumn<Materia, Nota>("Nota A1");
		columnNotaA1.setCellValueFactory(new PropertyValueFactory("notaA1"));
		columnNotaA1.getStyleClass().add("columnNota");

		TableColumn columnNotaA2 = new TableColumn("Nota A2");
		columnNotaA2.setCellValueFactory(new PropertyValueFactory("notaA2"));
		columnNotaA2.getStyleClass().add("columnNota");

		TableColumn columnNotaAf = new TableColumn("Nota AF");
		columnNotaAf.setCellValueFactory(new PropertyValueFactory("notaAf"));
		columnNotaAf.getStyleClass().add("columnNota");

		TableColumn columnNotaTotal = new TableColumn("Nota Total");
		columnNotaTotal.setCellValueFactory(new PropertyValueFactory("notaTotal"));
		columnNotaTotal.getStyleClass().add("columnNota");

		TableColumn columnSituacao = new TableColumn("Situação");
		columnSituacao.setCellValueFactory(new PropertyValueFactory("situacao"));
		columnSituacao.getStyleClass().add("columnSituacao");

		tbMateria.getColumns().addAll(columnNome, columnNotaA1, columnNotaA2, columnNotaAf, columnNotaTotal,
				columnSituacao);
		tbMateria.getStyleClass().add("tbNotas");
		pane3.getChildren().addAll(tbMateria);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void carregarTableViewFaltas(ObservableList<FaltasProperty> listaFaltas) {
		tbFaltas = new TableView<FaltasProperty>(listaFaltas);

		TableColumn columnNomef = new TableColumn("Materia ");
		columnNomef.setCellValueFactory(new PropertyValueFactory("nome"));
		columnNomef.getStyleClass().add("columnNota");

		TableColumn columnJan = new TableColumn("Jan");
		columnJan.setCellValueFactory(new PropertyValueFactory("jan"));
		columnJan.getStyleClass().add("columnNota");

		TableColumn columnFev = new TableColumn("fev");
		columnFev.setCellValueFactory(new PropertyValueFactory("fev"));
		columnFev.getStyleClass().add("columnNota");

		TableColumn columnMar = new TableColumn("mar");
		columnMar.setCellValueFactory(new PropertyValueFactory("mar"));
		columnMar.getStyleClass().add("columnNota");

		TableColumn columnAbr = new TableColumn("abr");
		columnAbr.setCellValueFactory(new PropertyValueFactory("abr"));
		columnAbr.getStyleClass().add("columnNota");

		TableColumn columnMai = new TableColumn("mai");
		columnMai.setCellValueFactory(new PropertyValueFactory("mai"));
		columnMai.getStyleClass().add("columnNota");

		TableColumn columnJun = new TableColumn("jun");
		columnJun.setCellValueFactory(new PropertyValueFactory("jun"));
		columnJun.getStyleClass().add("columnNota");

		TableColumn columnJul = new TableColumn("jul");
		columnJul.setCellValueFactory(new PropertyValueFactory("jul"));
		columnJul.getStyleClass().add("columnNota");

		TableColumn columnAgo = new TableColumn("ago");
		columnAgo.setCellValueFactory(new PropertyValueFactory("ago"));
		columnAgo.getStyleClass().add("columnNota");

		TableColumn columnSet = new TableColumn("set");
		columnSet.setCellValueFactory(new PropertyValueFactory("set"));
		columnSet.getStyleClass().add("columnNota");

		TableColumn columnOut = new TableColumn("out");
		columnOut.setCellValueFactory(new PropertyValueFactory("out"));
		columnOut.getStyleClass().add("columnNota");

		TableColumn columnNov = new TableColumn("nov");
		columnNov.setCellValueFactory(new PropertyValueFactory("nov"));
		columnNov.getStyleClass().add("columnNota");

		TableColumn columnDez = new TableColumn("dez");
		columnDez.setCellValueFactory(new PropertyValueFactory("dez"));
		columnDez.getStyleClass().add("columnNota");

		tbFaltas.getColumns().addAll(columnNomef, columnJan, columnFev, columnMar, columnAbr, columnMai, columnJun,
				columnJul, columnAgo, columnSet, columnOut, columnNov, columnDez);
		tbFaltas.getStyleClass().add("tbNotas");
		pane3.getChildren().addAll(tbFaltas);
	}

	private void criarBotaoFinancia() {
		financeiro = new Image(getClass().getResourceAsStream("icon/financeiro.png"));
		btnFinanceiro = new Button("Financeiro     ", new ImageView(financeiro));
	}

	private void criarBotaoListaNotas() {
		ListaNotas = new Image(getClass().getResourceAsStream("icon/listaNotas.png"));
		btnListaNotas = new Button("Lista Notas    ", new ImageView(ListaNotas));
	}

	private void criarBotaoConsultaFaltas() {
		ConsultaFaltas = new Image(getClass().getResourceAsStream("icon/ConsultaFaltas.png"));
		btnConsultaFaltas = new Button("Faltas         ", new ImageView(ConsultaFaltas));

	}

	private void criarBotaoAvisos() {
		Avisos = new Image(getClass().getResourceAsStream("icon/avisos.png"));
		btnAvisos = new Button("Avisos         ", new ImageView(Avisos));

	}

	private void criarBotaoServicos() {
		Servicos = new Image(getClass().getResourceAsStream("icon/serviço.png"));
		btnServicos = new Button("Serviços       ", new ImageView(Servicos));
	}

	private ObservableList<FaltasProperty> buscaFaltas() throws SQLException {
		ObservableList<FaltasProperty> ListasDeFaltas;
		try (Connection con = new ConnectionPool().getConnection()) {
			ListasDeFaltas = new NotaDao(con).buscaMinhasFaltas(aluno);

		}
		return ListasDeFaltas;

	}

	private ObservableList<MateriasProperty> criarLista() throws SQLException {
		ObservableList<MateriasProperty> listMateria;
		try (Connection con = new ConnectionPool().getConnection()) {
			listMateria = new NotaDao(con).buscaMinhasNotas(aluno);
		}
		return listMateria;
	}

	private void posicaoBtnFinanceiro() {
		btnFinanceiro.setLayoutX(680);
		btnFinanceiro.setLayoutY(530);
	}

	private void posicaoBtnServicos() {
		btnServicos.setLayoutX(530);
		btnServicos.setLayoutY(530);
	}

	private void posicaoBtnAvisos() {
		btnAvisos.setLayoutX(380);
		btnAvisos.setLayoutY(530);
	}

	private void posicaoBtnConsultarFaltas() {
		btnConsultaFaltas.setLayoutX(230);
		btnConsultaFaltas.setLayoutY(530);
	}

	private void posicaoBtnListaNotas() {
		btnListaNotas.setLayoutX(80);
		btnListaNotas.setLayoutY(530);
	}

	private void posicaoNome() {
		lblnome.setLayoutX(1200);
		lblnome.setLayoutY(150);
	}

	private void posicaoData() {
		lbldata.setLayoutX(1200);
		lbldata.setLayoutY(90);
	}

	private void posicaoVb() {
		vb.setLayoutX(15);
		vb.setLayoutY(60);
	}

	private void posicaoPane3() {
		pane3.setLayoutX(250);
		pane3.setLayoutY(40);
	}

	private void posicaoPane2() {
		pane2.setLayoutX(100);
		pane2.setLayoutY(40);
	}

	private void posicaoPane4() {
		pane4.setLayoutX(20);
		pane4.setLayoutY(40);
	}

	private void posicaoBemVindoPane4() {
		lblBemVindo.setLayoutX((pane3.getWidth() - lblBemVindo.getWidth()) / 2);
		lblBemVindo.setLayoutY(50);
	}

	private void posicaoNomePane4() {
		lblNomeAluno.setLayoutX(0);
		lblNomeAluno.setLayoutY(90);
	}

	private void posicaoMatriculaPane4() {
		lblMatriculaAluno.setLayoutX(0);
		lblMatriculaAluno.setLayoutY(130);
	}

	private void posicaoCpfPane4() {
		lblCpfAluno.setLayoutX(0);
		lblCpfAluno.setLayoutY(170);
	}

	private void posicaoTellPane4() {
		lblTelefone.setLayoutX(0);
		lblTelefone.setLayoutY(210);
	}
	private void posicaoEmailPane4() {
		lblEmail.setLayoutX(0);
		lblEmail.setLayoutY(250);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
