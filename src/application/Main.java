package application;


import application.models.*;
import application.test.DaoTest;
import application.views.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
	private Stage primaryStage;
	private RadioButton libranian;
	private RadioButton admin;
	private RadioButton superRole;

	private BorderPane rootLayout;
	private ObservableList<Person> members = FXCollections.observableArrayList();
	private ObservableList<CheckoutRecord> ckrecords = FXCollections.observableArrayList();

	private ObservableList<CheckoutRecord> ckRecords = FXCollections.observableArrayList();
	//	private ObservableList<CheckoutEntry> entries = FXCollections.observableArrayList();
	private ObservableList<Publication> publications = FXCollections.observableArrayList();

	/**
     * Constructor
     */
	public Main(){
		
		DaoTest.testWriteDB();
		
//		List<PersonRole> list1 = new ArrayList<PersonRole>();
//		list1.add(new LibraryMember());
//
//		List<PersonRole> list2 = new ArrayList<PersonRole>();
//		list2.add(new LibraryMember());
//		list2.add(new Administrator());
//
//		List<PersonRole> list3 = new ArrayList<PersonRole>();
//		list3.add(new LibraryMember());
//
//		List<PersonRole> list4 = new ArrayList<PersonRole>();
//		list4.add(new LibraryMember());
//
//		List<PersonRole> list5 = new ArrayList<PersonRole>();
//		list5.add(new LibraryMember());
//		list5.add(new Librarian());
//
//		List<PersonRole> list6 = new ArrayList<PersonRole>();
//		list6.add(new LibraryMember());
//
//		List<PersonRole> list7 = new ArrayList<PersonRole>();
//		list7.add(new Administrator());
//		list7.add(new Librarian());
//
//		List<PersonRole> list8 = new ArrayList<PersonRole>();
//		list8.add(new LibraryMember());
//
//		List<PersonRole> list9 = new ArrayList<PersonRole>();
//		list9.add(new LibraryMember());
//
//		//Hard code some data
//		//Person(int pid, String firstName, String lastName, String street, String city, String state, String zip, String phoneNum, LocalDate dob)
//		members.add(new Person(1, "Tim", "TM", "1st ST", "New York", "NY", "11231", "123456789", LocalDate.of(1980, 5, 20), list1));
//		members.add(new Person(2, "Jessica", "JS", "12st ST", "Fairfield", "IA", "55231", "213456789", LocalDate.of(1983, 9, 12), list2));
//		members.add(new Person(3, "Monica", "MN", "11st ST", "Washington", "DC", "20231", "123547689", LocalDate.of(1988, 11, 5), list3));
//		members.add(new Person(4, "Jack", "JK", "5st ST", "New York", "NY", "13511", "993456789", LocalDate.of(1981, 06, 10), list4));
//		members.add(new Person(5, "Tony", "TN", "3st ST", "Baltimore", "MD", "20931", "128765439", LocalDate.of(1990, 03, 26), list5));
//		members.add(new Person(6, "Serena", "SE", "8st ST", "North Potomac", "MD", "20878", "871234569", LocalDate.of(1999, 07, 19), list6));
//		members.add(new Person(7, "Fei", "Zhang", "11st ST", "Seattle", "MA", "22631", "431257789", LocalDate.of(1985, 03, 15), list7));
//		members.add(new Person(8, "Yu", "Guan", "20st ST", "Boston", "MA", "71231", "123410989", LocalDate.of(1992, 8, 21), list8));
//		members.add(new Person(9, "Bei", "Liu", "8st ST", "Rockville", "MD", "27231", "162216789", LocalDate.of(1989, 7, 11), list9));

		Address addr1 = new Address("1st", "New York", "NY", "12345");
		List<Author> authorList1 = new ArrayList<Author>();
		Author author = new Author(1, "Fei", "Zhang", addr1, "PhD", "novelist", "CHN");
		authorList1.add(author);
		Publication bk1 = new Publication(1, LocalDate.of(2011, 11, 2),"C#", "123456789", authorList1, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());
		Publication bk2 = new Publication(2, LocalDate.of(2013, 5, 2), "Java", "789123456", authorList1, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());
		Publication bk3 = new Publication(3, LocalDate.of(2009, 7, 2), "C++", "456123789", authorList1, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());

		ObservableList<CheckoutEntry> enties1 = FXCollections.observableArrayList();
		CheckoutEntry ckEntry1 = new CheckoutEntry(1, bk1, LocalDate.of(2015, 5, 10), LocalDate.of(2015, 6, 4), null, false, 0.0f);
		CheckoutEntry ckEntry2 = new CheckoutEntry(2, bk2, LocalDate.of(2015, 5, 1), LocalDate.of(2015, 5, 22), null, false, 0.0f);
		CheckoutEntry ckEntry3 = new CheckoutEntry(3, bk3, LocalDate.of(2015, 5, 1), LocalDate.of(2015, 5, 22), null, false, 0.0f);
		enties1.add(ckEntry1);
		enties1.add(ckEntry2);
		enties1.add(ckEntry3);

		CheckoutRecord ckRecord1 = new CheckoutRecord(1, 2, enties1);	//member ID 2's record
		ckRecords.add(ckRecord1);

		Address addr2 = new Address("1st", "New York", "NY", "12345");
		List<Author> authorList2 = new ArrayList<Author>();
		Author author2 = new Author(2, "Yu", "Guan", addr2, "PhD", "novelist", "CHN");
		authorList2.add(author2);

		Publication bk4 = new Publication(4, LocalDate.of(2012, 10, 2), "C#2", "923456789", authorList2, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());
		Publication bk5 = new Publication(5, LocalDate.of(2013, 8, 5), "Java2", "789123456", authorList2, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());
		Publication bk6 = new Publication(6, LocalDate.of(2014, 9, 12), "C++2", "456123789", authorList2, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());
		bk4.addCopy(LocalDate.of(2012, 3, 12), bk4);
		bk4.addCopy(LocalDate.of(2013, 4, 1), bk4);
		bk4.addCopy(LocalDate.of(2015, 8, 8), bk4);

		publications.add(bk1);
		publications.add(bk2);
		publications.add(bk3);
		publications.add(bk4);
		publications.add(bk5);
		publications.add(bk6);

		ObservableList<CheckoutEntry> enties2 = FXCollections.observableArrayList();
		CheckoutEntry ckEntry4 = new CheckoutEntry(4, bk4, LocalDate.of(2015, 5, 10), LocalDate.of(2015, 6, 4), null, false, 0.0f);
		CheckoutEntry ckEntry5 = new CheckoutEntry(5, bk5, LocalDate.of(2015, 5, 1), LocalDate.of(2015, 5, 22), null, false, 0.0f);
		CheckoutEntry ckEntry6 = new CheckoutEntry(6, bk6, LocalDate.of(2015, 5, 1), LocalDate.of(2015, 5, 22), null, false, 0.0f);
		enties2.add(ckEntry4);
		enties2.add(ckEntry5);
		enties2.add(ckEntry6);
		CheckoutRecord ckRecord2 = new CheckoutRecord(2, 3, enties2);	//member ID 3's record
		ckRecords.add(ckRecord2);
	}

	/**
     * Returns the data as an observable list of Persons.
     * @return
     */
