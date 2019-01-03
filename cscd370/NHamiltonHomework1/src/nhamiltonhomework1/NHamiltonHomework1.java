package nhamiltonhomework1;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.undo.UndoManager;

public class NHamiltonHomework1 extends Application
{
    private static final double DIMX = 400, DIMY = 400;
    
    private Canvas mCanvas;
    private double mLastX, mLastY;
    private Color  mColor = Color.BLACK;
    
    private MenuItem mUndo = new MenuItem();
    private MenuItem mRedo = new MenuItem();
    
    private final UndoManager UNDO_MANAGER = new UndoManager();
    
    @Override
    public void start(Stage primaryStage)
    {
        mCanvas = new Canvas(DIMX, DIMY);
        initCanvas();
        
        mCanvas.setOnMousePressed(mouseEvent -> onMousePressed(mouseEvent));
        mCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseEvent -> onMouseDragged(mouseEvent));

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: lightgray;");
        root.setCenter(mCanvas);

        MenuBar menuBar = buildMenus();
        root.setTop(menuBar);

        primaryStage.setTitle("Scribble 2");
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar buildMenus() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("_File");
        MenuItem item = new MenuItem("_New");
        item.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        item.setOnAction(actionEvent -> initCanvas());
        fileMenu.getItems().add(item);
        item = new MenuItem("_Quit");
        item.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        item.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().add(item);
        
        Menu editMenu = new Menu("_Edit");
        mUndo = new MenuItem("_Undo");
        mUndo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        mUndo.setOnAction(actionEvent -> onUndo());
        mUndo.setDisable(true);
        mRedo = new MenuItem("_Redo");
        mRedo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
        mRedo.setOnAction(actionEvent -> onRedo());
        mRedo.setDisable(true);
        editMenu.getItems().addAll(mUndo, mRedo);
        
        String[] colorItems = new String[]{"_Red", "_Green", "_Blue", "Blac_k"};
        Menu colorMenu = new Menu("_Color");
        for (String colorItem : colorItems)
        {
            item = new MenuItem(colorItem);
            item.setOnAction(actionEvent -> onColor(colorItem));
            colorMenu.getItems().add(item);
        }

        Menu helpMenu = new Menu("_Help");
        item = new MenuItem("_About");
        item.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(item);

        menuBar.getMenus().addAll(fileMenu, editMenu, colorMenu, helpMenu);

        return menuBar;
    }

    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Scribble 2");
        alert.setHeaderText("Nathan E Hamilton, CSCD 370 Homework 1, Fall 2018");
        Optional<ButtonType> result = alert.showAndWait();
    }

    private void onColor(String colorItem)
    {
        switch (colorItem)
        {
            case "_Red":
                mColor = Color.RED;
                break;
            case "_Green":
                mColor = Color.GREEN;
                break;
            case "_Blue":
                mColor = Color.BLUE;
                break;
            case "Blac_k":
                mColor = Color.BLACK;
                break;
        }
    }

    private void initCanvas()
    {
        GraphicsContext g = mCanvas.getGraphicsContext2D();
        
        UndoableNew undoNew = new UndoableNew(mCanvas);
        UNDO_MANAGER.addEdit(undoNew);
        
        this.refreshUndoRedo();

        g.setFill(Color.WHITE);
        g.fillRect(0, 0, mCanvas.getWidth(), mCanvas.getHeight());
        g.setLineWidth(3);
    }

    private void onMousePressed(MouseEvent e)
    {
        UndoableScribble undoScribble = new UndoableScribble(mCanvas);
        UNDO_MANAGER.addEdit(undoScribble);
        this.refreshUndoRedo();
        mLastX = e.getX();
        mLastY = e.getY();
    }
    
    private void onMouseDragged(MouseEvent e)
    {
        GraphicsContext g = mCanvas.getGraphicsContext2D();
        g.setStroke(mColor);
        g.strokeLine(mLastX, mLastY, e.getX(), e.getY());
        mLastX = e.getX();
        mLastY = e.getY();
    }
    
    private void onUndo()
    {
        UNDO_MANAGER.undo();
        this.refreshUndoRedo();
    }
    
    private void onRedo()
    {
        UNDO_MANAGER.redo();
        this.refreshUndoRedo();
    }
    
    private void refreshUndoRedo()
    {
        if (UNDO_MANAGER.canUndo())
        {
            mUndo.setText(UNDO_MANAGER.getUndoPresentationName());
            mUndo.setDisable(false);
        }
        else
        {
            mUndo.setText("_Undo");
            mUndo.setDisable(true);
        }
        
        if (UNDO_MANAGER.canRedo())
        {
            mRedo.setText(UNDO_MANAGER.getRedoPresentationName());
            mRedo.setDisable(false);
        }
        else
        {
            mRedo.setText("_Redo");
            mRedo.setDisable(true);
        }
    }

    public static void main(String[] args) { launch(args); }
}
