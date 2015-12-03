package application.views;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import application.Dao.CheckoutRecordDao;
import application.models.CheckoutEntry;
import application.models.CheckoutRecord;
import application.models.Person;
import application.models.Publication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class CheckoutSceneController extends BaseController{
	
	Person person;
	
	Publication publication;
	
	LocalDate dueDate;
	
	@FXML
	private Label userNameLB;

	@FXML
	private Label userIdLB;

	@FXML
	private Label bookLB;

	@FXML
	private Label authorLB;

	@FXML
	private Label isbnLB;
	
	@FXML
	private DatePicker datePicker;
	
	
	@FXML
	private Button ckBt;
	
	
	@FXML
    void initialize() {
		
		setDatePicker();	
	}
	
	
	private void setDatePicker(){
		
		datePicker.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				dueDate = datePicker.getValue();
				System.out.println("Selected date: " + dueDate);
			}
		});
		
		ckBt.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("Please confirm your action!");
				alert.setContentText("Are you sure to checkout the book?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					insertCheckRecord();
					curStage.close();
				}
				
				
			}
		});
	}
	
	public void insertCheckRecord(){
		CheckoutRecordDao cd = new CheckoutRecordDao();
		CheckoutRecord cr = cd.getCheckoutRecordFromPid(person.getPid());
		
		System.out.println("loads rc " + cr);
		if(cr == null){
			cr = new CheckoutRecord();
			cr.setmId(person.getPid());
		}
		
		List<CheckoutEntry> ces = cr.getEnties();
		CheckoutEntry ce = new CheckoutEntry();
		ce.seteId(ces.size() + 1);
		ce.setBookId(publication.getpId());
		ce.setCheckoutDate(LocalDate.now());
		ce.setDueDate(dueDate);
		
		ces.add(ce);
		
		cd.saveCheckRecord(cr);
		
	}
	
	public void setPerson(Person p){
		this.person = p;
		
		userIdLB.setText(person.getUserName());
		userIdLB.setText(String.valueOf(person.getPid()));
	}
	
	public void setPublication(Publication b){
		this.publication = b;
		
		bookLB.setText(publication.getTitle());
		authorLB.setText(publication.getAuthors().toString());
		isbnLB.setText(publication.getISBN());
	}
}
