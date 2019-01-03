package com.hamilton.nathan.nhamiltonfinal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.Serializable;

public class Ghost implements Serializable
{
    private float mX;
    private float mY;
    private int mCurDirection;
    private float mVelocity;
    private boolean mStop;

    public Ghost(int x, int y, int direction)
    {
        mX = x;
        mY = y;
        mCurDirection = direction;
        mStop = false;
    }

    public float getXPosition()
    {
        return mX;
    }

    public float getYPosition()
    {
        return mY;
    }

    public void stop()
    {
        mStop = true;
    }

    public void move(Maze maze, int[][] mazeArrayRep, int level)
    {
        if (!mStop)
        {
            if (mCurDirection == 1)
            {
                if (mX % 1 == 0 && mY > 0)
                {
                    if (mY % 1 == 0)
                    {
                        int cell = mazeArrayRep[(int) mY - 1][(int) mX];
                        if (cell != 0)
                            update(maze, mCurDirection, level);
                        else
                            while (mCurDirection == 1)
                                mCurDirection = ((int) (Math.random() * 4) + 1);
                    }
                    else
                        update(maze, mCurDirection, level);
                }
                else
                    while (mCurDirection == 1)
                        mCurDirection = ((int) (Math.random() * 4) + 1);
            }
            else if (mCurDirection == 2)
            {
                if (mY % 1 == 0 && mX > 0)
                {
                    if (mX % 1 == 0)
                    {
                        int cell = mazeArrayRep[(int) mY][(int) mX - 1];
                        if (cell != 0)
                            update(maze, mCurDirection, level);
                        else
                            while (mCurDirection == 2)
                                mCurDirection = ((int) (Math.random() * 4) + 1);
                    }
                    else
                        update(maze, mCurDirection, level);
                }
                else
                    while (mCurDirection == 2)
                        mCurDirection = ((int) (Math.random() * 4) + 1);
            }
            else if (mCurDirection == 3)
            {
                if (mX % 1 == 0 && mY < 13)
                {
                    if (mY % 1 == 0)
                    {
                        int cell = mazeArrayRep[(int) mY + 1][(int) mX];
                        if (cell != 0)
                            update(maze, mCurDirection, level);
                        else
                            while (mCurDirection == 3)
                                mCurDirection = ((int) (Math.random() * 4) + 1);
                    }
                    else
                        update(maze, mCurDirection, level);
                }
                else
                    while (mCurDirection == 3)
                        mCurDirection = ((int) (Math.random() * 4) + 1);
            }
            else if (mCurDirection == 4)
            {
                if (mY % 1 == 0 && mX < 13)
                {
                    if (mX % 1 == 0)
                    {
                        int cell = mazeArrayRep[(int) mY][(int) mX + 1];
                        if (cell != 0)
                            update(maze, mCurDirection, level);
                        else
                            while (mCurDirection == 4)
                                mCurDirection = ((int) (Math.random() * 4) + 1);
                    }
                    else
                        update(maze, mCurDirection, level);
                }
                else
                    while (mCurDirection == 4)
                        mCurDirection = ((int) (Math.random() * 4) + 1);
            }
        }
    }

    private void update(Maze maze, int direction, int level)
    {
        if (level == 1)
            mVelocity = .125f;
        else
            mVelocity = .25f;

        if (direction == 1)
            mY -= mVelocity;
        else if (direction == 2)
            mX -= mVelocity;
        else if (direction == 3)
            mY += mVelocity;
        else if (direction == 4)
            mX += mVelocity;
    }

    public void onDraw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.save();
        canvas.drawCircle(mX + .5f, mY + .5f, .45f, paint);
    }
}
