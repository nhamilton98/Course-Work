package nhamiltonfinal;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Projectile
{
    private final Image PROJECTILE = new Image("img/bullet.png");
    
    private static Projectile instance = null;
    
    public static Projectile getInstance()
    {
        if (instance == null)
            instance = new Projectile();
        return instance;
    }
    
    private Launcher mLauncher;
    private Point2D mTopRightCorner;
    
    private Projectile()
    {
        mLauncher = null;
        mTopRightCorner = null;
    }
    
    public void initialize(Launcher launcher, Canvas canvas)
    {
        mLauncher = launcher;
        mTopRightCorner = new Point2D(mLauncher.getLocation().getX(), mLauncher.getLocation().getY() - 24);
        mLauncher.setProjectile(true);
        this.draw(canvas);
    }
    
    private void draw(Canvas canvas) { canvas.getGraphicsContext2D().drawImage(PROJECTILE, mTopRightCorner.getX(), mTopRightCorner.getY(), 24, 24); }
    
    public void update(Canvas canvas, int[][] cells, Centipede centipede, SimpleIntegerProperty score, SimpleIntegerProperty lives)
    {
        GraphicsContext context = canvas.getGraphicsContext2D();
        if (mTopRightCorner.getY() - 24 >= 0)
        {
            if (cells[(int)((mTopRightCorner.getY()) / 24)][(int)(mTopRightCorner.getX() / 24)] > 0 || cells[(int)((mTopRightCorner.getY() - 24) / 24)][(int)(mTopRightCorner.getX() / 24)] > 0)
            {
                switch (cells[(int)((mTopRightCorner.getY() - 24) / 24)][(int)(mTopRightCorner.getX() / 24)])
                {
                    case 4:
                        context.clearRect(mTopRightCorner.getX(), mTopRightCorner.getY() - 24, 24, 24);
                        context.drawImage(new Image("img/Rock3.png"), mTopRightCorner.getX(), mTopRightCorner.getY() - 24, 24, 24);
                        break;
                    case 3:
                        context.clearRect(mTopRightCorner.getX(), mTopRightCorner.getY() - 24, 24, 24);
                        context.drawImage(new Image("img/Rock2.png"), mTopRightCorner.getX(), mTopRightCorner.getY() - 24, 24, 24);
                        break;
                    case 2:
                        context.clearRect(mTopRightCorner.getX(), mTopRightCorner.getY() - 24, 24, 24);
                        context.drawImage(new Image("img/Rock1.png"), mTopRightCorner.getX(), mTopRightCorner.getY() - 24, 24, 24);
                        break;
                    case 1:
                        context.clearRect(mTopRightCorner.getX(), mTopRightCorner.getY() - 24, 24, 24);
                        break;
                }
                AudioClip clip = new AudioClip(getClass().getResource("rockhit.wav").toString());
                clip.play();
                cells[(int)((mTopRightCorner.getY() - 24) / 24)][(int)(mTopRightCorner.getX() / 24)]--;
                this.remove(canvas);
                score.set(score.intValue() + 5);
            }
            else
            {
                for (Centipede.Segment seg: centipede.getSegments()) SEGMENTS:
                {
                    if (mTopRightCorner.getY() == seg.getLocation().getY() && mTopRightCorner.getX() == seg.getLocation().getX() || mTopRightCorner.getY() - 24 == seg.getLocation().getY() && mTopRightCorner.getX() == seg.getLocation().getX())
                    {
                        this.remove(canvas);
                        centipede.update(canvas, cells, seg);
                        score.set(score.intValue() + 10);
                        break SEGMENTS;
                    }
                }
                if (instance != null)
                {
                    context.clearRect(mTopRightCorner.getX(), mTopRightCorner.getY(), 24, 24);
                    mTopRightCorner = new Point2D(mTopRightCorner.getX(), mTopRightCorner.getY() - 24);
                    this.draw(canvas);
                }
            }
        }
        else
            this.remove(canvas);
    }
    
    private void remove(Canvas canvas)
    {
        canvas.getGraphicsContext2D().clearRect(mTopRightCorner.getX(), mTopRightCorner.getY(), 24, 24);
        instance = null;
        mLauncher.setProjectile(false);
    }
}
