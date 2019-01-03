package nhamiltonlab5;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle implements Serializable
{
    private transient Point2D mStart;
    private transient Point2D mEnd;
    private int mWidth;
    private transient Color mColor;
    
    public Rectangle(Point2D start, Point2D end, int width, Color color)
    {
        mStart = start;
        mEnd = end;
        mWidth = width;
        mColor = color;
    }
    
    public void draw(Canvas canvas)
    {
        GraphicsContext context = canvas.getGraphicsContext2D();
        
        context.setStroke(mColor);
        context.setLineWidth(mWidth);
        
        if (mEnd.getX() > mStart.getX() && mEnd.getY() > mStart.getY())
            context.strokeRect(mStart.getX(), mStart.getY(), mEnd.getX() - mStart.getX(), mEnd.getY() - mStart.getY());
        else if (mEnd.getX() < mStart.getX() && mEnd.getY() > mStart.getY())
            context.strokeRect(mEnd.getX(), mStart.getY(), mStart.getX() - mEnd.getX(), mEnd.getY() - mStart.getY());
        else if (mEnd.getX() < mStart.getX() && mEnd.getY() < mStart.getY())
            context.strokeRect(mEnd.getX(), mEnd.getY(), mStart.getX() - mEnd.getX(), mStart.getY() - mEnd.getY());
        else if (mEnd.getX() > mStart.getX() && mEnd.getY() < mStart.getY())
            context.strokeRect(mStart.getX(), mEnd.getY(), mEnd.getX() - mStart.getX(), mStart.getY() - mEnd.getY());
    }
    
    private void readObject(ObjectInputStream in)
    {
        try
        {
            in.defaultReadObject();
            
            mStart = new Point2D(in.readDouble(), in.readDouble());
            mEnd = new Point2D(in.readDouble(), in.readDouble());
            
            mColor = new Color(in.readDouble(), in.readDouble(), in.readDouble(), 1);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void writeObject(ObjectOutputStream out)
    {
        try
        {
            out.defaultWriteObject();
            
            out.writeDouble(mStart.getX());
            out.writeDouble(mStart.getY());
            out.writeDouble(mEnd.getX());
            out.writeDouble(mEnd.getY());
            
            out.writeDouble(mColor.getRed());
            out.writeDouble(mColor.getGreen());
            out.writeDouble(mColor.getBlue());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
