package application.test;

import application.views.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class TestController extends BaseController{

	@FXML
    private Label lb;
	
    private Stage ss;
	public TestController(){
		
	}
	
	public void setText(String value){
		lb.setText(value);
	}
	public void setStage(Stage s){
		ss = s;
	}
	public void click(){
		ss.close();
	}
	
}
