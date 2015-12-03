package application.views;

import java.util.List;
import java.util.Optional;

import application.Dao.PersonDao;
import application.Main;
import application.models.Person;
import application.util.DateHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class MemberOverviewController {
	@FXML
	private TextField idField;

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

	private ObservableList<Person> personlist;

	// Reference to the main application.
	private Main main;

	public MemberOverviewController(){}

	@FXML
	private void initialize(){

		PersonDao personDao = new PersonDao();
		this.personlist = FXCollections.observableArrayList();
		List<Person> allPersons = personDao.loadALlPersons();
		for(Person person : allPersons){
			this.personlist.add(person);
		}
		this.personTable.setItems(this.personlist);
		if(this.personlist.size() > 0){
			showMemberDetails(this.personlist.get(0));
		}

		firstNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Person, String> param) {
				return new SimpleStringProperty(param.getValue().getFirstName()) ;
			}
		});
		
		lastNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Person, String> param) {
				
				return new SimpleStringProperty(param.getValue().getLastName()) ;
			}
		});


		personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
			@Override
			public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
				showMemberDetails(newValue);
				
			}
		});
	}

	public void setMain(Main main){
		this.main = main;
	}

	private void showMemberDetails(Person member){
		if(member != null){
			firstNameLabel.setText(member.getFirstName());
			lastNameLabel.setText(member.getLastName());
			birthdayLabel.setText(DateHelper.format(member.getDob()));
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
			//TODO
			PersonDao pd = new PersonDao(); 
			pd.loadALlPersons().add(p);
			
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

	@FXML
	private void handleMemberIDSearch(){
		String memberId = this.idField.getText().trim();
		if(memberId.length() == 0){
			this.personTable.setItems(this.personlist);
			return ;
		}
		ObservableList<Person> tmpList = FXCollections.observableArrayList();
		Alert alert = new Alert(AlertType.ERROR);
		int targetId  = 0;
		try{
			targetId = Integer.parseInt(memberId);
		}catch (NumberFormatException e){
			alert.setContentText("Member ID must be numeric");
			alert.showAndWait();
			return;
		}
		for(Person person : this.personlist){
			int id = person.getPid();
			if(id == targetId){
				tmpList.add(person);
			}
		}
		if(tmpList.size() == 0) {
			alert.setContentText("Can't find member.");
			alert.showAndWait();
			return;
		}else{
			this.personTable.setItems(tmpList);
		}

	}

	@FXML
	private void handleNameSearch(){
		String searchName = this.nameField.getText().trim().toLowerCase();
		if(searchName.length() == 0){
			this.personTable.setItems(this.personlist);
			return;
		}
		ObservableList<Person> tmpList = FXCollections.observableArrayList();
		for(Person person : this.personlist){
			if(person.getFirstName().toLowerCase().contains(searchName) || person.getLastName().toLowerCase().contains(searchName)){
				tmpList.add(person);
			}
		}
		if(tmpList.size() == 0){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Can't find member.");
			alert.showAndWait();
			return;
		}else {
			this.personTable.setItems(tmpList);
		}
	}
}
