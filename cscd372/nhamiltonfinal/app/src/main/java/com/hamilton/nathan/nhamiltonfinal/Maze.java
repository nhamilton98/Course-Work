package com.hamilton.nathan.nhamiltonfinal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class Maze extends View
{
    private static final float WIDTH = 14f;
    private static final float HEIGHT = 14f;
    private static final float RATIO = WIDTH / HEIGHT;
    private static int TIMER_MSEC = 100;

    private int mX = 14;
    private int mY = 14;

    private Handler mHandler;
    private Runnable mTimer;
    private boolean mIsRunning = false;

    private int mCakeCount;
    private int mLevel = 1;
    private int mNewGhosts = 2;
    private int mGhostCount = mNewGhosts;
    private boolean mGameOver = false;

    private Pacman mPacman;
    private ArrayList<Ghost> mGhosts;
    private int mCurDirection = 0;
    private int mPrevDirection = mCurDirection;

    private GameUpdate mGameCallback;

    private static final int[][] MAZE = {{1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                                         {1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                                         {1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1},
                                         {1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1},
                                         {1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1},
                                         {1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1},
                                         {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                                         {0, 0, 1, 0, 1, 0, 1, 2, 0, 0, 1, 0, 0, 1},
                                         {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1},
                                         {1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1},
                                         {1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0},
                                         {1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1},
                                         {1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
                                         {1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}};
    private int[][] mMaze = MAZE;

    public Maze(Context context)
    {
        super(context);
        initialize();
    }

    public Maze(Context context, AttributeSet attributes)
    {
        super(context, attributes);
        initialize();
    }

    public Maze(Context context, AttributeSet attributes, int style)
    {
        super(context, attributes, style);
        initialize();
    }

    private void initialize()
    {
        if (!mGameOver)
        {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);

            mPacman = new Pacman();

            if (!isInEditMode())
                mGameCallback = (GameUpdate) getContext();

            mCakeCount = 0;

            mGhosts = new ArrayList<>();
            spawnGhosts();

            mHandler = new Handler();
            mTimer = new Runnable()
            {
                @Override
                public void run()
                {
                    if (mIsRunning)
                    {
                        onTimer();
                        invalidate();
                        mHandler.postDelayed(this, TIMER_MSEC);
                    }
                }
            };
        }
    }

    private void onTimer()
    {
        if (!mGameOver)
        {
            gameStatus();
            if (mPacman.move(mMaze, mCurDirection, mPrevDirection))
                //mGameCallback.sound("eatCake");

            gameStatus();
            for (int x = 0; x < mGhosts.size(); x++)
                mGhosts.get(x).move(this, mMaze, mLevel);

            gameStatus();
        }
    }

    @Override
    public Parcelable onSaveInstanceState()
    {
        pause();

        Bundle bundle = new Bundle();
        bundle.putInt("cake_count", mCakeCount);
        bundle.putInt("level", mLevel);
        bundle.putInt("new_ghosts", mNewGhosts);
        bundle.putInt("ghost_count", mGhostCount);
        bundle.putBoolean("game_over", mGameOver);
        bundle.putSerializable("maze", mMaze);
        bundle.putSerializable("pacman", mPacman);
        for (int x = 0; x < mGhostCount; x++)
            bundle.putSerializable(String.valueOf(x), mGhosts.get(x));
        bundle.putInt("current_direction", mCurDirection);
        bundle.putInt("previous_direction", mPrevDirection);

        bundle.putParcelable("instance_state", super.onSaveInstanceState());

        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state)
    {
        pause();

        if (state instanceof Bundle)
        {
            Bundle bundle = (Bundle) state;

            mCakeCount = bundle.getInt("cake_count");
            mLevel = bundle.getInt("level");
            mNewGhosts = bundle.getInt("new_ghosts");
            mGhostCount = bundle.getInt("ghost_count");
            mGameOver = bundle.getBoolean("game_over");
            mMaze = (int[][]) bundle.getSerializable("maze");
            mPacman = (Pacman) bundle.getSerializable("pacman");
            for (int x = 0; x < mGhostCount; x++)
                mGhosts.add((Ghost) bundle.getSerializable(String.valueOf(x)));
            mCurDirection = bundle.getInt("current_direction");
            mPrevDirection = bundle.getInt("prev_direction");

            state = bundle.getParcelable("instance_state");
        }

        super.onRestoreInstanceState(state);
    }

    public void setGhosts(int num)
    {
        if (mLevel == 2)
            mGhostCount = num + mNewGhosts;
        else
            mGhostCount = num;

        spawnGhosts();
    }

    public void setNewGhosts(int num)
    {
        mNewGhosts = num;
    }

    private void spawnGhosts()
    {
        int x = 0;
        int y = 0;
        for (int i = 0; i < mGhostCount; i++) {
            do {
                x = (int) (Math.random() * 14);
                y = (int) (Math.random() * 14);
            } while (mMaze[x][y] == 0);

            int direction = (int) (Math.random() * 4) + 1;

            mGhosts.add(new Ghost(x, y, direction));
        }
    }

    public void go()
    {
        mIsRunning = true;
        mHandler.postDelayed(mTimer, TIMER_MSEC);
    }

    public void pause()
    {
        mIsRunning = false;
        mHandler.removeCallbacks(mTimer);
    }

    public void reset()
    {
        pause();

        if (mLevel == 2)
            mGhostCount -= mNewGhosts;

        mMaze = MAZE;
        mLevel = 1;
        changeDirection(0);
        mGameOver = false;

        initialize();
        pause();
    }

    public void nextLevel()
    {
        pause();

        //mGameCallback.sound("changeLevel");
        mGameCallback.nextLevel(mLevel);

        mLevel += 1;
        mGhostCount += mNewGhosts;
        changeDirection(0);

        mMaze = MAZE;

        initialize();
        pause();
    }

    public void cheat()
    {
        pause();

        mMaze = new int[][] {{2, 2, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
                             {2, 0, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2},
                             {2, 0, 2, 0, 2, 0, 0, 2, 2, 2, 2, 0, 2, 2},
                             {2, 0, 2, 0, 2, 2, 0, 0, 0, 0, 2, 0, 2, 2},
                             {2, 0, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 2, 2},
                             {2, 0, 0, 0, 2, 2, 0, 2, 2, 2, 0, 0, 0, 2},
                             {2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2},
                             {0, 0, 2, 0, 2, 0, 1, 2, 0, 0, 2, 0, 0, 2},
                             {2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2},
                             {2, 0, 2, 0, 2, 0, 0, 2, 0, 2, 2, 2, 2, 2},
                             {2, 0, 2, 0, 2, 0, 0, 2, 2, 2, 0, 2, 0, 0},
                             {2, 0, 2, 2, 2, 2, 0, 2, 0, 0, 0, 2, 0, 2},
                             {2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2},
                             {2, 0, 0, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2}};
        initialize();
    }

    public void win()
    {
        pause();

        mGameCallback.win();
        mGameOver = true;

        //mGameCallback.sound("win");
    }

    public void lose()
    {
        pause();

        mGameCallback.lose();
        mGameOver = true;

        //mGameCallback.sound("lose");
    }

    public void gameStatus()
    {
        float pacX = mPacman.getXPosition();
        float pacY = mPacman.getYPosition();
        float ghostX = 0;
        float ghostY = 0;

        for (int x = 0; x < mGhostCount; x++)
        {
            ghostX = mGhosts.get(x).getXPosition();
            ghostY = mGhosts.get(x).getYPosition();

            if ((pacX == ghostX || pacX + .25f == ghostX || pacX - .25f == ghostX) && (pacY == ghostY || pacY + .25f == ghostY || pacY - .25f == ghostY))
            {
                mPacman.dead();
                lose();
            }
        }

        if (mPacman.isDead())
        {
            if (!mGameOver)
                for (int x = 0; x < mGhosts.size(); x++)
                    mGhosts.get(x).stop();

            mGameOver = true;
        }
    }

    public boolean isGameOver()
    {
        return mGameOver;
    }

    public boolean isRunning()
    {
        return mIsRunning;
    }

    public void changeDirection(int direction)
    {
        if (mCurDirection != direction)
        {
            mPrevDirection = mCurDirection;
            mCurDirection = direction;
        }
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        this.setBackgroundColor(Color.rgb(0, 120, 255));
        Paint paint = new Paint();
        canvas.scale(canvas.getWidth() / WIDTH, canvas.getHeight() / HEIGHT);

        int count = 0;
        for (int x = 0; x < mX; x++)
        {
            for (int y = 0; y < mY; y++)
            {
                int cell = mMaze[y][x];
                if (cell == 0)
                {
                    paint.setColor(Color.BLACK);
                    canvas.drawRect(x, y, x + 1, y + 1, paint);
                }
                else if (cell == 1)
                {
                    paint.setColor(Color.WHITE);
                    canvas.drawCircle(x + .5f, y + .5f, .2f, paint);
                    count++;
                }
            }
        }

        if (count != mCakeCount && !mPacman.isDead())
            mGameCallback.setCakeCount(count);

        mCakeCount = count;

        if (mCakeCount == 0)
            if (mLevel == 1)
            {
                nextLevel();
                mGameCallback.setLevel(mLevel);
            }
            else
                win();

        mPacman.onDraw(canvas);

        for (int x = 0; x < mGhostCount; x++)
            mGhosts.get(x).onDraw(canvas);
    }

    @Override
    protected void onMeasure(int w, int h)
    {
        int width = MeasureSpec.getSize(w);
        int height = MeasureSpec.getSize(h);

        int calculatedWidth = (int) (height * RATIO);
        int calculatedHeight = (int) (width / RATIO);

        int retWidth;
        int retHeight;

        if (calculatedHeight > height)
        {
            retWidth = calculatedWidth;
            retHeight = height;
        }
        else
        {
            retWidth = width;
            retHeight = calculatedHeight;
        }

        setMeasuredDimension(retWidth, retHeight);
    }
}
