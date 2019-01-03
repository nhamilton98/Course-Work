package nhamiltonhomework2;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NHamiltonHomework2 extends Application 
{
    private Pendulum mPendulum;
    private ProgressBar mProgressBar;
    
    @Override
    public void start(Stage primaryStage) 
    {
        mPendulum = new Pendulum();
        BorderPane root = new BorderPane();
        root.setCenter(mPendulum);
        
        root.setTop(buildMenuBar());
        
        mProgressBar = new ProgressBar();
        mProgressBar.progressProperty().bind(mPendulum.progressBindingProperty());
        ToolBar toolBar = new ToolBar(mProgressBar);
        root.setBottom(toolBar);
        
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(keyEvent -> onKey(keyEvent));
        
        primaryStage.setTitle("NHamiltonHomework2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Nathan E Hamilton, CSCD 370 Homework2, Fall 2018");
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
        
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        
        return menuBar;
    }
    
    private void onKey(KeyEvent event)
    {
        switch (event.getCode())
        {
            case UP:
                mPendulum.setRunning(true);
                break;
            case DOWN:
                mPendulum.setRunning(false);
                break;
        }
    }
    
    public static void main(String[] args) { launch(args); }  
}