//	public ObservableList<Person> getPersonInfo(){
//		return members;
//	}

//	public ObservableList<CheckoutRecord> getCheckRecordData(){
//		return ckRecords;
//	}
//
//	public ObservableList<Publication> getPublications(){
//		return publications;
//	}

//	public ObservableList<CheckoutEntry> getEntries(){
//		return this.entries;
//	}

//	public ObservableList<CheckoutEntry> getCheckoutEntries(){
//		return ckRecords
//	}

	@Override

	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}

		this.primaryStage = primaryStage;
//		showListView();
		showLogin();
//		initRootLayout();
//		showMemberOverview();
	}

	private void showListView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("test/tbtest.fxml"));
			AnchorPane loginPage;

			loginPage = (AnchorPane) loader.load();

			Stage loginStage = new Stage();

			Scene scene = new Scene(loginPage);
			loginStage.setScene(scene);

			// Give the controller access to the main app.
			// LoginController controller = loader.getController();
			// controller.setStage(loginStage);

			// wait until user close it
			loginStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showLogin()
	{
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/Login.fxml"));
			AnchorPane loginPage = (AnchorPane) loader.load();

//			final ToggleGroup group = new ToggleGroup();
//			libranian = new RadioButton("Libranian");
//			admin = new RadioButton("Admin");
//			superRole = new RadioButton("Super");
//
//			libranian.setToggleGroup(group);
//			admin.setToggleGroup(group);
//			superRole.setToggleGroup(group);
//
//			Button loginBtn = new Button();
//			Button cancelBtn = new Button();

			Stage loginStage = new Stage();

			Scene scene = new Scene(loginPage);
			loginStage.setScene(scene);

			// Give the controller access to the main app.
			LoginController controller = loader.getController();
			controller.setStage(loginStage);

			//wait until user close it
			loginStage.showAndWait();
			Person person = controller.getPersonInfo();
			if(person != null){
				showRootLayout(person);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void showRootLayout(Person member){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/RootLayout.fxml"));
			rootLayout = (BorderPane)loader.load();
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			controller.setMember(member);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private void showMemberOverview(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/MemberOverview.fxml"));
			AnchorPane personOverview = (AnchorPane)loader.load();

			
			

			Stage loginStage = new Stage();

			Scene scene = new Scene(personOverview);
			loginStage.setScene(scene);

			// Give the controller access to the main app.
			// LoginController controller = loader.getController();
			// controller.setStage(loginStage);

			// wait until user close it
			
			

			MemberOverviewController controller = loader.getController();
			controller.setMain(this);
			loginStage.showAndWait();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public boolean showPersonEditDialog(Person member){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/MemberEditDialog.fxml"));
			AnchorPane editPage = (AnchorPane)loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit member");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(editPage);
			dialogStage.setScene(scene);

			MemberEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMember(member);

			dialogStage.showAndWait();
			return controller.checkIsOk();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}

	public void showCheckoutEntry(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/PublicationCheckout.fxml"));
			AnchorPane checkEntryPage = (AnchorPane) loader.load();

			PublicationCheckoutController controller = loader.getController();

			Stage checkOutEntryStage = new Stage();
			checkOutEntryStage.setTitle("Checkout Entry");
			checkOutEntryStage.initModality(Modality.WINDOW_MODAL);
			checkOutEntryStage.initOwner(primaryStage);
			Scene scene = new Scene(checkEntryPage);
			checkOutEntryStage.setScene(scene);
			//TODO:
//			controller.setCheckoutStage(checkOutEntryStage);
//
//			controller.setMain(this, 2);

			checkOutEntryStage.showAndWait();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void showPublicationCopy(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/PublicationAvailabilityCheck.fxml"));
			AnchorPane publicationCopyPage = (AnchorPane) loader.load();

			PublicationAvailabilityCheckController controller = loader.getController();

			Stage publicationCopyStage = new Stage();
			publicationCopyStage.setTitle("Publication copy");
			publicationCopyStage.initModality(Modality.WINDOW_MODAL);
			publicationCopyStage.initOwner(primaryStage);
			Scene scene = new Scene(publicationCopyPage);
			publicationCopyStage.setScene(scene);
			controller.setPublicationCopyStage(publicationCopyStage);

			controller.setMain(this, "923456789");

			publicationCopyStage.showAndWait();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage(){
		return this.primaryStage;
	}


	public static void main(String[] args) {

		launch(args);

	}
}
