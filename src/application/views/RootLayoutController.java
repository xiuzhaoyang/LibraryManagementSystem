package application.views;

import application.Main;
import application.models.Administrator;
import application.models.Librarian;
import application.models.Person;
import application.models.PersonRole;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by su on 12/1/15.
 */
public class RootLayoutController {

    private String MEMBER_OVERVIEW = "MemberOverview.fxml";

    @FXML
    private MenuBar menubar;

    @FXML
    private BorderPane content;

    private Main mainApp;
    private Person member;


    @FXML
    private void initialize() {
        initAdminLayout();
    }

    public void setMember(Person member) {
        this.member = member;
        PersonRole role = member.getPersonRoles().get(0);
        if(role instanceof Administrator){
            initAdminLayout();
        } else if(role instanceof Librarian){
            initLibrarianLayout();
        }
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void initAdminLayout() {
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
        showContentView(MEMBER_OVERVIEW);
    }

    public void initLibrarianLayout() {

    }


    private void showContentView(String fileName){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/"+fileName));
            AnchorPane contentView = (AnchorPane) loader.load();
            content.setCenter(contentView);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
