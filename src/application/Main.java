package application;


import java.io.IOException;

import application.models.CheckoutRecord;
import application.models.Person;
import application.models.Publication;
import application.test.DaoTest;
import application.views.LoginController;
import application.views.MemberEditDialogController;
import application.views.MemberOverviewController;
import application.views.PublicationAvailabilityCheckController;
import application.views.PublicationCheckoutController;
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

//			controller.setDialogStage(dialogStage);
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
