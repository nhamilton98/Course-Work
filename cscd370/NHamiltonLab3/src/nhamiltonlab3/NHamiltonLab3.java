package nhamiltonlab3;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NHamiltonLab3 extends Application 
{    
    private Label mStatus;
    private SevenSegment mTop;
    private SevenSegment mLeft;
    private SevenSegment mLeftMiddle;
    private SevenSegment mRightMiddle;
    private SevenSegment mRight;
    
    @Override
    public void start(Stage primaryStage) 
    {    
        BorderPane root = new BorderPane();
        
        mTop = new SevenSegment(SevenSegment.MIN_VALUE);
        mLeft = new SevenSegment(1);
        mLeftMiddle = new SevenSegment(2);
        mRightMiddle = new SevenSegment(3);
        mRight = new SevenSegment(4);
        
        Button button = new Button("Increment");
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> onButtonClick(mouseEvent));
        
        HBox bottomContainer = new HBox();
        bottomContainer.setAlignment(Pos.CENTER);
        bottomContainer.getChildren().addAll(mLeft, mLeftMiddle, mRightMiddle, mRight);
        VBox allContainer = new VBox();
        allContainer.setAlignment(Pos.CENTER);
        allContainer.getChildren().addAll(mTop, button, bottomContainer);
        
        root.setCenter(allContainer);
        
        root.setTop(buildMenuBar());
        
        mStatus = new Label("Nathan Hamilton, CSCD 370") ;
        ToolBar toolBar = new ToolBar(mStatus) ;
        root.setBottom(toolBar) ;
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("NHamiltonLab3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setStatus(String status) { mStatus.setText(status); }
    
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION) ;
        alert.setTitle("About") ;
        alert.setHeaderText("Nathan E Hamilton, CSCD 370 Lab 3, Fall 2018") ;
        alert.showAndWait() ;
    }
    
    public MenuBar buildMenuBar()
    {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("_File");
        
        MenuItem quitMenuItem = new MenuItem("_Quit");  
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q,
        KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().add(quitMenuItem);
        
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        
        return menuBar;
    }
    
    private void onButtonClick(MouseEvent event)
    {
        addSegment(mTop);
        addSegment(mLeft);
        addSegment(mLeftMiddle);
        addSegment(mRightMiddle);
        addSegment(mRight);
    }
    
    private void addSegment(SevenSegment segment)
    {
        int value = segment.getValue() + 1;
        
        if (value > 10)
            value = 0;
        
        segment.setValue(value);
    }
    
    public static void main(String[] args) { launch(args); }  
}
