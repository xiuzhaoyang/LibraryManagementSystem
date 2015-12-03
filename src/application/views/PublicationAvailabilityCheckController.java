package application.views;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import application.Dao.PublicationDao;
import application.models.Author;
import application.models.Publication;
import application.models.PublicationCopy;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PublicationAvailabilityCheckController {
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
					public ObservableValue<String> call(CellDataFeatures<PublicationCopy, String> param) {
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
			
			//TODO  to load publication
			Publication pb  =  null;
			isbnLabel.setText(pb.getISBN());
			titleLabel.setText(pb.getTitle());

			List<Author> authors = pb.getAuthors();
			StringBuilder sb = new StringBuilder();
			for (Author a : authors) {
				sb.append(a.getFirstName());
				sb.append(" ");
				sb.append(a.getLastName());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			authorsLabel.setText(sb.toString());

			allowedBorrowDaysLabel.setText(pb.getAllowedBorrowDays() + "");

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
				publicationCopies = p.getpublicationCopies();
				break;
			}
		}

		tablePublicationCopies.setItems(FXCollections.observableArrayList(publicationCopies));
	}

	public void setPublicationCopyStage(Stage publicationCopyStage) {
		this.publicationCopyStage = publicationCopyStage;
	}
}
