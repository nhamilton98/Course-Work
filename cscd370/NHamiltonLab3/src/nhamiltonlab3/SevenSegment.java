package nhamiltonlab3;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class SevenSegment extends Region
{
    private Canvas mCanvas;
    private int mCurValue;
    private boolean[] mState;
    
    private static final double WIDTH = 20;
    private static final double HEIGHT = 34;
    private static final double ASPECT_RATIO = WIDTH / HEIGHT;
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 10;
    private static final Color ON = Color.RED;
    private static final Color OFF = new Color(1, 0, 0, 0.1);
    private static final double[] SEGMENT_VERTICES_X = {0, 1, 13, 14, 13, 1};
    private static final double[] SEGMENT_VERTICES_Y = {0, 1, 1, 0, -1, -1};
    private static final boolean[][] SEGMENTS = {{true, true, false, true, true, true, true},
                                                {false, false, false, true, false, false, true},
                                                {true, false, true, true, true, true, false},
                                                {true, false, true, true, false, true, true},
                                                {false, true, true, true, false, false, true},
                                                {true, true, true, false, false, true, true},
                                                {true, true, true, false, true, true, true},
                                                {true, false, false, true, false, false, true},
                                                {true, true, true, true, true, true, true},
                                                {true, true, true, true, false, true, true},
                                                {false, false, false, false, false, false, false}};
    
    public SevenSegment()
    {
        initialize(MAX_VALUE);
    }
    
    public SevenSegment(int value)
    {
        if (value >= MIN_VALUE || value <= MAX_VALUE)
            initialize(value);
    }
    
    private void initialize(int value)
    {
        this.setPrefSize(WIDTH * 10, HEIGHT * 10);
        
        mCanvas = new Canvas();
        this.getChildren().add(mCanvas);
        
        mCurValue = value;
        mState = SEGMENTS[mCurValue];
    }
    
    public void setValue(int value) 
    {
        if (value >= 0 && value <= 10)
        {
            mCurValue = value;
            mState = SEGMENTS[value];
        }
        this.draw();
    }
    
    public int getValue() { return mCurValue; }
    
    public void draw()
    {
        int curSegment = 0;
        GraphicsContext context = mCanvas.getGraphicsContext2D();
        context.save();
        
        context.clearRect(0, 0, mCanvas.getWidth(), mCanvas.getHeight());
        context.scale(mCanvas.getWidth() / WIDTH, mCanvas.getHeight() / HEIGHT);
        
        this.setFillColor(context, curSegment);
        context.translate(3, 3);
        context.fillPolygon(SEGMENT_VERTICES_X, SEGMENT_VERTICES_Y, 6);
        curSegment++;
        
        context.save();
        this.setFillColor(context, curSegment);
        context.rotate(90);
        context.fillPolygon(SEGMENT_VERTICES_X, SEGMENT_VERTICES_Y, 6);
        context.restore();
        curSegment++;
        
        context.save();
        this.setFillColor(context, curSegment);
        context.translate(0, 14);
        context.fillPolygon(SEGMENT_VERTICES_X, SEGMENT_VERTICES_Y, 6);
        context.restore();
        curSegment++;
        
        context.save();
        this.setFillColor(context, curSegment);
        context.translate(14, 0);
        context.rotate(90);
        context.fillPolygon(SEGMENT_VERTICES_X, SEGMENT_VERTICES_Y, 6);
        context.restore();
        curSegment++;
        
        context.save();
        this.setFillColor(context, curSegment);
        context.translate(0, 14);
        context.rotate(90);
        context.fillPolygon(SEGMENT_VERTICES_X, SEGMENT_VERTICES_Y, 6);
        context.restore();
        curSegment++;
        
        context.save();
        this.setFillColor(context, curSegment);
        context.translate(0, 28);
        context.fillPolygon(SEGMENT_VERTICES_X, SEGMENT_VERTICES_Y, 6);
        context.restore();
        curSegment++;
        
        context.save();
        this.setFillColor(context, curSegment);
        context.translate(14, 14);
        context.rotate(90);
        context.fillPolygon(SEGMENT_VERTICES_X, SEGMENT_VERTICES_Y, 6);
        context.restore();
        
        context.restore();
    }
    
    private void setFillColor(GraphicsContext context, int segment)
    {
        if (mState[segment])
            context.setFill(ON);
        else
            context.setFill(OFF);
    }
    
    @Override
    protected void layoutChildren()
    {
        double allocWidth = this.getWidth();
        double allocHeight = this.getHeight();
        
        double calcHeight = allocWidth / ASPECT_RATIO;
        double calcWidth = allocHeight * ASPECT_RATIO;
        
        double finalWidth, finalHeight;
        if (calcHeight > allocHeight)
        {
            finalWidth = calcWidth;
            finalHeight = allocHeight;
        }
        else
        {
            finalWidth = allocWidth;
            finalHeight = calcHeight;
        }
        
        mCanvas.setWidth(finalWidth);
        mCanvas.setHeight(finalHeight);
        
        this.layoutInArea(mCanvas, 0, 0, allocWidth, allocHeight, this.getBaselineOffset(), Insets.EMPTY, HPos.CENTER, VPos.CENTER);
        
        this.draw();
    }
}
