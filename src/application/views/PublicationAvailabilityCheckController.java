package application.views;

import application.Dao.PersonDao;
import application.Dao.PublicationDao;
import application.Main;
import application.models.Author;
import application.models.Person;
import application.models.Publication;
import application.models.PublicationCopy;
import application.util.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;


public class PublicationAvailabilityCheckController extends BaseController {
	@FXML
	private TableView<PublicationCopy> tablePublicationCopies;

	@FXML
	private Label publicationIdLabel;

	@FXML
	private TableColumn<PublicationCopy, String> publicationCopyColumn;

	@FXML
	private Label isbnLabel;

	@FXML
	private Label titleLabel;

	@FXML
	private Label authorsLabel;

	@FXML
	private Label allowedBorrowDaysLabel;

	@FXML
	private Label availabilityLabel;

	@FXML
	private TextField ISBNField;

	@FXML
	private Button createCheckoutBtn;

	private Publication publication;
	private ObservableList<PublicationCopy> dataList;


	private int memberId;

	private Main main;

	private List<PublicationCopy> publicationCopies;

	private Stage publicationCopyStage;

	public PublicationAvailabilityCheckController() {
	}

	@FXML
	private void initialize() {
		publicationCopyColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<PublicationCopy, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<PublicationCopy, String> param) {
						return new SimpleStringProperty(param.getValue().getPcId());
					}
				});

		showPublicationCopyDetails(null);

		tablePublicationCopies.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<PublicationCopy>() {
					@Override
					public void changed(ObservableValue<? extends PublicationCopy> observable, PublicationCopy oldValue,
							PublicationCopy newValue) {
						showPublicationCopyDetails(newValue);
						createCheckoutBtn.setDisable(false);
					}
				});

		// publicationIdColumn.setCellValueFactory(new
		// Callback<TableColumn.CellDataFeatures<Publication,Number>,
		// ObservableValue<Number>>() {
		//
		// @Override
		// public ObservableValue<Number> call(CellDataFeatures<Publication,
		// Number> param) {
		// return new SimpleIntegerProperty(param.getValue().getbId());
		// }
		// });
		//
		// isbnColumn.setCellValueFactory(new
		// Callback<TableColumn.CellDataFeatures<Publication,String>,
		// ObservableValue<String>>() {
		//
		// @Override
		// public ObservableValue<String> call(CellDataFeatures<Publication,
		// String> param) {
		// return new SimpleStringProperty(param.getValue().getISBN());
		// }
		// });
		//
		// titleColumn.setCellValueFactory(new
		// Callback<TableColumn.CellDataFeatures<Publication,String>,
		// ObservableValue<String>>() {
		// @Override
		// public ObservableValue<String> call(CellDataFeatures<Publication,
		// String> param) {
		// return new SimpleStringProperty(param.getValue().getTitle());
		// }
		// });
		//
		// authorColumn.setCellValueFactory(new
		// Callback<TableColumn.CellDataFeatures<Publication,String>,
		// ObservableValue<String>>() {
		// @Override
		// public ObservableValue<String> call(CellDataFeatures<Publication,
		// String> param) {
		// return new SimpleStringProperty(param.getValue().getTitle());
		// }
		// });
		//
		// allowedBorrowDaysColumn.setCellValueFactory(new
		// Callback<TableColumn.CellDataFeatures<Publication,Object>,
		// ObservableValue<Object>>() {
		// @Override
		// public ObservableValue<Object> call(CellDataFeatures<Publication,
		// Object> param) {
		// return new
		// SimpleObjectProperty(param.getValue().getAllowedBorrowkDays());
		// }
		// });
		//
		// availabilityColumn.setCellValueFactory(new
		// Callback<TableColumn.CellDataFeatures<Publication,String>,
		// ObservableValue<String>>() {
		// @Override
		// public ObservableValue<String> call(CellDataFeatures<Publication,
		// String> param) {
		// if(param.getValue()param.getValue())
		// return new SimpleStringProperty(param.getValue().getBookCopies().)
		// }
		// });
	}

	public void showPublicationCopyDetails(PublicationCopy publicationCopy) {
		if (publicationCopy != null) {
			publicationIdLabel.setText(publicationCopy.getpId() + "");
			
			isbnLabel.setText(this.publication.getISBN());
			titleLabel.setText(this.publication.getTitle());
			authorsLabel.setText(this.publication.getAuthorString());


			allowedBorrowDaysLabel.setText(String.valueOf(this.publication.getIntAllowBorrowDays()));

			if (publicationCopy.isAvailable()) {
				availabilityLabel.setText("true");
			} else {
				availabilityLabel.setText("false");
			}
		} else {
			publicationIdLabel.setText("");
			isbnLabel.setText("");
			titleLabel.setText("");
			authorsLabel.setText("");
			allowedBorrowDaysLabel.setText("");
			availabilityLabel.setText("");
		}
	}

	public void setMain(Main main, String isbn) {
		this.main = main;

		PublicationDao pd = new PublicationDao();
		
		List<Publication> publications = pd.loadAllPublication();

		for (Publication p : publications) {
			if (p.getISBN().equals(isbn)) {
				publicationCopies = p.getPublicationCopies();
				break;
			}
		}

		tablePublicationCopies.setItems(FXCollections.observableArrayList(publicationCopies));
	}

	public void setPublicationCopyStage(Stage publicationCopyStage) {
		this.publicationCopyStage = publicationCopyStage;
	}

	public void setMemberId(int memberId){
		this.memberId = memberId;
	}

	@FXML
	private void handleSearch(){
		String ISBN = this.ISBNField.getText().trim();
		Alert alert = new Alert(Alert.AlertType.ERROR);
		if(ISBN == null || ISBN.length() == 0){
			alert.setContentText("Please input ISBN number.");
			alert.showAndWait();
			return;
		}

		PublicationDao publicationDao = new PublicationDao();
		Publication publication =  publicationDao.getPublicationByISBN(ISBN);
		if(publication == null){
			alert.setContentText("No book with this ISBN exists.");
			alert.showAndWait();
			return;
		}

		this.publication = publication;
		this.dataList = FXCollections.observableList(publication.getPublicationCopies());
		this.tablePublicationCopies.setItems(this.dataList);
	}

	@FXML
	private void handleCreateCheckoutRecord(){
		Utils.gotoNextScene(CheckoutSceneController.class, "CheckoutScene.fxml", new Utils.ISceneControllerSetting() {
			@Override
			public BaseController prepareForController(FXMLLoader fxmlLoader) {
				CheckoutSceneController controller = fxmlLoader.getController();
				PersonDao personDao = new PersonDao();
				Person person = personDao.loadPersonById(memberId);
				controller.setPerson(person);
				controller.setPublication(publication);
				PublicationCopy copy = tablePublicationCopies.getSelectionModel().getSelectedItem();
				controller.setPublicationCopy(copy);
				return controller;
			}
		},this.curStage);
	}
}
