package nhamiltonhomework2;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class Pendulum extends Region
{
    private static final double WIDTH = 20;
    private static final double HEIGHT = 12;
    private static final double ASPECT_RATIO = WIDTH / HEIGHT;
    
    private static final double GRAVITY = 9.80665;
    private static final double PENDULUM_LENGTH = 10;
    
    private static final int TIMER_MSEC = 100;
    
    private double mPendulumAngle;
    private double mAngularVelocity;
    
    private SimpleDoubleProperty mCurPos;
    private SimpleDoubleProperty mMaxPos;
    private SimpleDoubleProperty mMinPos;
    private SimpleDoubleProperty mProgressBinding;
    
    private AnimationTimer mTimer;
    private boolean mIsRunning;
    private long mPreviousTime;
    
    private Canvas mCanvas;
    
    public Pendulum() { this.initialize(45); }
    
    private void initialize(double angle)
    {
        this.setPrefSize(WIDTH * 30, HEIGHT * 30);
        
        mPendulumAngle = angle;
        mAngularVelocity = 0;
        mCanvas = new Canvas();
        this.getChildren().add(mCanvas);
        
        mCurPos = new SimpleDoubleProperty(0.0);
        mMaxPos = new SimpleDoubleProperty(0.0);
        mMinPos = new SimpleDoubleProperty(0.0);
        mProgressBinding = new SimpleDoubleProperty(0.0);
        
        mPreviousTime = 0;
        mIsRunning = true;
        mTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now) { onTimer(now); }
        };
        mTimer.start();
    }
    
    public void draw()
    {
        GraphicsContext context = mCanvas.getGraphicsContext2D();
        
        context.save();

        context.clearRect(0, 0, mCanvas.getWidth(), mCanvas.getHeight());
        context.scale(mCanvas.getWidth() / WIDTH, mCanvas.getHeight() / HEIGHT);
        
        context.save();
        context.setGlobalAlpha(0.6);
        context.drawImage(new Image("img/background.jpeg"), 0, 0, WIDTH, HEIGHT);
        context.restore();
        
        context.translate(WIDTH / 2, 0);
        context.setLineWidth(0.3);
        context.setStroke(Color.BLACK);
        
        context.rotate(mPendulumAngle);
        context.strokeLine(0, 0, 0, PENDULUM_LENGTH);
        context.translate(0, PENDULUM_LENGTH);
        context.drawImage(new Image("img/pSchimpfBob.jpg"), -1, -1, 2, 3);
        
        context.restore();
    }
    
    public boolean isRunning() { return mIsRunning; }
    
    public void setRunning(boolean running) { mIsRunning = running; }
    
    public void update()
    {
        double interval = (double) TIMER_MSEC / 1000;
        double angularAcceleration = -(GRAVITY / PENDULUM_LENGTH) * Math.sin(Math.toRadians(mPendulumAngle));
        mAngularVelocity += angularAcceleration * interval;
        mPendulumAngle = Math.toRadians(mPendulumAngle);
        mPendulumAngle += mAngularVelocity * interval;
        mPendulumAngle = Math.toDegrees(mPendulumAngle);
        
        mCurPos = new SimpleDoubleProperty(PENDULUM_LENGTH * Math.sin(Math.toRadians(mPendulumAngle)));
        if (mCurPos.doubleValue() > mMaxPos.doubleValue())
            mMaxPos = new SimpleDoubleProperty(mCurPos.doubleValue());
        if (mCurPos.doubleValue() < mMinPos.doubleValue())
            mMinPos = new SimpleDoubleProperty(mCurPos.doubleValue());
        if (mCurPos.subtract(mMinPos).divide(mMaxPos.subtract(mMinPos)) != null)
            mProgressBinding = new SimpleDoubleProperty(mCurPos.subtract(mMinPos).divide(mMaxPos.subtract(mMinPos)).doubleValue());
        
        this.draw();
    }
    
    private void onTimer(long now)
    {
        now = System.currentTimeMillis();
        long elapsed = now - mPreviousTime;
        
        if (elapsed > TIMER_MSEC)
        {
            if (mIsRunning)
                this.update();
            
            mPreviousTime = now;
        }
    }
    
    public SimpleDoubleProperty progressBindingProperty() { return mProgressBinding; }
    
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
        
        this.layoutInArea(mCanvas, 0, 0, allocWidth, allocHeight, this.getBaselineOffset(), Insets.EMPTY, HPos.CENTER, VPos.TOP);
        
        this.draw();
    }
}
