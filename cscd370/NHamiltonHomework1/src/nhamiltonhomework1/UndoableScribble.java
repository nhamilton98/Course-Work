package nhamiltonhomework1;

import javafx.scene.canvas.Canvas;
import javax.swing.undo.AbstractUndoableEdit;

public class UndoableScribble extends AbstractUndoableEdit
{
    private final Canvas CANVAS;
    private Canvas mUndo;
    private Canvas mRedo;
    
    public UndoableScribble(Canvas canvas)
    {
        CANVAS = canvas;
        mUndo = this.copyCanvas(canvas);
    }
    
    @Override
    public void undo()
    {
        mRedo = this.copyCanvas(CANVAS);
        
        Canvas temp = this.copyCanvas(CANVAS);
        this.saveCanvas(mUndo, CANVAS);
        this.saveCanvas(temp, mUndo);
    }
    
    @Override
    public void redo()
    {
        mUndo = this.copyCanvas(CANVAS);
        
        Canvas temp = this.copyCanvas(CANVAS);
        this.saveCanvas(mRedo, CANVAS);
        this.saveCanvas(temp, mRedo);
    }
    
    @Override
    public String getPresentationName() { return "Scribble"; }
    
    private Canvas copyCanvas(Canvas canvas)
    {
        Canvas temp = new Canvas(CANVAS.getWidth(), CANVAS.getHeight());
        temp.getGraphicsContext2D().drawImage(canvas.snapshot(null, null), 0, 0);
        
        return temp;
    }
    
    private void saveCanvas(Canvas canvas, Canvas save) { save.getGraphicsContext2D().drawImage(canvas.snapshot(null, null), 0, 0); }
    
    @Override
    public boolean canRedo() { return true; }
}
