package com.hamilton.nathan.nhamiltonfinal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.io.Serializable;

public class Pacman implements Serializable
{
    private float mX;
    private float mY;
    private int mCurDirection;
    private boolean mIsDead;

    public Pacman() {
        mX = 7;
        mY = 7;
        mCurDirection = 0;
        mIsDead = false;
    }

    public float getXPosition()
    {
        return mX;
    }

    public float getYPosition()
    {
        return mY;
    }

    public boolean isDead()
    {
        return mIsDead;
    }

    public void dead()
    {
        mIsDead = true;
    }

    public boolean move(int[][] mazeArrayRep, int newDirection, int prevDirection)
    {
        boolean ret = false;

        if (!mIsDead) {
            if (newDirection == 1)
            {
                if (mX % 1 == 0 && mY > 0)
                {
                    if (mY % 1 == 0)
                    {
                        int cell = mazeArrayRep[(int) mY - 1][(int) mX];
                        if (cell != 0) {
                            if (cell == 1)
                                ret = true;

                            mazeArrayRep[(int) mY][(int) mX] = 2;
                            update(newDirection);
                        }
                    }
                    else
                        update(newDirection);
                }
                else if (mX % 1 != 0) {
                    move(mazeArrayRep, prevDirection, newDirection);
                }
            }
            else if (newDirection == 2)
            {
                if (mY % 1 == 0 && mX > 0)
                {
                    if (mX % 1 == 0)
                    {
                        int cell = mazeArrayRep[(int) mY][(int) mX - 1];
                        if (cell != 0) {
                            if (cell == 1)
                                ret = true;

                            mazeArrayRep[(int) mY][(int) mX] = 2;
                            update(newDirection);
                        }
                    }
                    else
                        update(newDirection);
                }
                else if (mY % 1 != 0) {
                    move(mazeArrayRep, prevDirection, newDirection);
                }
            }
            else if (newDirection == 3)
            {
                if (mX % 1 == 0 && mY < 13)
                {
                    if (mY % 1 == 0)
                    {
                        int cell = mazeArrayRep[(int) mY + 1][(int) mX];
                        if (cell != 0) {
                            if (cell == 1)
                                ret = true;

                            mazeArrayRep[(int) mY][(int) mX] = 2;
                            update(newDirection);
                        }
                    }
                    else
                        update(newDirection);
                }
                else if (mX % 1 != 0) {
                    move(mazeArrayRep, prevDirection, newDirection);
                }
            }
            else if (newDirection == 4)
            {
                if (mY % 1 == 0 && mX < 13)
                {
                    if (mX % 1 == 0)
                    {
                        int cell = mazeArrayRep[(int) mY][(int) mX + 1];
                        if (cell != 0) {
                            if (cell == 1)
                                ret = true;

                            mazeArrayRep[(int) mY][(int) mX] = 2;
                            update(newDirection);
                        }
                    }
                    else
                        update(newDirection);
                }
                else if (mY % 1 != 0) {
                    move(mazeArrayRep, prevDirection, newDirection);
                }
            }
        }

        return ret;
    }

    private void update(int newDirection)
    {
        mCurDirection = newDirection;

        if (mCurDirection == 1)
            mY -= .25f;
        else if (mCurDirection == 2)
            mX -= .25f;
        else if (mCurDirection == 3)
            mY += .25f;
        else if (mCurDirection == 4)
            mX += .25f;
    }

    public void onDraw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        canvas.save();
        canvas.drawCircle(mX + .5f, mY + .5f, .45f, paint);

        paint.setColor(Color.rgb(0, 120, 255));
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        if (mCurDirection == 1 && mY % 1 != 0)
        {
            path.lineTo(mX, mY);
            path.lineTo(mX + 1, mY);
            path.lineTo(mX + .5f, mY + .5f);
            path.lineTo(mX, mY);
        }
        else if (mCurDirection == 2 && mX % 1 != 0)
        {
            path.lineTo(mX, mY);
            path.lineTo(mX, mY + 1);
            path.lineTo(mX + .5f, mY + .5f);
            path.lineTo(mX, mY);
        }
        else if (mCurDirection == 3 && mY % 1 != 0)
        {
            path.lineTo(mX, mY + 1);
            path.lineTo(mX + 1, mY + 1);
            path.lineTo(mX + .5f, mY + .5f);
            path.lineTo(mX, mY + 1);
        }
        else if (mCurDirection == 4 && mX % 1 != 0)
        {
            path.lineTo(mX + 1, mY);
            path.lineTo(mX + 1, mY + 1);
            path.lineTo(mX + .5f, mY + .5f);
            path.lineTo(mX + 1, mY);
        }

        canvas.drawPath(path, paint);
    }
}
