package application.views;

import java.time.LocalDate;

import application.models.Book;
import application.models.Person;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class CheckoutSceneController extends BaseController{
	
	Person person;
	
	Book book;
	
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
				LocalDate date = datePicker.getValue();
				System.out.println("Selected date: " + date);
			}
		});
		
		ckBt.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				
			}
		});
	}
	
	public void setPerson(Person p){
		this.person = p;
		
		userIdLB.setText(person.getUserName());
		userIdLB.setText(String.valueOf(person.getPid()));
	}
	
	public void setBook(Book b){
		this.book = b;
		
		bookLB.setText(book.getTitle());
		authorLB.setText(book.getAuthorsString());
		isbnLB.setText(book.getISBN());
	}
}
