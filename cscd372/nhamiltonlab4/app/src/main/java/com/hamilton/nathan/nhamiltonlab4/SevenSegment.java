package com.hamilton.nathan.nhamiltonlab4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

public class SevenSegment extends View
{
    private int value;
    private boolean[] state;

    private static final int on = Color.argb(255, 255, 0, 0);
    private static final int off = Color.argb(50, 255, 0, 0);
    private static final float WIDTH = 20;
    private static final float HEIGHT = 34;
    private static final float RATIO = WIDTH/HEIGHT;
    private static final float[] VERTICES = {3, 3, 4, 2, 16, 2, 17, 3, 16, 4, 4, 4};
    private static final boolean[][] SEGMENTS = {{true, true, false, true, true, true, true}, // #0
                                                {false, false, false, true, false, false, true}, // #1
                                                {true, false, true, true, true, true, false}, // #2
                                                {true, false, true, true, false, true, true}, // #3
                                                {false, true, true, true, false, false, true}, // #4
                                                {true, true, true, false, false, true, true}, // #5
                                                {true, true, true, false, true, true, true}, // #6
                                                {true, false, false, true, false, false, true}, // #7
                                                {true, true, true, true, true, true, true}, // #8
                                                {true, true, true, true, false, true, true}, // #9
                                                {false, false, false, false, false, false, false}}; // Blank

    public SevenSegment(Context context)
    {
        super(context);
        initialization();
    }

    public SevenSegment(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initialization();
    }

    public SevenSegment(Context context, AttributeSet attrs , int defStyle)
    {
        super(context, attrs, defStyle);
        initialization();
    }

    public int getValue()
    {
        return this.value;
    }

    public void setValue(int num)
    {
        if (num >= 0 && num <= 10)
        {
            this.value = num;
            this.state = this.SEGMENTS[num];
            this.invalidate();
        }
    }

    private void initialization()
    {
        this.state = new boolean[7];
        this.setValue(0);
    }

    @Override
    public Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("save", super.onSaveInstanceState());
        bundle.putInt("value", this.value);

        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state)
    {
        if (state instanceof Bundle)
        {
            Bundle bundle = (Bundle) state;
            this.setValue(bundle.getInt("value"));
            state = bundle.getParcelable("save");
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onMeasure(int widthMeasure, int heightMeasure)
    {
        int tempWidth = MeasureSpec.getSize(widthMeasure);
        int tempHeight = MeasureSpec.getSize(heightMeasure);

        int calculatedWidth = (int) (tempHeight * this.RATIO);
        int calculatedHeight = (int) (tempWidth / this.RATIO);

        int width, height;
        if (calculatedHeight > tempHeight)
        {
            width = calculatedWidth;
            height = tempHeight;
        }
        else
        {
            width = tempWidth;
            height = calculatedHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        setLayerType(this.LAYER_TYPE_SOFTWARE, null);
        this.setBackgroundColor(Color.BLACK);
        canvas.scale((float) canvas.getWidth() / this.WIDTH, (float) canvas.getHeight() / this.HEIGHT);
        canvas.drawColor(Color.rgb(0, 0, 0));

        canvas.save();
        this.drawSegment(canvas, 0);
        canvas.restore();

        canvas.save();
        canvas.rotate(90, this.VERTICES[0], this.VERTICES[1]);
        this.drawSegment(canvas, 1);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 14);
        this.drawSegment(canvas, 2);
        canvas.restore();

        canvas.save();
        canvas.translate(14, 0);
        canvas.rotate(90, this.VERTICES[0], this.VERTICES[1]);
        this.drawSegment(canvas, 3);
        canvas.restore();

        canvas.save();
        canvas.rotate(90, this.VERTICES[0], this.VERTICES[1]);
        canvas.translate(14, 0);
        this.drawSegment(canvas, 4);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 28);
        this.drawSegment(canvas, 5);
        canvas.restore();

        canvas.save();
        canvas.translate(14, 14);
        canvas.rotate(90, this.VERTICES[0], this.VERTICES[1]);
        this.drawSegment(canvas, 6);
        canvas.restore();
    }

    private void drawSegment(Canvas canvas, int num)
    {
        Path path = new Path();

        path.moveTo(this.VERTICES[0], this.VERTICES[1]);

        for (int x = 0; x < this.VERTICES.length; x += 2)
            path.lineTo(this.VERTICES[x], this.VERTICES[x + 1]);

        path.close();
        canvas.clipPath(path);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        if (this.state[num])
        {
            paint.setColor(this.on);
            canvas.drawPath(path, paint);
        }
        else
        {
            paint.setColor(this.off);
            canvas.drawPath(path, paint);
        }
    }
}
