package application.util;

import java.io.IOException;

import application.views.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Utils {

	public interface ISceneControllerSetting{
		public BaseController prepareForController(FXMLLoader fxmlLoader);
	}
	
	public static void gotoNextScene(Object o , String resourceName, ISceneControllerSetting i, Stage preStage){
		
		FXMLLoader fxmlLoader = new FXMLLoader(o.getClass().getResource(resourceName));     

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
}
