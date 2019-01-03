package nhamiltonfinal;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Centipede
{
    public class Segment
    {
        public final Image SEGMENT = new Image("img/segment.png"); 
        private final Point2D TOP_LEFT_CORNER;
        private final Direction DIRECTION;
        
        public Segment(Point2D point, Direction direction)
        {
            TOP_LEFT_CORNER = point;
            DIRECTION = direction;
        }
        
        public Point2D getLocation() { return TOP_LEFT_CORNER; }
        
        public Direction getDirection() { return DIRECTION; }
        
        public Direction switchDirection()
        {
            if (DIRECTION == Direction.RIGHT)
                return Direction.LEFT;
            else
                return Direction.RIGHT;
        }
    }
    
    private ArrayList<Segment> mSegments;
    private int mSize;
    
    public boolean mReachedBottomRow;
    
    public Centipede(int size)
    {
        mSegments = new ArrayList<>();
        mSize = size;
        mReachedBottomRow = false;
        
        for (int i = 0; i < mSize; i++)
            mSegments.add(new Segment(new Point2D(i * 24, 0), Direction.RIGHT));
    }
    
    public ArrayList<Segment> getSegments() { return mSegments; }
    
    public void draw(Canvas canvas)
    {
        if (mSize > 0)
        {
            GraphicsContext context = canvas.getGraphicsContext2D();
            for (Segment seg: mSegments)
                context.drawImage(seg.SEGMENT, seg.getLocation().getX(), seg.getLocation().getY(), 24, 24);
        }
    }
    
    public int size() { return mSize; }
    
    public void update(Canvas canvas, int[][] cells, Segment segment)
    {
        GraphicsContext context = canvas.getGraphicsContext2D();
        ArrayList<Segment> temp = new ArrayList<>();
        if (segment == null)
        {
            for (Segment seg: mSegments)
            {
                context.clearRect(seg.getLocation().getX(), seg.getLocation().getY(), 24, 24);
                if (seg.getDirection() == Direction.RIGHT)
                {
                    if (seg.getLocation().getX() + 24 >= 24 * 24 || cells[(int)(seg.getLocation().getY() / 24)][(int)((seg.getLocation().getX() + 24) / 24)] > 0)
                    {
                        if (seg.getLocation().getY() + 24 >= 24 * 23)
                            mReachedBottomRow = true;
                        else
                            temp.add(new Segment(new Point2D(seg.getLocation().getX(), seg.getLocation().getY() + 24), seg.switchDirection()));
                    }
                    else
                        temp.add(new Segment(new Point2D(seg.getLocation().getX() + 24, seg.getLocation().getY()), seg.getDirection()));
                }
                else
                {
                    if (seg.getLocation().getX() - 24 < 0 || cells[(int)(seg.getLocation().getY() / 24)][(int)((seg.getLocation().getX() - 24) / 24)] > 0)
                    {
                        if (seg.getLocation().getY() + 24 >= 24 * 23)
                            mReachedBottomRow = true;
                        else
                            temp.add(new Segment(new Point2D(seg.getLocation().getX(), seg.getLocation().getY() + 24), seg.switchDirection()));
                    }
                    else
                        temp.add(new Segment(new Point2D(seg.getLocation().getX() - 24, seg.getLocation().getY()), seg.getDirection()));
                }
            }
        }
        else
        {
            for (Segment seg: mSegments)
            {
                if (!seg.equals(segment))
                    temp.add(seg);
            }
            AudioClip clip = new AudioClip(getClass().getResource("seghit.wav").toString());
            clip.play();
            mSize--;
            mSegments = temp;
            context.clearRect(segment.getLocation().getX(), segment.getLocation().getY(), 24, 24);
            context.drawImage(new Image("img/Rock4.png"), segment.getLocation().getX(), segment.getLocation().getY(), 24, 24);
            cells[(int)(segment.getLocation().getY() / 24)][(int)(segment.getLocation().getX() / 24)] = 4;
        }
        mSegments = temp;
        this.draw(canvas);
    }
}
