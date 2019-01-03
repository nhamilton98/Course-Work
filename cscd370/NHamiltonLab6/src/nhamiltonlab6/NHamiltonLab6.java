package nhamiltonlab6;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NHamiltonLab6 extends Application 
{    
    private Label mStatus;
    private Text mText;
    
    @Override
    public void start(Stage primaryStage) 
    {
        mText = new Text("File > Options to change this text.");
        
        BorderPane root = new BorderPane();
        root.setCenter(mText);
        
        root.setTop(buildMenuBar());
        
        mStatus = new Label("Nathan Hamilton, CSCD 370") ;
        ToolBar toolBar = new ToolBar(mStatus) ;
        root.setBottom(toolBar) ;
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("NHamiltonLab6");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setStatus(String status) { mStatus.setText(status); }
    
    public MenuBar buildMenuBar()
    {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("_File");
        MenuItem optionsMenuItem = new MenuItem("_Options");
        optionsMenuItem.setOnAction(actionEvent -> onOptions());
        MenuItem quitMenuItem = new MenuItem("_Quit");  
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().addAll(optionsMenuItem, quitMenuItem);
        
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        
        return menuBar;
    }
    
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Nathan E Hamilton, CSCD 370 Lab 6, Fall 2018");
        alert.showAndWait();
    }
    
    private void onOptions()
    {
        try
        {
            OptionsLayoutController dialog = new OptionsLayoutController();
            Optional<ButtonType> result = dialog.showAndWait();
            
            if (result.isPresent() && result.get().getButtonData() == ButtonData.OK_DONE)
            {
                Font font;
                if (OptionsData.bold && OptionsData.italics)
                    font = Font.font("Monospaced", FontWeight.BOLD, FontPosture.ITALIC, OptionsData.size);
                else if (OptionsData.bold)
                    font = Font.font("Monospaced", FontWeight.BOLD, OptionsData.size);
                else if (OptionsData.italics)
                    font = Font.font("Monospaced", FontPosture.ITALIC, OptionsData.size);
                else
                    font = Font.font("Monospaced", OptionsData.size);
                
                mText.setFont(font);
                if (OptionsData.showString)
                    mText.setText(OptionsData.userString);
                else
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
                    Date date = new Date();
                    String showDate = dateFormat.format(date);
                    mText.setText(showDate);
                }
            }
        } catch (IOException e)
        {
            Logger.getLogger(NHamiltonLab6.class.getName()).log(Level.SEVERE, "Exception while opening the options dialog: " + e.getMessage(), e.getMessage());
        }
    }
    
    public static void main(String[] args) { launch(args); }  
}
