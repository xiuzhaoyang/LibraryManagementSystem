package application.views;

import java.util.Optional;

import application.Main;
import application.models.Person;
import application.util.DateHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MemberOverviewController {
	@FXML
	private TextField isbnField;

	@FXML
	private TextField nameField;

	@FXML
	private TableView<Person> personTable;

	@FXML
	private TableColumn<Person, String> firstNameColumn;

	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;

	@FXML
	private Label lastNameLabel;

	@FXML
	private Label birthdayLabel;

	@FXML
	private Label streetLabel;

	@FXML
	private Label cityLabel;

	@FXML
	private Label stateLabel;

	@FXML
	private Label zipCodeLabel;

	@FXML
	private Label phoneNumLabel;

	@FXML
	private Label rolesLabel;

	// Reference to the main application.
	private Main main;

	public MemberOverviewController(){}

	@FXML
	private void initialize(){
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		showMemberDetails(null);

		personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showMemberDetails(newValue));
	}

	public void setMain(Main main){
		this.main = main;

		personTable.setItems(main.getPersonInfo());
	}

	private void showMemberDetails(Person member){
		if(member != null){
			firstNameLabel.setText(member.getFirstName());
			lastNameLabel.setText(member.getLastName());
			birthdayLabel.setText(DateHelper.format(member.getBirthday()));
			streetLabel.setText(member.getAddress().getStreet());
			cityLabel.setText(member.getAddress().getCity());
			stateLabel.setText(member.getAddress().getState());
			zipCodeLabel.setText(member.getAddress().getZip());
			phoneNumLabel.setText(member.getPhoneNum());
			rolesLabel.setText(member.getPersonRolesToString());
		}else{
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			birthdayLabel.setText("");
			streetLabel.setText("");
			cityLabel.setText("");
			stateLabel.setText("");
			zipCodeLabel.setText("");
			phoneNumLabel.setText("");
			rolesLabel.setText("");
		}
	}

	@FXML
	private void handleDeleteMember(){
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Please confirm your action!");
		alert.setContentText("Are you sure you want to delete this member?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			if(selectedIndex >= 0){
				personTable.getItems().remove(selectedIndex);
			}else{
				//no effect
				Alert errorAlert = new Alert(AlertType.WARNING);
				errorAlert.initOwner(main.getPrimaryStage());
				errorAlert.setTitle("Error Dialog");
				errorAlert.setHeaderText("Please check your action!");
				errorAlert.setContentText("You haven't choose any member yet!");
				errorAlert.showAndWait();
			}
		}
	}

	@FXML
	private void handleCreateMember(){
		Person p = new Person();
		boolean isOk = main.showPersonEditDialog(p);

		if(isOk){
			main.getPersonInfo().add(p);
		}
	}

	@FXML
	private void handleEditMember(){
		Person selectedMember = personTable.getSelectionModel().getSelectedItem();
		if(selectedMember != null){
			boolean isOk = main.showPersonEditDialog(selectedMember);

			if(isOk){
				showMemberDetails(selectedMember);
			}
		}else{
			Alert warnAlert = new Alert(AlertType.WARNING);
			warnAlert.initOwner(main.getPrimaryStage());
			warnAlert.setTitle("No Member Selected");
			warnAlert.setHeaderText("No member Selected");
			warnAlert.setContentText("Please select a member from the table.");

			warnAlert.showAndWait();
		}
	}
}
