package application.views;

import application.Dao.AuthorDao;
import application.Dao.PublicationDao;
import application.models.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by su on 12/3/15.
 */
public class BookEditDialogController extends BaseController {
    @FXML
    private TextField titleField;

    @FXML
    private TextField ISBNField;

    @FXML
    private ComboBox<String> dayComboBox;

    @FXML
    private ComboBox<PublicationType>  typeComboBox;

    @FXML
    private TextField countField;

    @FXML
    private ComboBox<Author> authorComboBox;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField city;

    @FXML
    private TextField zipField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField credentialField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField nationalityField;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    private IEditPublication ie;

    private ObservableList<String> daysData;

    private ObservableList<Author> authorData;

    private ObservableList<PublicationType> typeData;

    @FXML
    private void initialize(){
        this.daysData = FXCollections.observableArrayList();
        this.daysData.add("7");
        this.daysData.add("21");
        this.dayComboBox.setItems(this.daysData);
        this.dayComboBox.setValue("7");

        this.typeData = FXCollections.observableArrayList();
        Book book = new Book();
        Periodical periodical = new Periodical();
        this.typeData.add(book);
        this.typeData.add(periodical);
        this.typeComboBox.setItems(this.typeData);
        this.typeComboBox.getSelectionModel().selectFirst();


        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.loadAllAuthor();
        if(authorList == null){
            authorList = new ArrayList<Author>();
        }

        this.authorData = FXCollections.observableArrayList(authorList);
        Author tmpAuthor = new Author(0,"add","",null,"","","");
        this.authorData.add(tmpAuthor);
        this.authorComboBox.setItems(this.authorData);
        this.authorComboBox.valueProperty().addListener(new ChangeListener<Author>() {
            @Override
            public void changed(ObservableValue<? extends Author> observable, Author oldValue, Author newValue) {
                showAuthor(newValue);
            }
        });
        this.authorComboBox.getSelectionModel().selectLast();

//        .valueProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue ov, String t, String t1) {
//                System.out.println(ov);
//                System.out.println(t);
//                System.out.println(t1);
//            }
//        });

    }


    private void showAuthor(Author author){
        if(author != null && author.getaId() != 0){
            this.firstNameField.setText(author.getFirstName());
            this.firstNameField.setDisable(true);
            this.lastNameField.setText(author.getLastName());
            this.lastNameField.setDisable(true);
            this.streetField.setText(author.getAddress().getStreet());
            this.streetField.setDisable(true);
            this.zipField.setText(author.getAddress().getZip());
            this.zipField.setDisable(true);
            this.stateField.setText(author.getAddress().getState());
            this.stateField.setDisable(true);
            this.credentialField.setText(author.getCredential());
            this.credentialField.setDisable(true);
            this.descriptionField.setText(author.getDescription());
            this.descriptionField.setDisable(true);
            this.nationalityField.setText(author.getNationality());
            this.nationalityField.setDisable(true);
            this.city.setText(author.getAddress().getCity());
            this.city.setDisable(true);
        }else{
            this.firstNameField.setText("");
            this.firstNameField.setDisable(false);
            this.lastNameField.setText("");
            this.lastNameField.setDisable(false);
            this.streetField.setText("");
            this.streetField.setDisable(false);
            this.zipField.setText("");
            this.zipField.setDisable(false);
            this.stateField.setText("");
            this.stateField.setDisable(false);
            this.credentialField.setText("");
            this.credentialField.setDisable(false);
            this.descriptionField.setText("");
            this.descriptionField.setDisable(false);
            this.nationalityField.setText("");
            this.nationalityField.setDisable(false);
            this.city.setText("");
            this.city.setDisable(false);
        }
    }

    @FXML
    private void handleOkAction(){

        String title = this.titleField.getText().trim();
        String ISBN = this.ISBNField.getText().trim();
        String countStr = this.countField.getText().trim();
        String dayStr = this.dayComboBox.getValue();
        PublicationType type = this.typeComboBox.getValue();
        Author author = this.authorComboBox.getValue();
        if(author.getaId() == 0){
            String firstName = this.firstNameField.getText().trim();
            String lastName = this.lastNameField.getText().trim();
            String street = this.streetField.getText().trim();
            String zip = this.zipField.getText().trim();
            String state = this.stateField.getText().trim();
            String credential = this.credentialField.getText().trim();
            String description = this.descriptionField.getText().trim();
            String nationality = this.nationalityField.getText().trim();
            String city = this.city.getText().trim();
            Address address = new Address(street,city,state,zip);
            author = new Author(0,firstName,lastName,address,credential,description,nationality);
        }

        int countInt = 0;
        try{
            countInt = Integer.parseInt(countStr);
        }catch (NumberFormatException e){

        }
        AllowedBorrowDays days = AllowedBorrowDays.SEVEN_DAYS;
        if(dayStr.equals("7")) {
            days = AllowedBorrowDays.SEVEN_DAYS;
        }else if(dayStr.equals("21")){
            days = AllowedBorrowDays.TWENTY_ONE_DAYS;
        }

        List<Author>  authorList = new ArrayList<Author>();
        authorList.add(author);
        Publication publication = new Publication(0, LocalDate.now(),title,ISBN,authorList,days,type);
        for(int i = 2; i <= countInt; i ++){
            publication.addCopy(LocalDate.now());
        }
        this.ie.editPublication(publication,true);
        this.curStage.close();
    }

    public void setIe(IEditPublication ie) {
        this.ie = ie;
    }

    @FXML
    private void handleCancelAction(){
        this.curStage.close();
    }

}
