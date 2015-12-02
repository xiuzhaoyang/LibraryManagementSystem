package application.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import application.test.ListViewCell;

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
    }
}
