package com.hamilton.nathan.nhamiltonlab5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class BouncyMass extends View {
    private static final float HEIGHT = 800;
    private static final float WIDTH = 600;
    private static final float RATIO = WIDTH / HEIGHT;
    private static final float PIX_PER_METER = 25;
    private float y = HEIGHT / 2;

    private static final float GRAVITY = 9.80665f;
    private static final float MASS = 1.0f;
    private static final float SPRING_CONSTANT = 1.7f;
    private float bottomPosition = 0.0f;
    private float acceleration = 0.0f;
    private float velocity = 0.0f;

    private static final int TIMER_MSEC = 100;
    private Handler handler;
    private Runnable timer;
    private boolean isRunning = false;

    public BouncyMass(Context context) {
        super(context);
        initialize();
    }

    public BouncyMass(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public BouncyMass(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initialize();
    }

    public boolean isRunning()
    {
        return this.isRunning;
    }

    public void initialize()
    {
        this.handler = new Handler();
        this.timer = new Runnable()
        {
            @Override
            public void run()
            {
                interval();
                handler.postDelayed(this, TIMER_MSEC);

                y = (bottomPosition * PIX_PER_METER) + (HEIGHT / 2);
                postInvalidate();
            }
        };
    }

    public void go()
    {
        this.isRunning = true;
        this.handler.postDelayed(this.timer, this.TIMER_MSEC);
    }

    public void pause()
    {
        this.isRunning = false;
        this.handler.removeCallbacks(this.timer);
    }

    protected void interval()
    {
        double interval = (double) this.TIMER_MSEC / 1000;
        this.acceleration = (this.GRAVITY - (this.SPRING_CONSTANT * this.bottomPosition / this.MASS));
        this.velocity += this.acceleration * interval;
        this.bottomPosition += this.velocity * interval;
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        this.setBackgroundColor(Color.WHITE);
        canvas.scale(canvas.getWidth() / this.WIDTH, canvas.getHeight() / this.HEIGHT);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        float position, link = this.y / 11;
        for (int x = 1; x < 11; x++)
        {
            position = x * link;
            canvas.drawOval(new RectF(this.WIDTH / 2 - 10, position - link, this.WIDTH / 2 + 10, position + link), paint);
        }

        canvas.drawRect(this.WIDTH / 2 - 50, this.y, this.WIDTH / 2 + 50, this.y + 100, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int calculatedWidth = (int)(height * this.RATIO);
        int calculatedHeight = (int)(width / this.RATIO);

        int finalWidth, finalHeight;
        if(calculatedHeight > height) {
            finalWidth = calculatedWidth;
            finalHeight = height;
        } else {
            finalWidth = width;
            finalHeight = calculatedHeight;
        }

        setMeasuredDimension(finalWidth, finalHeight);
    }
}
