package nhamiltonfinal;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

public class Game extends Region
{
    private int[][] mGameCells;
    private Canvas mGameArea = new Canvas(24 * 24, 24 * 24);
    private Canvas mBackground = new Canvas(24 * 24, 24 * 24);
    
    private final int TIMER_MSEC = 80;
    
    private AnimationTimer mTimer;
    private SimpleBooleanProperty mIsRunning;
    private long mPreviousTime;
    
    private int mRockCount;
    private int mCentipedeSize;
    
    private Centipede mCentipede;
    private Launcher mLauncher;
    private Projectile mProjectile;
    
    private SimpleIntegerProperty mLives;
    private SimpleIntegerProperty mScore;
    
    private SimpleBooleanProperty mGameOver = new SimpleBooleanProperty();
    
    public Game(int rockCount, int centSize)
    {
        this.initialize(rockCount, centSize, 3, 0, false, true);
        
        mPreviousTime = 0;
        mTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now) { onTimer(now); }
        };
        mTimer.start();
    }
    
    public void initialize(int rocks, int size, int lives, int score, boolean isRunning, boolean newGame)
    {
        this.getChildren().removeAll(mBackground, mGameArea);
        
        mGameCells = new int[24][24];
        mGameArea = new Canvas(24 * 24, 24 * 24);
        mBackground = new Canvas(24 * 24, 24 * 24);
        
        this.getChildren().addAll(mBackground, mGameArea);
        
        GraphicsContext context = mBackground.getGraphicsContext2D();
        context.clearRect(0, 0, mBackground.getWidth(), mBackground.getHeight());
        context.drawImage(new Image("img/starfield.png"), 0, 0, mBackground.getWidth(), mBackground.getHeight());
        
        context = mGameArea.getGraphicsContext2D();
        context.clearRect(0, 0, mGameArea.getWidth(), mGameArea.getHeight());
        context.setFill(Color.TRANSPARENT);
        context.fillRect(0, 0, mGameArea.getWidth(), mGameArea.getHeight());
        
        mRockCount = rocks;
        mCentipedeSize = size;
        
        mCentipede = new Centipede(mCentipedeSize);
        mCentipede.draw(mGameArea);
        
        Random random = new Random();
        int x, y, count = 0;
        while (count < mRockCount)
        {
            x = random.nextInt(24);
            y = random.nextInt(22) + 1;
            if (mGameCells[y][x] == 0)
            {
                mGameCells[y][x] = 4;
                count++;
                context.drawImage(new Image("img/Rock4.png"), x * 24, y * 24, 24, 24);
            }
        }
        
        mLauncher = new Launcher(24 * 24 / 2, 24 * 23);
        mLauncher.draw(mGameArea);
        
        if (newGame)
        {
            mIsRunning = new SimpleBooleanProperty(false);
            mLives = new SimpleIntegerProperty(lives);
            mScore = new SimpleIntegerProperty(score);
            mGameOver = new SimpleBooleanProperty(false);
        }
        else
        {
            mIsRunning.set(isRunning);
            mLives.set(lives);
            mScore.set(score);
            mGameOver.setValue(false);
        }
    }
    
    private void onTimer(long now)
    {
        now = System.currentTimeMillis();
        long elapsed = now - mPreviousTime;
        
        if (elapsed > TIMER_MSEC && mLauncher.hasProjectile())
        {
            if (mIsRunning.get())
                mProjectile.update(mGameArea, mGameCells, mCentipede, mScore, mLives);
        }
        
        if (elapsed > TIMER_MSEC * 1.5)
        {
            if (mIsRunning.get())
            {
                if (mCentipede.size() == 0)
                    this.initialize(mRockCount, mCentipedeSize, mLives.intValue(), mScore.intValue(), false, false);
                else if (mCentipede.mReachedBottomRow)
                {
                    mLives.set(mLives.intValue() - 1);
                    if (mLives.intValue() == 0)
                        mGameOver.set(true);
                    else
                        this.initialize(mRockCount, mCentipedeSize, mLives.intValue(), mScore.intValue(), false, false);
                }
                else
                {
                    mCentipede.update(mGameArea, mGameCells, null);
                    mPreviousTime = now;
                }
            }
        }
    }
    
    public void onKeyPressed(KeyEvent event)
    {
        if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT)
            mLauncher.update(event.getCode(), mGameArea);
        else if (event.getCode() == KeyCode.SPACE && !mLauncher.hasProjectile())
        {
            if (mIsRunning.get())
            {
                if (mGameCells[(int)((mLauncher.getLocation().getY() - 24) / 24)][(int)(mLauncher.getLocation().getX() / 24)] == 0)
                {
                    mProjectile = Projectile.getInstance();
                    mLauncher.setProjectile(true);
                    mProjectile.initialize(mLauncher, mGameArea);
                }
                else
                {
                    GraphicsContext context = mGameArea.getGraphicsContext2D();
                    switch (mGameCells[(int)((mLauncher.getLocation().getY() - 24) / 24)][(int)(mLauncher.getLocation().getX() / 24)])
                    {
                        case 4:
                            context.clearRect(mLauncher.getLocation().getX(), mLauncher.getLocation().getY() - 24, 24, 24);
                            context.drawImage(new Image("img/Rock3.png"), mLauncher.getLocation().getX(), mLauncher.getLocation().getY() - 24, 24, 24);
                            break;
                        case 3:
                            context.clearRect(mLauncher.getLocation().getX(), mLauncher.getLocation().getY() - 24, 24, 24);
                            context.drawImage(new Image("img/Rock2.png"), mLauncher.getLocation().getX(), mLauncher.getLocation().getY() - 24, 24, 24);
                            break;
                        case 2:
                            context.clearRect(mLauncher.getLocation().getX(), mLauncher.getLocation().getY() - 24, 24, 24);
                            context.drawImage(new Image("img/Rock1.png"), mLauncher.getLocation().getX(), mLauncher.getLocation().getY() - 24, 24, 24);
                            break;
                        case 1:
                            context.clearRect(mLauncher.getLocation().getX(), mLauncher.getLocation().getY() - 24, 24, 24);
                            break;
                    }
                    AudioClip clip = new AudioClip(getClass().getResource("rockhit.wav").toString());
                    clip.play();
                    mScore.set(mScore.intValue() + 5);
                    mGameCells[(int)((mLauncher.getLocation().getY() - 24) / 24)][(int)(mLauncher.getLocation().getX() / 24)]--;
                }
            }
        }
    }
    
    public void onPauseGo(boolean isRunning) { mIsRunning.set(isRunning); }
    
    public SimpleIntegerProperty getLives() { return mLives; }
    
    public SimpleIntegerProperty getScore() { return mScore; }
    
    public SimpleBooleanProperty isRunning() { return mIsRunning; }
    
    public SimpleBooleanProperty getGameOver() { return mGameOver; }
}
