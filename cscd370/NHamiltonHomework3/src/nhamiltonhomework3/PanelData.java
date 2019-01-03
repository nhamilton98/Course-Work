package nhamiltonhomework3;

import java.io.Serializable;
import javafx.scene.paint.Color;

public class PanelData implements Serializable
{
    private final String TEXT;
    private final double RED;
    private final double GREEN;
    private final double BLUE;
    private final double OPACITY;
    
    PanelData(String text, double red, double green, double blue, double opacity)
    {
        TEXT = text;
        RED = red;
        GREEN = green;
        BLUE = blue;
        OPACITY = opacity;
    }
    
    public String getText() { return TEXT; }
    
    public Color getColor() { return new Color(RED, GREEN, BLUE, OPACITY); }
    
    @Override
    public String toString() { return "Panel Data:\n" + TEXT + "\nRed: " + (int) (RED * 255) + "\nGreen: " + (int) (GREEN * 255) + "\nBlue: " + (int) (BLUE * 255) + "\nOpacity: " + OPACITY; }
}
