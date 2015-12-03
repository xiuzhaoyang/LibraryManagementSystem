package application;


import application.models.*;
import application.test.DaoTest;
import application.views.LoginController;
import application.views.MemberEditDialogController;
import application.views.MemberOverviewController;
import application.views.RootLayoutController;
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

	/**
     * Constructor
     */
	public Main(){
		
		DaoTest.testWriteDB();
		
		List<PersonRole> list1 = new ArrayList<PersonRole>();
		list1.add(new LibraryMember());

		List<PersonRole> list2 = new ArrayList<PersonRole>();
		list2.add(new LibraryMember());
		list2.add(new Administrator());

		List<PersonRole> list3 = new ArrayList<PersonRole>();
		list3.add(new LibraryMember());

		List<PersonRole> list4 = new ArrayList<PersonRole>();
		list4.add(new LibraryMember());

		List<PersonRole> list5 = new ArrayList<PersonRole>();
		list5.add(new LibraryMember());
		list5.add(new Librarian());

		List<PersonRole> list6 = new ArrayList<PersonRole>();
		list6.add(new LibraryMember());

		List<PersonRole> list7 = new ArrayList<PersonRole>();
		list7.add(new Administrator());
		list7.add(new Librarian());

		List<PersonRole> list8 = new ArrayList<PersonRole>();
		list8.add(new LibraryMember());

		List<PersonRole> list9 = new ArrayList<PersonRole>();
		list9.add(new LibraryMember());

		//Hard code some data
		//Person(int pid, String firstName, String lastName, String street, String city, String state, String zip, String phoneNum, LocalDate dob)
		members.add(new Person(1, "Tim", "TM", "1st ST", "New York", "NY", "11231", "123456789", LocalDate.of(1980, 5, 20), list1));
		members.add(new Person(2, "Jessica", "JS", "12st ST", "Fairfield", "IA", "55231", "213456789", LocalDate.of(1983, 9, 12), list2));
		members.add(new Person(3, "Monica", "MN", "11st ST", "Washington", "DC", "20231", "123547689", LocalDate.of(1988, 11, 5), list3));
		members.add(new Person(4, "Jack", "JK", "5st ST", "New York", "NY", "13511", "993456789", LocalDate.of(1981, 06, 10), list4));
		members.add(new Person(5, "Tony", "TN", "3st ST", "Baltimore", "MD", "20931", "128765439", LocalDate.of(1990, 03, 26), list5));
		members.add(new Person(6, "Serena", "SE", "8st ST", "North Potomac", "MD", "20878", "871234569", LocalDate.of(1999, 07, 19), list6));
		members.add(new Person(7, "Fei", "Zhang", "11st ST", "Seattle", "MA", "22631", "431257789", LocalDate.of(1985, 03, 15), list7));
		members.add(new Person(8, "Yu", "Guan", "20st ST", "Boston", "MA", "71231", "123410989", LocalDate.of(1992, 8, 21), list8));
		members.add(new Person(9, "Bei", "Liu", "8st ST", "Rockville", "MD", "27231", "162216789", LocalDate.of(1989, 7, 11), list9));

//		Book bk1 = new Book();
//		Author

	}

	/**
     * Returns the data as an observable list of Persons.
     * @return
     */
	public ObservableList<Person> getPersonInfo(){
		return members;
	}

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

			rootLayout.setCenter(personOverview);

			MemberOverviewController controller = loader.getController();
			controller.setMain(this);
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

	public Stage getPrimaryStage(){
		return this.primaryStage;
	}


	public static void main(String[] args) {

		launch(args);

	}
}
