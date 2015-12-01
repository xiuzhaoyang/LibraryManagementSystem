package application.views;

import application.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import application.models.Book;

/**
 * Created by su on 12/1/15.
 */
public class RootLayoutController {

    @FXML
    private MenuBar menubar;
    private Boolean isAdmin;
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void initAdminLayout(){
    }

    public void initLibrarianLayout(){
        ObservableList<Menu> menus = menubar.getMenus();

    }

}
