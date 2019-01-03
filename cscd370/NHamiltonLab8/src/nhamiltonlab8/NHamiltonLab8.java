package nhamiltonlab8;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NHamiltonLab8 extends Application 
{    
    private Label mStatus;
    
    private final Image X = new Image("img/x.png");
    private final Image O = new Image("img/o.png");
    
    @Override
    public void start(Stage primaryStage) 
    {
        ImageView ivX = new ImageView(X);
        ivX.setUserData("X");
        ivX.setOnDragDetected(mouseEvent -> dragXO(mouseEvent));
        ImageView ivO = new ImageView(O);
        ivO.setUserData("O");
        ivO.setOnDragDetected(mouseEvent -> dragXO(mouseEvent));
        HBox xoHolder = new HBox();
        xoHolder.getChildren().addAll(ivX, ivO);
        xoHolder.setAlignment(Pos.CENTER);
        
        GridPane board = this.buildBoard();
        
        VBox playArea = new VBox();
        playArea.getChildren().addAll(xoHolder, board);
        
        BorderPane root = new BorderPane();
        root.setCenter(playArea);
        
        root.setTop(buildMenuBar());
        
        mStatus = new Label("Nathan Hamilton, CSCD 370") ;
        ToolBar toolBar = new ToolBar(mStatus) ;
        root.setBottom(toolBar) ;
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("NHamiltonLab8");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setStatus(String status) { mStatus.setText(status); }
    
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Nathan E Hamilton, CSCD 370 Lab 8, Fall 2018");
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
    
    private GridPane buildBoard()
    {
        GridPane board = new GridPane();
        for (int i = 0; i < 5; i++)
        {
            if (i % 2 == 0)
            {
                for (int j = 0; j < 5; j++)
                {
                    if (j % 2 == 0)
                    {
                        ImageView blank = new ImageView("img/blank.png");
                        blank.setUserData("BLANK");
                        blank.setOnDragOver(dragEvent -> dragOverBlank(dragEvent));
                        blank.setOnDragDropped(dragEvent -> dragDropBlank(dragEvent));
                        board.add(blank, j, i);
                    }
                    else
                        board.add(new ImageView("img/vert.png"), j, i);
                }
            }
            else
            {
                for (int j = 0; j < 5; j += 2)
                    board.add(new ImageView("img/horiz.png"), j, i);
            }
        }
        return board;
    }
    
    private void dragXO(MouseEvent event)
    {
        ImageView piece = (ImageView) event.getSource();
        Dragboard db = piece.startDragAndDrop(TransferMode.COPY);
        db.setDragView(piece.getImage(), piece.getImage().getWidth() / 2, piece.getImage().getHeight() / 2);
        ClipboardContent clipboard = new ClipboardContent();
        clipboard.put(DataFormat.PLAIN_TEXT, piece.getUserData());
        db.setContent(clipboard);
    }
    
    private void dragOverBlank(DragEvent event)
    {
        ImageView blank = (ImageView) event.getTarget();
        if (blank.getUserData().equals("BLANK"))
        {
            Dragboard db = event.getDragboard();
            if (db.hasString() && db.getString().equals("X") || db.getString().equals("O"))
                event.acceptTransferModes(TransferMode.COPY);
        }
    }
    
    private void dragDropBlank(DragEvent event)
    {
        ImageView blank = (ImageView) event.getTarget();
        Dragboard db = event.getDragboard();
        if (db.getString().equals("X"))
        {
            blank.setImage(X);
            blank.setUserData("X");
        }
        else if (db.getString().equals("O"))
        {
            blank.setImage(O);
            blank.setUserData("O");
        }
        event.setDropCompleted(true);
    }
    
    public static void main(String[] args) { launch(args); }  
}
