package application.views;

import application.Main;
import application.models.CheckoutEntry;
import application.models.CheckoutRecord;
import application.models.PublicationCopy;
import application.util.DateHelper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	}

	public void setMain(Main main, int memberId){
		this.main = main;

		ObservableList<CheckoutRecord> records = main.getCheckRecordData();
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
			//TODO:
//			bookIdLabel.setText(entry.getPublication().getpId() + "");
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
		}else{
			bookIdLabel.setText("");
			isbnLabel.setText("");
			titleLabel.setText("");
			checkoutDateLabel.setText("");
			dueDateLabel.setText("");
			returnDateLabel.setText("");
			isFineLabel.setText("");
			finePaidLabel.setText("");
		}
	}

	public void setCheckoutStage(Stage checkoutEntryStage){
		this.checkoutEntryStage = checkoutEntryStage;
	}
}
