package com.hamilton.nathan.nhamiltonlab6;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Nathan on 2/27/2018.
 */

public class NoSwipe extends ViewPager
{
    public NoSwipe(Context context)
    {
        super(context);
    }

    public NoSwipe(Context context, AttributeSet attributes)
    {
        super(context, attributes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return false;
    }
}
