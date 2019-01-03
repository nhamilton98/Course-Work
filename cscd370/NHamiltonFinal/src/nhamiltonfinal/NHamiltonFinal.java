package nhamiltonfinal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.Preferences;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NHamiltonFinal extends Application 
{    
    private Label mStatus;
    private Game mGame;
    
    private MenuItem mPause;
    private MenuItem mGo;
    private MenuItem mSettings;
    
    private int mScore;
    private int mLives;
    
    @Override
    public void start(Stage primaryStage) 
    {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        prefs.addPreferenceChangeListener(prefChangedEvent -> onSettingsChanged(prefChangedEvent));
        
        CentipedeSettings.readPreferences(getClass());
        
        mGame = new Game(CentipedeSettings.numRocks, CentipedeSettings.numCentipedeSegs);
        BorderPane root = new BorderPane();
        root.setCenter(mGame);
        mGame.getLives().addListener((observable, oldValue, newValue) -> this.setStatus(newValue.intValue(), mScore));
        mGame.getScore().addListener((observable, oldValue, newValue) -> this.setStatus(mLives, newValue.intValue()));
        mGame.getGameOver().addListener((observable, oldValue, newValue) -> this.gameOver());
        mGame.isRunning().addListener((observable, oldValue, newValue) -> this.onPauseGo(newValue));
        
        root.setTop(buildMenuBar());
        
        mStatus = new Label() ;
        this.setStatus(3, 0);
        ToolBar toolBar = new ToolBar(mStatus) ;
        root.setBottom(toolBar) ;
        
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(keyEvent -> mGame.onKeyPressed(keyEvent));
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("NHamiltonFinal");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    
    private void setStatus(int lives, int score) 
    {
        if (lives < 0)
            mStatus.setText("Lives: 0   Score: " + score);
        else
            mStatus.setText("Lives: " + lives + "   Score: " + score);
        mScore = score;
        mLives = lives;
    }
    
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Nathan E Hamilton, CSCD 370 Final, Fall 2018");
        mGame.onPauseGo(false);
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
        
        Menu gameMenu = new Menu("_Game");
        MenuItem newMenuItem = new MenuItem("_New");
        newMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        newMenuItem.setOnAction(actionEvent -> onNew());
        SeparatorMenuItem sep1 = new SeparatorMenuItem();
        mGo = new MenuItem("_Go");
        mGo.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));
        mGo.setOnAction(actionEvent -> this.onPauseGo(true));
        mPause = new MenuItem("_Pause");
        mPause.setDisable(true);
        mPause.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));
        mPause.setOnAction(actionEvent -> this.onPauseGo(false));
        SeparatorMenuItem sep2 = new SeparatorMenuItem();
        MenuItem highScoreMenuItem = new MenuItem("_High Score");
        highScoreMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
        highScoreMenuItem.setOnAction(actionEvent -> onHighScore());
        MenuItem resetHighScoreMenuItem = new MenuItem("_Reset High Score");
        resetHighScoreMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
        resetHighScoreMenuItem.setOnAction(actionEvent -> onResetHighScore());
        SeparatorMenuItem sep3 = new SeparatorMenuItem();
        mSettings = new MenuItem("_Settings");
        mSettings.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        mSettings.setOnAction(actionEvent -> onSettings());
        gameMenu.getItems().addAll(newMenuItem, sep1, mGo, mPause, sep2, highScoreMenuItem, resetHighScoreMenuItem, sep3, mSettings);
        
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
        
        return menuBar;
    }
    
    private void onNew()
    {
        mGame.initialize(CentipedeSettings.numRocks, CentipedeSettings.numCentipedeSegs, 3, 0, false, true);
        mGame.getLives().addListener((observable, oldValue, newValue) -> this.setStatus(newValue.intValue(), mScore));
        mGame.getScore().addListener((observable, oldValue, newValue) -> this.setStatus(mLives, newValue.intValue()));
        mGame.getGameOver().addListener((observable, oldValue, newValue) -> this.gameOver());
        mGame.isRunning().addListener((observable, oldValue, newValue) -> this.onPauseGo(newValue));
        this.onPauseGo(false);
        this.setStatus(3, 0);
    }
    
    private void onPauseGo(boolean isRunning)
    {
        if (isRunning)
        {
            mGo.setDisable(true);
            mPause.setDisable(false);
        }
        else
        {
            mGo.setDisable(false);
            mPause.setDisable(true);
        }
        mGame.onPauseGo(isRunning);
    }
    
    private void onHighScore()
    {
        CentipedeSettings.readPreferences(getClass());
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("HIGH SCORE");
        alert.setHeaderText("High Score: " + CentipedeSettings.highScore);
        this.onPauseGo(false);
        alert.showAndWait();
    }
    
    private void onResetHighScore()
    {
        CentipedeSettings.highScore = 0;
        CentipedeSettings.storePreferences(getClass());
    }
    
    private void onSettings()
    {
        try
        {
            SettingsLayoutController dialog = new SettingsLayoutController();
            dialog.show();
        } catch (IOException e)
        {
            Logger.getLogger(NHamiltonFinal.class.getName()).log(Level.SEVERE, "Exception while opening the options dialog: " + e.getMessage(), e.getMessage());
        }
    }
    
    private void onSettingsChanged(PreferenceChangeEvent event)
    {
        Platform.runLater(() -> { onNew(); });
    }
    
    private void gameOver()
    {
        mPause.setDisable(true);
        mPause.setDisable(false);
        
        CentipedeSettings.readPreferences(getClass());
        Alert alert = new Alert(AlertType.INFORMATION);
        if (mScore > CentipedeSettings.highScore)
        {
            alert.setTitle("NEW HIGH SCORE");
            alert.setHeaderText("New High Score: " + mScore);
        }
        else
        {
            alert.setTitle("GAME OVER");
            alert.setHeaderText("Score: " + mScore);
        }
        this.onPauseGo(false);
        alert.show();
        
        if (mScore > CentipedeSettings.highScore)
        {
            CentipedeSettings.highScore = mScore;
            CentipedeSettings.storePreferences(getClass());
        }
    }
    
    public static void main(String[] args) { launch(args); }  
}
