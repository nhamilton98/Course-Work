package nhamiltonfinal;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Launcher
{
    private static final Image LAUNCHER_IMAGE = new Image("img/launcher2.png");
    
    private Point2D mTopRightCorner;
    private boolean mHasProjectile;
    
    public Launcher(double posX, double posY)
    {
        mTopRightCorner = new Point2D(posX, posY);
        mHasProjectile = false;
    }
    
    public void draw(Canvas canvas)
    {
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.drawImage(LAUNCHER_IMAGE, mTopRightCorner.getX(), mTopRightCorner.getY(), 24, 24);
    }
    
    public Point2D getLocation() { return mTopRightCorner; }
    
    public boolean hasProjectile() { return mHasProjectile; }
    
    public void setProjectile(boolean exists) { mHasProjectile = exists; }
    
    public void update(KeyCode direction, Canvas canvas)
    {
        GraphicsContext context = canvas.getGraphicsContext2D();
        if (direction == KeyCode.LEFT)
        {
            if (mTopRightCorner.getX() - 24 >= 0)
            {
                context.clearRect(mTopRightCorner.getX(), mTopRightCorner.getY(), 24, 24);
                mTopRightCorner = new Point2D(mTopRightCorner.getX() - 24, mTopRightCorner.getY());
                this.draw(canvas);
            }
        }
        else if (direction == KeyCode.RIGHT)
        {
            if (mTopRightCorner.getX() + 24 < 24 * 24)
            {
                context.clearRect(mTopRightCorner.getX(), mTopRightCorner.getY(), 24, 24);
                mTopRightCorner = new Point2D(mTopRightCorner.getX() + 24, mTopRightCorner.getY());
                this.draw(canvas);
            }
        }
    }
}
