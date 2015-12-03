package application.views;

import application.models.Publication;
import com.sun.xml.internal.rngom.parse.host.Base;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

/**
 * Created by su on 12/3/15.
 */
public class BookOverviewController extends Base {
    @FXML
    private TextField ISBNSearchField;

    @FXML
    private TextField nameSearchField;

    @FXML
    private TableView tableView;

    private List<Publication> publicationList;

    @FXML
    private void initialize(){


    }

    private void handleSearchByISBN(){

    }

    private void handleSearchByName(){

    }

    private void handleAddPublic(){

    }




}
