package application.util;

import java.io.IOException;

import application.Dao.CheckoutRecordDao;
import application.Dao.PublicationDao;
import application.models.CheckoutEntry;
import application.models.CheckoutRecord;
import application.models.Person;
import application.models.Publication;
import application.views.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Utils {

	public interface ISceneControllerSetting{
		public BaseController prepareForController(FXMLLoader fxmlLoader);
	}
	
	public static void gotoNextScene(Class z, String resourceName, ISceneControllerSetting i, Stage preStage){
		
		FXMLLoader fxmlLoader = new FXMLLoader(z.getResource(resourceName));     

    	Parent root = null;
		try {
			root = (Parent)fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// set Controller paras
		
		BaseController  con = i.prepareForController(fxmlLoader);
		
		Scene scene = new Scene(root); 
		Stage s = new Stage();
    	s.setScene(scene);
    	con.setCurrentStage(s);
    	
    	
		if(preStage != null){
			preStage.close();
		}
		
		s.show();
	}
	
	public static void printCheckoutRecordAccordingMember(Person p){
		
		System.out.println("");
		System.out.println("Member Name: " + p.getFirstName() + " " + p.getLastName());
		System.out.println("Member ID  : " + p.getPid());
//		System.out.println("");
		System.out.println("---------------------------------------------------------");
//		System.out.println("");
		
		System.out.println("EntryId    Book    CheckoutDate    DueDate     ReturnDate");
		
		CheckoutRecordDao crd = new CheckoutRecordDao();
		CheckoutRecord cr = crd.getCheckoutRecordFromPid(p.getPid());
		
		if(cr == null || cr.getEnties() == null || cr.getEnties().isEmpty()){
			System.out.println("           NO CHECKOUT RECORD");
			return;
		}
		
		PublicationDao pb = new PublicationDao();
		for(CheckoutEntry ce : cr.getEnties()){
			
			Publication pBook = pb.getPublicationByPid(ce.getpId());
			
			System.out.println(ce.geteId() + "          " +pBook.getTitle() + "     " + ce.getCheckoutDate() + "    " + ce.getDueDate() + "     " + (ce.getReturnDate() == null ? "NOT RETURN " :   ce.getReturnDate()) );
		}
		
	}
}
