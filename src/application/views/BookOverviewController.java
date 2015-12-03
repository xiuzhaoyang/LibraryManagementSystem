package application.views;

import java.util.List;

import application.models.Publication;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by su on 12/3/15.
 */
public class BookOverviewController extends BaseController {
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
