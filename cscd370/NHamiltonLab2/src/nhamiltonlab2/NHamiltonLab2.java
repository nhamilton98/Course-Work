package nhamiltonlab2;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class NHamiltonLab2 extends Application 
{    
    private Label mStatus;
    private Canvas mTempCanvas;
    private Canvas mPermCanvas;
    private Point2D mStart;
    private Point2D mEnd;
    
    @Override
    public void start(Stage primaryStage) 
    {
        mTempCanvas = new Canvas(400, 400);
        mPermCanvas = new Canvas(400, 400);
        initializeCanvas(mTempCanvas, Color.WHITE);
        initializeCanvas(mPermCanvas, Color.TRANSPARENT);
        
        StackPane sPane = new StackPane();
        sPane.getChildren().setAll(mTempCanvas, mPermCanvas);
        
        ScrollPane pane = new ScrollPane();
        pane.setContent(sPane);
        
        BorderPane root = new BorderPane();
        root.setCenter(pane);
        
        mPermCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> onMousePressed(mouseEvent));
        mPermCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseEvent -> onMouseDragged(mouseEvent));
        mPermCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> onMouseReleased(mouseEvent));
        
        root.setTop(buildMenuBar());
        
        mStatus = new Label("Nathan Hamilton, CSCD 370");
        ToolBar toolBar = new ToolBar(mStatus);
        root.setBottom(toolBar);
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("NHamiltonLab2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setStatus(String status) { mStatus.setText(status); }
    
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION) ;
        alert.setTitle("About") ;
        alert.setHeaderText("Nathan E Hamilton, CSCD 370 Lab 2, Fall 2018") ;
        alert.showAndWait() ;
    }
    
    public MenuBar buildMenuBar()
    {
        MenuBar menuBar = new MenuBar();
        
        Menu fileMenu = new Menu("_File");
        MenuItem newMenuItem = new MenuItem("_New");
        newMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        newMenuItem.setOnAction(actionEvent -> onNew());
        MenuItem openMenuItem = new MenuItem("_Open");
        openMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        openMenuItem.setOnAction(actionEvent -> onOpen());
        MenuItem saveMenuItem = new MenuItem("_Save");
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveMenuItem.setOnAction(actionEvent -> onSave(false));
        MenuItem saveAsMenuItem = new MenuItem("_Save As");
        saveAsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        saveAsMenuItem.setOnAction(actionEvent -> onSave(true));
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem exitMenuItem = new MenuItem("_Exit");  
        exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, separator, exitMenuItem);
        
        Menu widthMenu = new Menu("_Width");
        RadioMenuItem widthOneMenuItem = new RadioMenuItem("_1 Pixel");
        widthOneMenuItem.setOnAction(actionEvent -> onWidth(1));
        RadioMenuItem widthFourMenuItem = new RadioMenuItem("_4 Pixels");
        widthFourMenuItem.setOnAction(actionEvent -> onWidth(4));
        RadioMenuItem widthEightMenuItem = new RadioMenuItem("_8 Pixels");
        widthEightMenuItem.setOnAction(actionEvent -> onWidth(8));
        widthMenu.getItems().addAll(widthOneMenuItem, widthFourMenuItem, widthEightMenuItem);
        
        Menu colorMenu = new Menu("_Color");
        RadioMenuItem colorBlackMenuItem = new RadioMenuItem("_Black");
        colorBlackMenuItem.setOnAction(actionEvent -> onColor("Black"));
        RadioMenuItem colorBlueMenuItem = new RadioMenuItem("_Blue");
        colorBlueMenuItem.setOnAction(actionEvent -> onColor("Blue"));
        RadioMenuItem colorGreenMenuItem = new RadioMenuItem("_Green");
        colorGreenMenuItem.setOnAction(actionEvent -> onColor("Green"));
        RadioMenuItem colorRedMenuItem = new RadioMenuItem("_Red");
        colorRedMenuItem.setOnAction(actionEvent -> onColor("Red"));
        colorMenu.getItems().addAll(colorBlackMenuItem, colorBlueMenuItem, colorGreenMenuItem, colorRedMenuItem);
        
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        
        menuBar.getMenus().addAll(fileMenu, widthMenu, colorMenu, helpMenu);
        return menuBar ;
    }
    
    private void initializeCanvas(Canvas canvas, Paint paint)
    {
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(paint); 
        context.fillRect(0, 0, canvasWidth, canvasHeight);
    }
    
    private void onMousePressed(MouseEvent event)
    {
        mStart = new Point2D(event.getX(), event.getY());
        mEnd = mStart;
    }
    
    private void onMouseDragged(MouseEvent event)
    {
        if (mTempCanvas.contains(new Point2D(event.getX(), event.getY())))
        {
            initializeCanvas(mTempCanvas, Color.WHITE);
            mEnd = new Point2D(event.getX(), event.getY());
            drawRectangle(mTempCanvas);
        }
    }
    
    private void onMouseReleased(MouseEvent event)
    {
        mEnd = new Point2D(event.getX(), event.getY());
        
        GraphicsContext context = mPermCanvas.getGraphicsContext2D();
        context.setStroke(Color.RED);
        initializeCanvas(mTempCanvas, Color.TRANSPARENT);
        
        if (mTempCanvas.contains(mEnd))
            rectangleDirectionChooser(context);
    }
    
    private void drawRectangle(Canvas canvas)
    {
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setStroke(Color.BLACK);
        
        rectangleDirectionChooser(context);
    }
    
    private void rectangleDirectionChooser(GraphicsContext context)
    {
        if (mEnd.getX() > mStart.getX() && mEnd.getY() > mStart.getY())
            context.strokeRect(mStart.getX(), mStart.getY(), mEnd.getX() - mStart.getX(), mEnd.getY() - mStart.getY());
        else if (mEnd.getX() < mStart.getX() && mEnd.getY() > mStart.getY())
            context.strokeRect(mEnd.getX(), mStart.getY(), mStart.getX() - mEnd.getX(), mEnd.getY() - mStart.getY());
        else if (mEnd.getX() < mStart.getX() && mEnd.getY() < mStart.getY())
            context.strokeRect(mEnd.getX(), mEnd.getY(), mStart.getX() - mEnd.getX(), mStart.getY() - mEnd.getY());
        else if (mEnd.getX() > mStart.getX() && mEnd.getY() < mStart.getY())
            context.strokeRect(mStart.getX(), mEnd.getY(), mEnd.getX() - mStart.getX(), mStart.getY() - mEnd.getY());
    }
    
    private void onNew()
    {
        setStatus("onNew() called.");
        mPermCanvas.getGraphicsContext2D().clearRect(0, 0, mPermCanvas.getWidth(), mPermCanvas.getHeight());
        initializeCanvas(mPermCanvas, Color.WHITE);
    }
    
    private void onOpen()
    {
        setStatus("onOpen() called.");
    }
    
    private void onSave(boolean saveAs)
    {
        setStatus("onSave(" + saveAs + ") called.");
    }
    
    private void onWidth(int width)
    {
        setStatus("onWidth(" + width + ") called.");
    }
    
    private void onColor(String color)
    {
        setStatus("onColor(" + color + ") called.");
    }
    
    public static void main(String[] args) { launch(args); }  
}
