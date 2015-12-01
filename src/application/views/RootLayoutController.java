package application.views;

import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import application.models.Book;

import java.awt.*;

/**
 * Created by su on 12/1/15.
 */
public class RootLayoutController {

    @FXML
    private MenuBar menubar;

    private Boolean isAdmin;
    private Main mainApp;

    @FXML
    private void initialize() {
        initAdminLayout();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void initAdminLayout(){

        ObservableList<Menu> menus = menubar.getMenus();
        menus.clear();

        Menu menuMember = new Menu("member");
        MenuItem addMemberItem = new MenuItem("add member");
        addMemberItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.print("add member");
            }
        });

        Menu menuBook = new Menu("book");
        MenuItem addBookItem = new MenuItem("add book");
        addBookItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.print("add book");
            }
        });
        MenuItem addExitBook = new MenuItem("add a exiting book");
        addExitBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            System.out.print("add a exiting book");
        }
        });
        menuBook.getItems().add(addBookItem);
        menuBook.getItems().add(addExitBook);

        menuMember.getItems().add(addMemberItem);

        menus.add(menuMember);
        menus.add(menuBook);

    }

    public void initLibrarianLayout(){

    }

}
