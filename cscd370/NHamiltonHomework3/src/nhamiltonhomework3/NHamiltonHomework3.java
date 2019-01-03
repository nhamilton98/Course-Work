package nhamiltonhomework3;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NHamiltonHomework3 extends Application 
{    
    private Label mStatus;
    private Scene mScene;
    
    private ToggleGroup mViewToggle;
    
    @Override
    public void start(Stage primaryStage) 
    {
        BorderPane root = new BorderPane();
        Panel top = new Panel("It's Wednesday, my dudes.");
        VBox.setVgrow(top, Priority.ALWAYS);
        top.mPasteSuccess.addListener((observable, oldValue, newValue) -> this.setStatus(newValue));
        Panel bottom = new Panel("Let's get this bread.");
        VBox.setVgrow(bottom, Priority.ALWAYS);
        bottom.mPasteSuccess.addListener((observable, oldValue, newValue) -> this.setStatus(newValue));
        VBox container = new VBox();
        container.getChildren().addAll(top, bottom);
        root.setCenter(container);
        
        root.setTop(buildMenuBar());
        
        mStatus = new Label("Nathan Hamilton, CSCD 370") ;
        ToolBar toolBar = new ToolBar(mStatus) ;
        root.setBottom(toolBar) ;
        
        mScene = new Scene(root);
        
        primaryStage.setTitle("NHamiltonHomework3");
        primaryStage.getIcons().add(new Image("img/icon.png"));
        primaryStage.setScene(mScene);
        primaryStage.show();
    }
    
    private void setStatus(String status) { mStatus.setText(status); }
    
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Nathan E Hamilton, CSCD 370 Homework 3, Fall 2018");
        alert.showAndWait();
    }
    
    public MenuBar buildMenuBar()
    {
        MenuBar menuBar = new MenuBar();
        
        Menu fileMenu = new Menu("_File");
        MenuItem quitMenuItem = new MenuItem("_Quit");  
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().add(quitMenuItem);
        
        mViewToggle = new ToggleGroup();
        Menu viewMenu = new Menu("_View");
        RadioMenuItem defaultMenuItem = new RadioMenuItem("_Default");
        defaultMenuItem.setToggleGroup(mViewToggle);
        defaultMenuItem.setSelected(true);
        defaultMenuItem.setOnAction(actionEvent -> changeTheme("modena"));
        RadioMenuItem darkMenuItem = new RadioMenuItem("_Dark");
        darkMenuItem.setToggleGroup(mViewToggle);
        darkMenuItem.setOnAction(actionEvent -> changeTheme("dark"));
        viewMenu.getItems().addAll(defaultMenuItem, darkMenuItem);
        
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
                
        menuBar.getMenus().addAll(fileMenu, viewMenu, helpMenu);
        return menuBar;
    }
    
    private void changeTheme(String style) 
    {
        if (style.equals("dark"))
            mScene.getStylesheets().add("style/dark.css");
        else if (style.equals("modena"))
            mScene.getStylesheets().clear();
    }
    
    public static void main(String[] args) { launch(args); }  
}
