package com.hamilton.nathan.nhamiltonfinal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Joystick extends View
{
    private static final float WIDTH = 10f;
    private static final float HEIGHT = 10f;
    private static final float RATIO = WIDTH / HEIGHT;

    public Joystick(Context context)
    {
        super(context);
    }

    public Joystick(Context context, AttributeSet attributes)
    {
        super(context, attributes);
    }

    public Joystick(Context context, AttributeSet attributes, int style)
    {
        super(context, attributes, style);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        Paint paint = new Paint();
        paint.setColor(Color.rgb(0, 0, 130));

        canvas.scale(canvas.getWidth() / WIDTH, canvas.getHeight() / HEIGHT);
        canvas.drawCircle(5, 2.6f, 2, paint);
        canvas.drawCircle(2, 5.25f, 2, paint);
        canvas.drawCircle(5, 7.9f, 2, paint);
        canvas.drawCircle(8, 5.25f, 2, paint);
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
