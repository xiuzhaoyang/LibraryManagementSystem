package application.views;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import application.Dao.CheckoutRecordDao;
import application.models.*;
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
	private int memberId;

	@FXML
	private PublicationCopy publicationCopy;
	
	
	@FXML
    void initialize() {
		
		setDatePicker();	
	}
	
	
	private void setDatePicker(){
		
		datePicker.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				dueDate = datePicker.getValue();
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

	public void setPublicationCopy(PublicationCopy publicationCopy) {
		this.publicationCopy = publicationCopy;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public void insertCheckRecord(){
		CheckoutRecordDao cd = new CheckoutRecordDao();
		CheckoutRecord cr = cd.getCheckoutRecordFromPid(person.getPid());
		
		if(cr == null){
			cr = new CheckoutRecord();
			cr.setmId(person.getPid());
		}
		
		List<CheckoutEntry> ces = cr.getEnties();
		CheckoutEntry ce = new CheckoutEntry();
		if(ces.size() == 0){
			ce.seteId(1);
		}else{
			int maxId = 0;
			
			for(CheckoutEntry e: ces){
				if(e.geteId() > maxId){
					maxId = e.geteId();
				}
			}
			
			ce.seteId(maxId + 1);
		}
		
		ce.setpId(publication.getpId());

		ce.setCheckoutDate(LocalDate.now());
		ce.setDueDate(dueDate);
		
		ces.add(ce);
		
		cd.saveCheckRecord(cr);
		
	}
	
	public void setPerson(Person p){
		this.person = p;
		
		userNameLB.setText(person.getFirstName() + " " + person.getLastName());
		userIdLB.setText(String.valueOf(person.getPid()));
	}
	
	public void setPublication(Publication b){
		this.publication = b;
		
		bookLB.setText(publication.getTitle());
		authorLB.setText(publication.getAuthorString());
		isbnLB.setText(publication.getISBN());
	}
}
