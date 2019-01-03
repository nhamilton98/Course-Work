package nhamiltonlab7;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.Preferences;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NHamiltonLab7 extends Application 
{    
    private Label mStatus;
    private Text mText;
    private MenuItem mOptionsMenuItem;
    
    @Override
    public void start(Stage primaryStage) 
    {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        if (prefs.getBoolean("FirstRun", true))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("First Time User");
            alert.setHeaderText("Hello first time user.");
            alert.setContentText("File > Options to get started.");
            alert.showAndWait();
            prefs.putBoolean("FirstRun", false);
        }
        prefs.addPreferenceChangeListener(prefChangeEvent -> onPrefChanged(prefChangeEvent));
        
        OptionsData.readPreferences(getClass());
        
        mText = new Text();
        BorderPane root = new BorderPane();
        root.setCenter(mText);
        
        root.setTop(buildMenuBar());
        
        mStatus = new Label("Nathan Hamilton, CSCD 370") ;
        ToolBar toolBar = new ToolBar(mStatus) ;
        root.setBottom(toolBar) ;
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("NHamiltonLab7");
        primaryStage.setScene(scene);
        this.setMainText();
        primaryStage.show();
    }
    
    private void setStatus(String status) { mStatus.setText(status); }
    
    public MenuBar buildMenuBar()
    {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("_File");
        mOptionsMenuItem = new MenuItem("_Options");
        mOptionsMenuItem.setOnAction(actionEvent -> onOptions());
        MenuItem quitMenuItem = new MenuItem("_Quit");  
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().addAll(mOptionsMenuItem, quitMenuItem);
        
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
        alert.setHeaderText("Nathan E Hamilton, CSCD 370 Lab 7, Fall 2018");
        alert.showAndWait();
    }
    
    private Font createFont()
    {
        if (OptionsData.bold && OptionsData.italics)
            return Font.font("Monospaced", FontWeight.BOLD, FontPosture.ITALIC, OptionsData.size);
        else if (OptionsData.bold)
            return Font.font("Monospaced", FontWeight.BOLD, OptionsData.size);
        else if (OptionsData.italics)
            return Font.font("Monospaced", FontPosture.ITALIC, OptionsData.size);
        else
            return Font.font("Monospaced", OptionsData.size);
    }
    
    private void setMainText()
    {
        Font font = this.createFont();
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
    
    private void onOptions()
    {
        try
        {
            OptionsLayoutController dialog = new OptionsLayoutController();
            dialog.initModality(Modality.WINDOW_MODAL);
            mOptionsMenuItem.setDisable(true);
            dialog.setOnCloseRequest(dialogEvent -> onClose(dialogEvent));
            dialog.show();
        } catch (IOException e)
        {
            Logger.getLogger(NHamiltonLab7.class.getName()).log(Level.SEVERE, "Exception while opening the options dialog: " + e.getMessage(), e.getMessage());
        }
    }
    
    private void onClose(DialogEvent event) { mOptionsMenuItem.setDisable(false); }
    
    private void onPrefChanged(PreferenceChangeEvent prefChangeEvent) { this.setMainText(); }
    
    public static void main(String[] args) { launch(args); }  
}
