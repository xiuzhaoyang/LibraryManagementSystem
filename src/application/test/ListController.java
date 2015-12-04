package application.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Severity;

import application.Dao.PersonDao;
import application.models.*;
import application.util.Utils;
import application.util.Utils.ISceneControllerSetting;
import application.views.BaseController;
import application.views.CheckoutSceneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ListController {
	
	@FXML
    private ListView listView;
	
    private List<String> stringSet;
    ObservableList observableList = FXCollections.observableArrayList();
	
    public ListController(){
    	
    }
    
    @FXML
    void initialize() {
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'CustomList.fxml'.";
        stringSet = new ArrayList<>();
        setListView();
    }
    
	public void setListView()
    {
		System.out.print("setListView");
        stringSet.add("String 1");
        stringSet.add("String 2");
        stringSet.add("String 3");
        stringSet.add("String 4");
        stringSet.add("String 5");
        stringSet.add("String 6");
        stringSet.add("String 7");
        
        observableList.setAll(stringSet);
        listView.setItems(observableList);
        listView.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>()
        {
            @Override
            public ListCell<String> call(ListView<String> listView)
            {
                return new ListViewCell();
            }
        });
        
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            	
            	
            PersonDao pd = new PersonDao();
            List<Person> ps = pd.loadALlPersons();
            Person p = null;
            for(Person r : ps){
            	if(r.getPid() == 3){
            		p = r;
            		break;
            	}
            }
            	
            if(p != null){
            	Utils.printCheckoutRecordAccordingMember(p);
            }
            
//            	Utils.gotoNextScene(CheckoutSceneController.class, "CheckoutScene.fxml", new ISceneControllerSetting() {
//					
//					@Override
//					public BaseController prepareForController(FXMLLoader fxmlLoader) {
//						CheckoutSceneController controller = fxmlLoader.<CheckoutSceneController>getController();
//						
//						PersonDao dao = new PersonDao();
//						Person p = dao.loadObjs().get(0);
//						
//						controller.setPerson(p);
////						Book b = new Book(111, "Western Traveling", "3j3838d73j28w8n", null,AllowedBorrowDays.SEVEN_DAYS	, null);
//                        Publication publiction = new Publication(1, LocalDate.now(),"book1","00001",new ArrayList<Author>(),AllowedBorrowDays.SEVEN_DAYS,new Book());
//						controller.setPublication(publiction);
//						return controller;
//					}
//				}, null);
//            	
            	
            }
        });
    }
}
