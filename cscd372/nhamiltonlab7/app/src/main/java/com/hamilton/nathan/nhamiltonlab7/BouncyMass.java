package com.hamilton.nathan.nhamiltonlab7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.ToneGenerator;
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
    private static final float springConstant = 1.7f;
    private float bottomPosition = 0.0f;
    private float acceleration = 0.0f;
    private float velocity = 0.0f;

    private static final int TIMER_MSEC = 100;
    private Handler handler;
    private Runnable timer;
    private boolean isRunning = false;

    public float initDisplacement = 0;
    public Float springStiffness = 1.5f;
    public int numCoils = 11;
    public String massType = "Rectangle";

    private int intervalCount = 0;

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
        this.initDisplacement = 0;
        this.velocity = 0;
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
        this.acceleration = (this.GRAVITY - (this.springConstant * this.bottomPosition / this.MASS));
        this.velocity += this.acceleration * interval;
        this.bottomPosition += this.velocity * interval;
        invalidate();

        this.intervalCount++;
        if (this.intervalCount % 70 == 0)
            playTone();
    }

    private void playTone()
    {
        ToneGenerator sound = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
        sound.startTone(ToneGenerator.TONE_PROP_BEEP);
        sound.release();
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        this.setBackgroundColor(Color.WHITE);
        canvas.scale(canvas.getWidth() / this.WIDTH, canvas.getHeight() / this.HEIGHT);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        if (this.massType.equals("Rectangle"))
            canvas.drawRect(WIDTH / 2 - 50, this.y, WIDTH / 2 + 50, this.y + 100, paint);
        else if (this.massType.equals("Rounded Rectangle"))
            canvas.drawRoundRect(new RectF(WIDTH / 2 - 50, this.y, WIDTH / 2 + 50, this.y + 100), 10, 10, paint);
        else if (this.massType.equals("Circle"))
            canvas.drawOval(new RectF(WIDTH / 2 - 50, this.y, WIDTH / 2 + 50, this.y + 100), paint);
        else
        {
            Bitmap picture = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.star);
            canvas.drawBitmap(picture, WIDTH / 2 - 200, this.y, paint);
        }

        float length = this.y / (this.numCoils + 1);
        for (int x = 1; x <= this.numCoils; x++)
        {
            float position = x * (this.y / (this.numCoils + 1));
            canvas.drawOval(new RectF(WIDTH / 2 - 10, position - length, WIDTH / 2 + 10, position + length), paint);
        }
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

    public void setInitialDisplacement(float displacement)
    {
        this.initDisplacement = displacement;
        this.velocity = 0;
    }

    public void setSpringStiffness(Float stiffness)
    {
        this.springStiffness = stiffness;
        this.velocity = 0;
    }

    public void setNumberCoils(int num)
    {
        this.numCoils = num;
        this.velocity = 0;
    }

    public void setMassType(String type)
    {
        this.massType = type;
        this.velocity = 0;
    }
}