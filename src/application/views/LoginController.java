package application.views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private RadioButton libranianField;

	@FXML
	private RadioButton adminField;

	@FXML
	private RadioButton supernField;

	private Stage loginStage;

	@FXML
	private void handleLoginBtn() {
		// if(usernameField == null){
		// System.out.println("username is null");
		// }
		String username = usernameField.getText();
		String password = passwordField.getText();
//		System.out.println("username" + username.length());

		// if(username == null){
		// System.out.println("username is null");
		// }

		String role = "";

		if (libranianField.isSelected()) {
			role = "Libranian";
		}

		if (adminField.isSelected()) {
			role = "Admin";
		}

		if(supernField.isSelected()){
			role = "Super";
		}

		Alert alert = new Alert(AlertType.ERROR);

		if (username.length() == 0) {
			alert.setContentText("Please fill out your username.");
			alert.showAndWait();
		}else if (password.length() == 0) {
			alert.setContentText("Please fill out your password.");
			alert.showAndWait();
		}else if (role.length() == 0) {
			alert.setContentText("Please choose a role.");
			alert.showAndWait();
		}else if (username.equals("lib") && password.equals("lib")) {
			if(role.equals("Libranian")){
				loginStage.close();
				//call libranian UI
			}else{
				alert.setContentText("Please check your role.");
				alert.showAndWait();
			}

		}else if(username.equals("admin") && password.equals("admin")){
			if(role.equals("Admin")){
				loginStage.close();
				//call Admin UI
			}else{
				alert.setContentText("Please check your role.");
				alert.showAndWait();
			}
		}else if(username.equals("super") && password.equals("super")){
			if(role.equals("Super")){
				loginStage.close();
				//call Super UI
			}else{
				alert.setContentText("Please check your role.");
				alert.showAndWait();
			}
		}else {
			alert.setContentText("Either your name or password is wrong.");
			alert.showAndWait();
		}
	}

	@FXML
	private void handleCancelBtn() {
		loginStage.close();
	}

	public void setStage(Stage loginStage) {
		this.loginStage = loginStage;
	}
}
