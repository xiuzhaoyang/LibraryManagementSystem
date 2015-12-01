package application;

import java.io.IOException;

import application.views.LoginController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	Stage primaryStage;
	BorderPane root;
	RadioButton libranian;
	RadioButton admin;
	RadioButton superRole;

	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
			this.primaryStage = primaryStage;
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();

			showLogin();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void showLogin()
	{
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/Login.fxml"));
			AnchorPane loginPage = (AnchorPane) loader.load();

			final ToggleGroup group = new ToggleGroup();
			libranian = new RadioButton("Libranian");
			admin = new RadioButton("Admin");
			superRole = new RadioButton("Super");

			libranian.setToggleGroup(group);
			admin.setToggleGroup(group);
			superRole.setToggleGroup(group);

			Button loginBtn = new Button();
			Button cancelBtn = new Button();

			Stage loginStage = new Stage();

			Scene scene = new Scene(loginPage);
			loginStage.setScene(scene);

			// Give the controller access to the main app.
			LoginController controller = loader.getController();
			controller.setStage(loginStage);

			//wait until user close it
			loginStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleLoginBtn(ActionEvent e) {
		String role;

		if(libranian.isSelected()){
			role = "Libranian";
		}else{
			role = "Amdin";
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
