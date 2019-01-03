package nhamiltonfinal;

import java.util.prefs.Preferences;

public class CentipedeSettings
{
    public static int highScore = 0;
    
    public static int numRocks = 15;
    public static int numCentipedeSegs = 10;
    
    public static void storePreferences(Class c)
    {
        Preferences prefs = Preferences.userNodeForPackage(c);
        prefs.putInt("HighScore", highScore);
        prefs.putInt("Rocks", numRocks);
        prefs.putInt("Size", numCentipedeSegs);
    }
    
    public static void readPreferences(Class c)
    {
        Preferences prefs = Preferences.userNodeForPackage(c);
        highScore = prefs.getInt("HighScore", highScore);
        numRocks = prefs.getInt("Rocks", numRocks);
        numCentipedeSegs = prefs.getInt("Size", numCentipedeSegs);
    }
}
