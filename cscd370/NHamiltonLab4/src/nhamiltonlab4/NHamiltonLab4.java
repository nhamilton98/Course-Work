package nhamiltonlab4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class NHamiltonLab4 extends Application 
{
    private BorderPane mRoot;
    
    private Label mStatus;
    private Canvas mTempCanvas;
    private Canvas mPermCanvas;
    private Point2D mStart;
    private Point2D mEnd;
    
    private Color mColor;
    private int mWidth;
    
    private ToggleGroup mColorToggle;
    private ToggleGroup mWidthToggle;
    
    private ToolBarLocation mToolBarLocation;
    
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
        
        mRoot = new BorderPane();
        mRoot.setCenter(pane);
        
        mPermCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> onMousePressed(mouseEvent));
        mPermCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseEvent -> onMouseDragged(mouseEvent));
        mPermCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> onMouseReleased(mouseEvent));
        
        mRoot.setTop(new VBox(this.buildMenuBar()));
        mRoot.setLeft(this.buildToolBar());
        mToolBarLocation = ToolBarLocation.LEFT;
        
        mStatus = new Label("Nathan Hamilton, CSCD 370");
        ToolBar toolBar = new ToolBar(mStatus);
        mRoot.setBottom(toolBar);
        
        Scene scene = new Scene(mRoot);
        
        primaryStage.setTitle("NHamiltonLab4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setStatus(String status) { mStatus.setText(status); }
    
    private void onAbout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION) ;
        alert.setTitle("About") ;
        alert.setHeaderText("Nathan E Hamilton, CSCD 370 Lab 4, Fall 2018") ;
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
        widthMenu.setOnShowing(event -> widthMenuSelected());
        mWidthToggle = new ToggleGroup();
        RadioMenuItem widthOneMenuItem = new RadioMenuItem("_1 Pixel");
        widthOneMenuItem.setOnAction(actionEvent -> onWidth(1));
        widthOneMenuItem.setToggleGroup(mWidthToggle);
        widthOneMenuItem.setSelected(true);
        mWidth = 1;
        RadioMenuItem widthFourMenuItem = new RadioMenuItem("_4 Pixels");
        widthFourMenuItem.setOnAction(actionEvent -> onWidth(4));
        widthFourMenuItem.setToggleGroup(mWidthToggle);
        RadioMenuItem widthEightMenuItem = new RadioMenuItem("_8 Pixels");
        widthEightMenuItem.setOnAction(actionEvent -> onWidth(8));
        widthEightMenuItem.setToggleGroup(mWidthToggle);
        widthMenu.getItems().addAll(widthOneMenuItem, widthFourMenuItem, widthEightMenuItem);
        
        Menu colorMenu = new Menu("_Color");
        colorMenu.setOnShowing(event -> colorMenuSelected());
        mColorToggle = new ToggleGroup();
        RadioMenuItem colorBlackMenuItem = new RadioMenuItem("_Black");
        colorBlackMenuItem.setOnAction(actionEvent -> onColor(Color.BLACK));
        colorBlackMenuItem.setToggleGroup(mColorToggle);
        colorBlackMenuItem.setSelected(true);
        mColor = Color.BLACK;
        RadioMenuItem colorBlueMenuItem = new RadioMenuItem("_Blue");
        colorBlueMenuItem.setOnAction(actionEvent -> onColor(Color.BLUE));
        colorBlueMenuItem.setToggleGroup(mColorToggle);
        RadioMenuItem colorGreenMenuItem = new RadioMenuItem("_Green");
        colorGreenMenuItem.setOnAction(actionEvent -> onColor(Color.GREEN));
        colorGreenMenuItem.setToggleGroup(mColorToggle);
        RadioMenuItem colorRedMenuItem = new RadioMenuItem("_Red");
        colorRedMenuItem.setOnAction(actionEvent -> onColor(Color.RED));
        colorRedMenuItem.setToggleGroup(mColorToggle);
        colorMenu.getItems().addAll(colorBlackMenuItem, colorBlueMenuItem, colorGreenMenuItem, colorRedMenuItem);
        
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        
        menuBar.getMenus().addAll(fileMenu, widthMenu, colorMenu, helpMenu);
        return menuBar ;
    }
    
    public ToolBar buildToolBar()
    {
        ToolBar toolBar = new ToolBar();
        toolBar.setOrientation(Orientation.VERTICAL);
        
        Button colorButton = new Button("", new ImageView(new Image("img/Color.png")));
        colorButton.setOnAction(actionEvent -> onColor(null));
        colorButton.setTooltip(new Tooltip("Cycle Color"));
        Button moveButton = new Button("", new ImageView(new Image("img/Move.png")));
        moveButton.setOnAction(actionEvent -> onMove());
        moveButton.setTooltip(new Tooltip("Move ToolBar"));
        Button newButton = new Button("", new ImageView(new Image("img/New.png")));
        newButton.setOnAction(actionEvent -> onNew());
        newButton.setTooltip(new Tooltip("New"));
        Button openButton = new Button("", new ImageView(new Image("img/Open.png")));
        openButton.setOnAction(actionEvent -> onOpen());
        openButton.setTooltip(new Tooltip("Open"));
        Button saveButton = new Button("", new ImageView(new Image("img/Save.png")));
        saveButton.setOnAction(actionEvent -> onSave(true));
        saveButton.setTooltip(new Tooltip("Save"));
        Button widthButton = new Button("", new ImageView(new Image("img/Width.png")));
        widthButton.setOnAction(actionEvent -> onWidth(0));
        widthButton.setTooltip(new Tooltip("Cycle Width"));
        
        Separator line1 = new Separator();
        line1.setOrientation(Orientation.HORIZONTAL);
        Separator line2 = new Separator();
        line2.setOrientation(Orientation.HORIZONTAL);
        Separator line3 = new Separator();
        line3.setOrientation(Orientation.HORIZONTAL);
        
        toolBar.getItems().addAll(newButton, openButton, saveButton, line1, widthButton, line2, colorButton, line3, moveButton);
        return toolBar;
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
        context.setStroke(mColor);
        context.setLineWidth(mWidth);
        initializeCanvas(mTempCanvas, Color.WHITE);
        
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
        mPermCanvas.getGraphicsContext2D().clearRect(0, 0, mPermCanvas.getWidth(), mPermCanvas.getHeight());
        initializeCanvas(mPermCanvas, Color.TRANSPARENT);
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
        if (width == 0)
            switch (mWidth)
            {
                case 1:
                    mWidth = 4;
                    break;
                case 4:
                    mWidth = 8;
                    break;
                case 8:
                    mWidth = 1;
                    break;
            }
        else
            mWidth = width;
    }
    
    private void widthMenuSelected()
    {
        ObservableList<Toggle> toggles = mWidthToggle.getToggles();
        
        Toggle selected = null;
        switch (mWidth)
        {
            case 1:
                selected = toggles.get(0);
                break;
            case 4:
                selected = toggles.get(1);
                break;
            case 8:
                selected = toggles.get(2);
                break;
        }
        
        mWidthToggle.selectToggle(selected);
    }
    
    private void onColor(Color color)
    {
        if (color == null)
        {
            if (mColor == Color.BLACK)
                mColor = Color.BLUE;
            else if (mColor == Color.BLUE)
                mColor = Color.GREEN;
            else if (mColor == Color.GREEN)
                mColor = Color.RED;
            else if (mColor == Color.RED)
                mColor = Color.BLACK;
        }
        else
            mColor = color;
    }
    
    private void colorMenuSelected()
    {
        ObservableList<Toggle> toggles = mColorToggle.getToggles();
        
        Toggle selected = null;
        if (mColor == Color.BLACK)
            selected = toggles.get(0);
        else if (mColor == Color.BLUE)
            selected = toggles.get(1);
        else if (mColor == Color.GREEN)
            selected = toggles.get(2);
        else if (mColor == Color.RED)
            selected = toggles.get(3);
        
        mColorToggle.selectToggle(selected);
    }
    
    private void onMove()
    {
        ToolBar toolBar = this.buildToolBar();
        switch (mToolBarLocation)
        {
            case LEFT:
                mRoot.setLeft(null);
                toolBar.setOrientation(Orientation.HORIZONTAL);
                ((VBox) mRoot.getTop()).getChildren().add(toolBar);
                mToolBarLocation = ToolBarLocation.TOP;
                break;
            case TOP:
                MenuBar menuBar = (MenuBar) ((VBox) mRoot.getTop()).getChildren().get(0);
                VBox top = new VBox(menuBar);
                mRoot.setTop(top);
                mRoot.setRight(toolBar);
                mToolBarLocation = ToolBarLocation.RIGHT;
                break;
            case RIGHT:
                mRoot.setRight(null);
                mRoot.setLeft(toolBar);
                mToolBarLocation = ToolBarLocation.LEFT;
                break;
        }
    }
    
    public static void main(String[] args) { launch(args); }  
}
