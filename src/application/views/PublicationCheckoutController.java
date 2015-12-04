package application.views;

import java.time.LocalDate;
import java.util.List;

import application.Main;
import application.Dao.CheckoutRecordDao;
import application.models.CheckoutEntry;
import application.models.CheckoutRecord;
import application.models.PublicationCopy;
import application.util.DateHelper;
import application.util.Utils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PublicationCheckoutController {
	@FXML
	private TableView<CheckoutEntry> entries;

	@FXML
	private TableColumn<CheckoutEntry, Number> entryIdColumn;

	@FXML
	private Label bookIdLabel;

	@FXML
	private TextField searchField;

	@FXML
	private Label isbnLabel;

	@FXML
	private Label titleLabel;

	@FXML
	private Label checkoutDateLabel;

	@FXML
	private Label dueDateLabel;

	@FXML
	private Label returnDateLabel;

	@FXML
	private Label isFineLabel;

	@FXML
	private Label finePaidLabel;

	@FXML
	private Button checkBookBtn;

	@FXML
	private Button returnBookBtn;

	private ObservableList<CheckoutEntry> entryList;

	private Main main;
	private Stage checkoutEntryStage;

	public PublicationCheckoutController(){

	}

	@FXML
	private void initialize(){
//		entryIdColumn.setCellValueFactory(cellData -> cellData.getValue().entryIdProperty());
		entryIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CheckoutEntry,Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(CellDataFeatures<CheckoutEntry, Number> param) {
				return new SimpleIntegerProperty(param.getValue().geteId());
			}
		});


//		entryIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CheckoutEntry,Integer>, ObservableValue<Integer>>() {
//
//			@Override
//			public ObservableValue<Integer> call(CellDataFeatures<CheckoutEntry, Integer> param) {
//				// TODO Auto-generated method stub
//
//				return new SimpleIntegerProperty(1).asObject();
////				new SimpleIntegerProperty(integer_value).asObject()
//			}
//		});;

		showCheckoutEntryDetails(null);

//		entries.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCheckoutEntryDetails(newValue));
		entries.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CheckoutEntry>() {
			@Override
			public void changed(ObservableValue<? extends CheckoutEntry> observable, CheckoutEntry oldValue,
					CheckoutEntry newValue) {
				showCheckoutEntryDetails(newValue);
			}
		});

//		tablePublicationCopies.getSelectionModel().selectedItemProperty()
//		.addListener(new ChangeListener<PublicationCopy>() {
//			@Override
//			public void changed(ObservableValue<? extends PublicationCopy> observable, PublicationCopy oldValue,
//					PublicationCopy newValue) {
//				showPublicationCopyDetails(newValue);
//			}
//		});
		this.checkBookBtn.setDisable(true);
	}

	public void setMain(Main main, int memberId){
		this.main = main;

		CheckoutRecordDao crd = new CheckoutRecordDao();
		List<CheckoutRecord> records =  crd.loadObjs(); //main.getCheckRecordData();
		ObservableList<CheckoutEntry> olist = FXCollections.observableArrayList();
		boolean isMemberFound = false;

		for(CheckoutRecord ckRecord : records){
			if(ckRecord.getmId() == memberId){
				isMemberFound = true;
				olist = FXCollections.observableArrayList(ckRecord.getEnties());
			}
		}

		entries.setItems(olist);

//		if(!isMemberFound){
//			Alert errorAlert = new Alert(AlertType.WARNING);
//			errorAlert.initOwner(checkoutEntryStage);
//			errorAlert.setTitle("Warning");
//			errorAlert.setHeaderText("No member with this Id exists!");
//			errorAlert.setContentText("No member with this Id exists!");
//
//			errorAlert.showAndWait();
//		}else{
//			entries.setItems(olist);
//		}
	}

	public void showCheckoutEntryDetails(CheckoutEntry entry){
		if(entry != null){

			bookIdLabel.setText(entry.getpId() + "");
	//TODO:
//			isbnLabel.setText(entry.getPublication().getISBN());
//			titleLabel.setText(entry.getPublication().getTitle());

			checkoutDateLabel.setText(DateHelper.format(entry.getCheckoutDate()));
			dueDateLabel.setText(DateHelper.format(entry.getDueDate()));
			returnDateLabel.setText(DateHelper.format(entry.getReturnDate()));
			if(entry.isFine()){
				isFineLabel.setText("true");
			}else{
				isFineLabel.setText("false");
			}
			finePaidLabel.setText(entry.getFinePaid() + "");
			if(entry.getReturnDate() == null){
				this.returnBookBtn.setDisable(false);
			}
		}else{
			bookIdLabel.setText("");
			isbnLabel.setText("");
			titleLabel.setText("");
			checkoutDateLabel.setText("");
			dueDateLabel.setText("");
			returnDateLabel.setText("");
			isFineLabel.setText("");
			finePaidLabel.setText("");
			this.returnBookBtn.setDisable(true);
		}
	}

	public void setCheckoutStage(Stage checkoutEntryStage){
		this.checkoutEntryStage = checkoutEntryStage;
	}

	@FXML
	private void handleSearch(){
		this.checkBookBtn.setDisable(true);
		String keyword = this.searchField.getText().trim();
		Alert alert = new Alert(AlertType.ERROR);
		int memberId = 0;
		try {
			memberId = Integer.parseInt(keyword);
		}catch (NumberFormatException e){

			alert.setContentText("Member id must be numeric.");
			alert.showAndWait();
			return;
		}

		CheckoutRecordDao checkout = new CheckoutRecordDao();
		CheckoutRecord record =  checkout.getCheckoutRecordFromPid(memberId);
		if(record == null){

			alert.setContentText("No member with this id exists.");
			alert.showAndWait();
			return;
		}
		this.entryList = FXCollections.observableList(record.getEnties());
		this.entries.setItems(this.entryList);
		this.checkBookBtn.setDisable(false);
	}

	@FXML
	private void handleCheckBook(){
		Utils.gotoNextScene(PublicationAvailabilityCheckController.class, "PublicationAvailabilityCheck.fxml", new Utils.ISceneControllerSetting() {
			@Override
			public BaseController prepareForController(FXMLLoader fxmlLoader) {
				PublicationAvailabilityCheckController controller = fxmlLoader.getController();
				String idStr =  searchField.getText();
				int memberId = 0;
				try {
					memberId = Integer.parseInt(idStr);
				}catch (NumberFormatException e){
				}
				controller.setMemberId(memberId);
				return controller;
			}
		},null);
	}

	@FXML
	private void handleReturnBook(){
		CheckoutEntry checkoutEntry =  this.entries.getSelectionModel().getSelectedItem();
		if(checkoutEntry.getReturnDate() == null){
			checkoutEntry.setReturnDate(LocalDate.now());
		}
		this.showCheckoutEntryDetails(checkoutEntry);
		this.returnBookBtn.setDisable(true);

	}
}
