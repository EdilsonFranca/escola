package aplication;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.escola.dao.AlunoDao;
import br.com.escola.jdbc.ConnectionPool;
import br.com.escola.jdbc.model.Aluno;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginApp extends Application {
	private AnchorPane pane;
	private TextField txLogin;
	private PasswordField txSenha;
	private Button btEntrar, btSair;
	private Label lblLogin, lblSenha;
	private static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Login");
		stage.show();
		initLayout();
		LoginApp.stage = stage;
	}

	public static Stage getStage() {
		return stage;
	}

	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(400, 300);
		pane.getStyleClass().add("pane");
		txLogin = new TextField();
		txLogin.setPromptText("Digite aqui seu login");
		txSenha = new PasswordField();
		txSenha.setPromptText("Digite aui sua senha");
		btEntrar = new Button("Entrar");
		btEntrar.setId("btEntrar");
		btSair = new Button("Sair");
		btSair.setId("btSair");
		lblLogin = new Label("Login");
		lblSenha = new Label("Senha");
		pane.getChildren().addAll(txLogin, txSenha, btEntrar, btSair, lblLogin, lblSenha);

	}

	public void initLayout() {
		txLogin.setLayoutX((pane.getWidth() - txLogin.getWidth()) / 2);
		txLogin.setLayoutY(50);
		txSenha.setLayoutX((pane.getWidth() - txSenha.getWidth()) / 2);
		txSenha.setLayoutY(100);
		lblLogin.setLayoutX((pane.getWidth() - lblLogin.getWidth()) / 5);
		lblLogin.setLayoutY(50);
		lblSenha.setLayoutX((pane.getWidth() - lblLogin.getWidth()) / 5);
		lblSenha.setLayoutY(100);

		btEntrar.setLayoutX(((pane.getWidth() - btEntrar.getWidth()) / 2) - 50);
		btEntrar.setLayoutY(150);
		btSair.setLayoutX(((pane.getWidth() - txSenha.getWidth()) / 2) + 110);
		btSair.setLayoutY(150);
	}

	public void initListeners() {
		btSair.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fecharAplicacao();
			}

			private void fecharAplicacao() {
				System.exit(0);
			}
		});

		btEntrar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					logar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void logar() throws Exception {
				Aluno aluno = isValid();
				if (aluno != null) {
					try {
//						new TelaInicial(aluno).start(new Stage());
						LoginApp.getStage().close();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Login ou senha invalido!!!", "ERRO",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			private Aluno isValid() throws SQLException {
				try (Connection con = new ConnectionPool().getConnection()) {
					return new AlunoDao(con).buscaAluno(txLogin.getText(), txSenha.getText());
				}
				
			}
		});
	}

	

}
