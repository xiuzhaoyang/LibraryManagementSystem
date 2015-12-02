package application.views;

import application.models.Address;
import application.models.Person;
import application.util.DateHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MemberEditDialogController {
	@FXML
	private Label idLabel;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField birthdayField;

	@FXML
	private TextField streetField;

	@FXML
	private TextField cityField;

	@FXML
	private TextField stateField;

	@FXML
	private TextField zipCodeField;

	@FXML
	private TextField phoneField;

	@FXML
	private TextField personRolesField;

	private Stage dialogStage;
	private Person member;
	private boolean isOk = false;

	@FXML
	private void initialize(){

	}

	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}

	public void setMember(Person member){
		this.member = member;

//		int id = member.getId();
		idLabel.setText(member.getId() + "");
		firstNameField.setText(member.getFirstName());
		lastNameField.setText(member.getLastName());
		birthdayField.setText(DateHelper.format(member.getBirthday()));
		birthdayField.setPromptText("dd.mm.yyyy");
		streetField.setText(member.getAddress().getStreet());
		cityField.setText(member.getAddress().getCity());
		stateField.setText(member.getAddress().getState());
		zipCodeField.setText(member.getAddress().getZip());
		phoneField.setText(member.getPhoneNum());
		personRolesField.setText(member.getPersonRolesToString());
	}

	public boolean checkIsOk(){
		return this.isOk;
	}

	@FXML
	private void handleOK(){
		if(isInputValid()){
			member.setId(Integer.parseInt(idLabel.getText()));
			member.setFirstName(firstNameField.getText());
			member.setLastName(lastNameField.getText());
			member.setBirthday(DateHelper.parse(birthdayField.getText()));

			String street = streetField.getText();
			String city = cityField.getText();
			String state = stateField.getText();
			String zipCode = zipCodeField.getText();
			Address addr = new Address(street, city, state, zipCode);
			member.setAddress(addr);

			member.setPhoneNum(phoneField.getText());
			member.setPersonRoleString(personRolesField.getText());

			isOk = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel(){
		dialogStage.close();
	}

	private boolean isInputValid() {
		StringBuilder sb = new StringBuilder();

//		try{
//			Integer.parseInt(idField.getText());
//		}catch(NumberFormatException e){
//			sb.append("Please enter a numeric id number");
//		}

		if(isNullOrEmpty(firstNameField.getText())){
			sb.append("First name input is not valid.\n");
		}

		if(isNullOrEmpty(lastNameField.getText())){
			sb.append("Last name input is not valid.\n");
		}

		if(isNullOrEmpty(birthdayField.getText())){
			sb.append("Birthday input is not valid.\n");
		}else if(!DateHelper.validDate(birthdayField.getText())){
			sb.append("Please use format dd.mm.yyyy.\n");
		}

		if(isNullOrEmpty(streetField.getText())){
			sb.append("Street input is not valid.\n");
		}

		if(isNullOrEmpty(cityField.getText())){
			sb.append("City input is not valid.\n");
		}

		if(isNullOrEmpty(stateField.getText())){
			sb.append("State input is not valid.\n");
		}

		if(isNullOrEmpty(zipCodeField.getText())){
			sb.append("Zip code input in not valid.\n");
		}else{
			try{
				Integer.parseInt(zipCodeField.getText());
			}catch(NumberFormatException e){
				sb.append("Please input valid digit number.\n");
			}
		}

		if(isNullOrEmpty(personRolesField.getText())){
			sb.append("Person role input is not valid.\n");
		}else{
			String[] roles = personRolesField.getText().split(",");

			for(String role : roles){
				if(!role.equals("Librarian") && !role.equals("LibraryMember") && !role.equals("Administrator")){
					sb.append("Please input correct role.\n");
				}
			}
		}

		if(sb.length() == 0){
			return true;
		}else{
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.initOwner(dialogStage);
			errorAlert.setTitle("You have invalid Fields");
			errorAlert.setHeaderText("Please check your input");
			errorAlert.setContentText(sb.toString());

			errorAlert.showAndWait();
            return false;
		}
	}

	private boolean isNullOrEmpty(String str){
		if(str == null || str.length() == 0){
			return true;
		}

		return false;
	}
}
