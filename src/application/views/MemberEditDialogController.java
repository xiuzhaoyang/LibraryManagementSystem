package application.views;

import java.util.ArrayList;
import java.util.List;

import application.models.Address;
import application.models.Administrator;
import application.models.IEditPerson;
import application.models.Librarian;
import application.models.LibraryMember;
import application.models.Person;
import application.models.PersonRole;
import application.util.DateHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MemberEditDialogController extends BaseController {
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

	// @FXML
	// private TextField personRolesField;

	@FXML
	private CheckBox adminChkbox;

	@FXML
	private CheckBox libranianChkbox;

	@FXML
	private CheckBox libMemberChkbox;

	private boolean isEdit = true;
	private boolean isNew = true;

	// private Stage dialogStage;
	private Person member;
	private boolean isOk = false;

	IEditPerson iEditPerson;

	@FXML
	private void initialize() {

	}

	// public void setDialogStage(Stage dialogStage){
	// this.dialogStage = dialogStage;
	// }

	public void setOverView(IEditPerson ie) {
		iEditPerson = ie;
	}

	public void setMember(Person member) {

		if (member.getFirstName() == null) {
			this.isEdit = false;
		}

		this.member = member;

		// int id = member.getId();
		idLabel.setText(member.getPid() + "");
		firstNameField.setText(member.getFirstName());
		lastNameField.setText(member.getLastName());
		birthdayField.setText(DateHelper.format(member.getDob()));
		birthdayField.setPromptText("dd.mm.yyyy");
		streetField.setText(member.getAddress().getStreet());
		cityField.setText(member.getAddress().getCity());
		stateField.setText(member.getAddress().getState());
		zipCodeField.setText(member.getAddress().getZip());
		zipCodeField.setPromptText("12345, 5 digit numbers");
		phoneField.setText(member.getPhoneNum() == null ? "" : member.getPhoneNum());
		phoneField.setPromptText("123456789, 9 digit numbers");
		// personRolesField.setText(member.getPersonRolesToString());

		List<PersonRole> personRoles = member.getPersonRoles();
		if (personRoles != null) {
			for (PersonRole role : personRoles) {
				if (role.getPersonRole().equals(Person.PERSON_TYPE_ADMIN)) {
					adminChkbox.setSelected(true);
				}

				if (role.getPersonRole().equals(Person.PERSON_TYPE_LIBRARIAN)) {
					libranianChkbox.setSelected(true);
				}

				if (role.getPersonRole().equals(Person.PERSON_TYPE_MEMBER)) {
					libMemberChkbox.setSelected(true);
				}
			}
		}
	}

	public boolean checkIsOk() {
		return this.isOk;
	}

	@FXML
	private void handleOK() {
		if (isInputValid()) {
			member.setPid(Integer.valueOf(idLabel.getText()));
			member.setFirstName(firstNameField.getText());
			member.setLastName(lastNameField.getText());
			member.setDob(DateHelper.parse(birthdayField.getText()));

			String street = streetField.getText();
			String city = cityField.getText();
			String state = stateField.getText();
			String zipCode = zipCodeField.getText();
			Address addr = new Address(street, city, state, zipCode);
			member.setAddress(addr);

			member.setPhoneNum(phoneField.getText());

			StringBuilder sb = new StringBuilder();

			List<PersonRole> personRoles = new ArrayList<PersonRole>();
			if (adminChkbox.isSelected()) {
				personRoles.add(new Administrator());
			}

			if (libranianChkbox.isSelected()) {
				personRoles.add(new Librarian());
			}

			if (libMemberChkbox.isSelected()) {
				personRoles.add(new LibraryMember());
			}

			member.setPersonRoles(personRoles);
			// member.setPersonRoleString(personRolesField.getText());

			isOk = true;
			iEditPerson.editPerson(member, this.isEdit);
			curStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		curStage.close();
	}

	private boolean isInputValid() {
		StringBuilder sb = new StringBuilder();

		// try{
		// Integer.parseInt(idField.getText());
		// }catch(NumberFormatException e){
		// sb.append("Please enter a numeric id number");
		// }

		if (isNullOrEmpty(firstNameField.getText())) {
			sb.append("First name input is not valid.\n");
		}

		if (isNullOrEmpty(lastNameField.getText())) {
			sb.append("Last name input is not valid.\n");
		}

		if (isNullOrEmpty(birthdayField.getText())) {
			sb.append("Birthday input is not valid.\n");
		} else if (!DateHelper.validDate(birthdayField.getText())) {
			sb.append("Please use format dd.mm.yyyy.\n");
		}

		if (isNullOrEmpty(streetField.getText())) {
			sb.append("Street input is not valid.\n");
		}

		if (isNullOrEmpty(cityField.getText())) {
			sb.append("City input is not valid.\n");
		}

		if (isNullOrEmpty(stateField.getText())) {
			sb.append("State input is not valid.\n");
		}

		if (isNullOrEmpty(zipCodeField.getText())) {
			sb.append("Zip code input in not valid.\n");
		} else {
			try {
				Integer.parseInt(zipCodeField.getText());
			} catch (NumberFormatException e) {
				sb.append("Please input valid digit number.\n");
			}

			////////////////////////////////////////////
			if (zipCodeField.getText().length() != 5) {
				sb.append("Please input 5 digit number in zip code field.\n");
			}
		}

		if (isNullOrEmpty(phoneField.getText())) {
			sb.append("Phone number input in not valid.\n");
		} else {
			try {
				Integer.parseInt(phoneField.getText());
			} catch (NumberFormatException e) {
				sb.append("Please input valid digit number in phone field.\n");
			}

			if (phoneField.getText().length() != 9) {
				sb.append("Please input 9 digit number in phone field.\n");
			}
		}

		// if(isNullOrEmpty(personRolesField.getText())){
		// sb.append("Person role input is not valid.\n");
		// }else{
		// String[] roles = personRolesField.getText().split(",");
		//
		// for(String role : roles){
		// if(!role.equals(Person.PERSON_TYPE_LIBRARIAN) &&
		// !role.equals(Person.PERSON_TYPE_MEMBER) &&
		// !role.equals(Person.PERSON_TYPE_ADMIN)){
		// sb.append("Please input correct role.\n");
		// }
		// }
		// }

		if (!adminChkbox.isSelected() && !libranianChkbox.isSelected() && !libMemberChkbox.isSelected()) {
			sb.append("Person role input is not valid.\n");
		}

		if (sb.length() == 0) {
			return true;
		} else {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.initOwner(curStage);
			errorAlert.setTitle("You have invalid Fields");
			errorAlert.setHeaderText("Please check your input");
			errorAlert.setContentText(sb.toString());

			errorAlert.showAndWait();
			return false;
		}
	}

	private boolean isNullOrEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}

		return false;
	}
}
