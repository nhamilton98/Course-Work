package nhamiltonlab1;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NHamiltonLab1 extends Application implements ChangeListener<String>
{    
    private Label status;
    private ImageView imgView;
    
    @Override
    public void start(Stage primaryStage) 
    {
        this.imgView = new ImageView(new Image(this.getClass().getResourceAsStream("/img/logo.png")));
        
        BorderPane root = new BorderPane();
        root.setCenter(imgView);
        
        root.setTop(buildMenuBar());
        
        this.status = new Label("Nathan Hamilton, CSCD 370") ;
        ToolBar toolBar = new ToolBar(this.status) ;
        root.setBottom(toolBar) ;
        
        ArrayList<String> labels = new ArrayList<>();
        labels.add("First Album");
        labels.add("Cindy");
        labels.add("Fred");
        labels.add("Kate");
        labels.add("Keith");
        labels.add("Matt");
        labels.add("Ricky");
        
        ListView<String> list = new ListView<>();
        list.setItems(FXCollections.observableArrayList(labels));
        root.setLeft(list);
        list.getSelectionModel().selectedItemProperty().addListener(this);
        
        double listWidth = this.stringWidth("First Album");
        list.setPrefWidth(listWidth * 1.3);
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("NHamiltonLab1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setStatus(String status) { this.status.setText(status); }
    
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION) ;
        alert.setTitle("About") ;
        alert.setHeaderText("Nathan E Hamilton, CSCD 370 Lab 1, Fall 2018") ;
        alert.showAndWait() ;
    }
    
    private MenuBar buildMenuBar()
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
        
        return menuBar ;
    }
    
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
    {
        String imgPath;
        if (newValue.toLowerCase().equals("first album"))
            imgPath = "/img/logo.png";
        else
            imgPath = "/img/" + newValue.toLowerCase() + ".png";
        this.imgView.setImage(new Image(this.getClass().getResourceAsStream(imgPath)));
        
        switch (newValue.toLowerCase()) {
            case "first album":
                this.setStatus("First Album, 1979");
                break;
            case "cindy":
                this.setStatus("Cindy Wilson (Percussion since 1976)");
                break;
            case "fred":
                this.setStatus("Fred Schneider (Vocals and Cowbell since 1976)");
                break;
            case "kate":
                this.setStatus("Kate Pierson (Organ since 1976)");
                break;
            case "keith":
                this.setStatus("Keith Strickland (Drums and Guitar since 1976)");
                break;
            case "matt":
                this.setStatus("Matt Flynn (Touring and Drums prior to 2004)");
                break;
            case "ricky":
                this.setStatus("Ricky Wilson, Deceased (Bass 1976-1985)");
                break;
            default:
                this.setStatus("Image Unavailable");
                break;
        }
    }
    
    private double stringWidth(String string)
    {
        final Text text = new Text(string);
        return text.getLayoutBounds().getWidth();
    }
    
    public static void main(String[] args) { launch(args); }
}