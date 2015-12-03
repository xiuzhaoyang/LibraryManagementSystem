package application.views;

import application.Dao.PersonDao;
import application.models.Administrator;
import application.models.Librarian;
import application.models.Person;
import application.models.PersonRole;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	private Person personInfo;

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
		PersonRole personRole = null;

		if (libranianField.isSelected()) {
			role = "Libranian";
			personRole = new Librarian();
		}

		if (adminField.isSelected()) {
			role = "Admin";
			personRole = new Administrator();
		}

//		if(supernField.isSelected()){
//			role = "Super";
//		}

		Alert alert = new Alert(AlertType.ERROR);

		if (username.length() == 0) {
			alert.setContentText("Please fill out your username.");
			alert.showAndWait();
			return ;
		}else if (password.length() == 0) {
			alert.setContentText("Please fill out your password.");
			alert.showAndWait();
			return ;
		}else if (role.length() == 0) {
			alert.setContentText("Please choose a role.");
			alert.showAndWait();
			return ;
		}else if(!checkUser(username,password,personRole)){
			alert.setContentText("Either your name or password is wrong.");
			alert.showAndWait();
			return ;
		}else{
			loginStage.close();
		}
	}

	@FXML
	private void handleCancelBtn() {
		loginStage.close();
	}

	public void setStage(Stage loginStage) {
		this.loginStage = loginStage;
	}

	public Person getPersonInfo(){
		return this.personInfo;
	}

	private boolean checkUser(String account, String pwd, PersonRole role){
		PersonDao persondao = new PersonDao();
		Person person =  persondao.loadPersonByNameAndPwd(account, pwd);
		if(person == null ){
			return false;
		}else{
			List<PersonRole> roleList =  person.getPersonRoles();
			for(PersonRole tmpRole : roleList){
				if(tmpRole.toString().equals(role.toString())){
					List<PersonRole> tmpRoleList = new ArrayList<PersonRole>();
					tmpRoleList.add(tmpRole);
					person.setPersonRoles(tmpRoleList);
					this.personInfo = person;
					return true;
				}
			}
			return false;
		}
	}
}
