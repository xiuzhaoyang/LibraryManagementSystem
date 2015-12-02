package application.views;

import java.time.LocalDate;

import application.Main;
import application.models.CheckoutEntry;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BookCheckoutController {
	@FXML
	private TableView<CheckoutEntry> entries;

	@FXML
	private TableColumn<CheckoutEntry, Integer> entryIdColumn;

	@FXML
	private TableColumn<CheckoutEntry, Integer> bookIdColumn;

	@FXML
	private TableColumn<CheckoutEntry, String> titleColumn;

	@FXML
	private TableColumn<CheckoutEntry, String> isbnColumn;

	@FXML
	private TableColumn<CheckoutEntry, LocalDate> checkoutDateColumn;

	@FXML
	private TableColumn<CheckoutEntry, LocalDate> dueDateColumn;

	@FXML
	private TableColumn<CheckoutEntry, Boolean> isFineColumn;

	@FXML
	private TableColumn<CheckoutEntry, Float> finePaidColumn;

	private Main main;

	public BookCheckoutController(){}

	@FXML
	private void initialize(){
		
	}
}
