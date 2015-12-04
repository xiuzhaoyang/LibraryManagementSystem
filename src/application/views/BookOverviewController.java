package application.views;

import application.Dao.PublicationDao;
import application.models.AllowedBorrowDays;
import application.models.IEditPerson;
import application.models.IEditPublication;
import application.models.Publication;
import application.util.Constants;
import application.util.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by su on 12/3/15.
 */
public class BookOverviewController extends BaseController implements IEditPublication {
    @FXML
    private TextField ISBNSearchField;

    @FXML
    private TextField nameSearchField;

    @FXML
    private TableView<Publication> tableView;

    @FXML
    private ObservableList<Publication> publicationList;

    @FXML
    private TableColumn<Publication, String> ISBNColumn;

    @FXML
    private TableColumn<Publication, String> nameColumn;

    @FXML
    private Label idLabel;

    @FXML
    private Label ISBNLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorsLabel;

    @FXML
    private Label borrowDaysLabel;

    @FXML
    private Label addDateLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label countLabel;

    @FXML
    private Label availableLabel;

    @FXML
    private Button addCopyBtn;

    private Publication publication;


    @FXML
    private void initialize(){

        this.ISBNColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Publication, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Publication, String> param) {
                return new SimpleStringProperty(param.getValue().getISBN());
            }
        });

        this.nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Publication, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Publication, String> param) {
                return new SimpleStringProperty(param.getValue().getTitle());
            }
        });

        PublicationDao pd = new PublicationDao();
        List<Publication> list = pd.loadAllPublication();
        this.publicationList = FXCollections.observableList(list);
        this.tableView.setItems(this.publicationList);

        this.tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Publication>() {
            @Override
            public void changed(ObservableValue<? extends Publication> observable, Publication oldValue, Publication newValue) {
                showDetail(newValue);

            }
        });
        if(this.publicationList.size() != 0){
            showDetail(this.publicationList.get(0));
        }
    }

    public void showDetail(Publication  publication){
        this.idLabel.setText(String.valueOf(publication.getpId()));
        this.ISBNLabel.setText(publication.getISBN());
        this.titleLabel.setText(publication.getTitle());
        this.authorsLabel.setText(publication.getAuthorString());
        if(publication.getAllowedBorrowDays() == AllowedBorrowDays.SEVEN_DAYS){
            this.borrowDaysLabel.setText("7");
        }else if(publication.getAllowedBorrowDays() == AllowedBorrowDays.TWENTY_ONE_DAYS){
            this.borrowDaysLabel.setText("20");
        }
        this.addDateLabel.setText(publication.getAddDate().toString());
        this.typeLabel.setText(publication.getPublicationType().getType());
        this.countLabel.setText(String.valueOf(publication.getCopyCount()));
        this.availableLabel.setText(String.valueOf(publication.getAvailableCount()));
        this.publication = publication;
        this.addCopyBtn.setDisable(false);

    }


    @FXML
    private void handleSearchByISBN(){
        String keyword = this.ISBNSearchField.getText().trim();
        if(keyword.length() == 0){
            this.tableView.setItems(this.publicationList);
            return ;
        }
        ObservableList<Publication> tmpList = FXCollections.observableArrayList();
        for(Publication publication : this.publicationList){
            String ISBN = publication.getISBN();
            if(ISBN.equals(keyword)){
                tmpList.add(publication);
            }
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(tmpList.size() == 0) {
            alert.setContentText("Can't find publication.");
            alert.showAndWait();
            return;
        }else{
            this.tableView.setItems(tmpList);
        }
    }

    @FXML
    private void handleSearchByName(){
        String keyword = this.nameSearchField.getText().trim();
        if(keyword.length() == 0){
            this.tableView.setItems(this.publicationList);
            return ;
        }
        ObservableList<Publication> tmpList = FXCollections.observableArrayList();
        for(Publication publication : this.publicationList){
            String title = publication.getTitle();
            if(title.toLowerCase().equals(keyword.toLowerCase())){
                tmpList.add(publication);
            }
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(tmpList.size() == 0) {
            alert.setContentText("Can't find publication.");
            alert.showAndWait();
            return;
        }else{
            this.tableView.setItems(tmpList);
        }
    }

    @FXML
    private void handleAddPublication(){
        Utils.gotoNextScene(BookEditDialogController.class, "BookEditDialog.fxml", new Utils.ISceneControllerSetting() {
            @Override
            public BaseController prepareForController(FXMLLoader fxmlLoader) {
                BookEditDialogController controller = fxmlLoader.getController();
                controller.setIe(BookOverviewController.this);
                return controller;
            }
        },null);
    }

    @FXML
    private void handleEditPublication(){


    }

    @FXML
    private void handleDeletePublication(){
        int selectedIndex = this.tableView.getSelectionModel().getSelectedIndex();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Please confirm your action!");
        alert.setContentText("Are you sure you want to delete this publication?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if(selectedIndex >= 0){
                this.publicationList.remove(selectedIndex);
                this.tableView.setItems(this.publicationList);
            }else{
                //no effect
                Alert errorAlert = new Alert(Alert.AlertType.WARNING);
                errorAlert.setTitle("Error Dialog");
                errorAlert.setHeaderText("Please check your action!");
                errorAlert.setContentText("You haven't choose any publication yet!");
                errorAlert.showAndWait();
            }
        }
    }

    @Override
    public void editPublication(Publication p, boolean isNew) {
        if(isNew){
            Publication publication = this.publicationList.get(this.publicationList.size() - 1);
            p.setpId(publication.getpId() + 1);
            this.publicationList.addAll(p);
            this.tableView.setItems(this.publicationList);
        }
    }

    @FXML
    private void handleAddCopy(){
        this.publication.addCopy(LocalDate.now());
        this.tableView.setItems(this.publicationList);
        showDetail(this.publication);
    }
}
