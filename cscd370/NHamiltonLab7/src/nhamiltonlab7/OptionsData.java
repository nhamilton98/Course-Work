package nhamiltonlab7;

import java.util.prefs.Preferences;

public class OptionsData
{
    public static boolean showString = true;
    public static boolean dateTime = false;
    public static boolean italics = false;
    public static boolean bold = false;
    public static String userString = "File > Options to change this text.";
    public static int size = 8;

    public static void storePreferences(Class c)
    {
        Preferences prefs = Preferences.userNodeForPackage(c);
        prefs.putBoolean("ShowString", showString);
        prefs.putBoolean("DateTime", dateTime);
        prefs.putBoolean("Italics", italics);
        prefs.putBoolean("Bold", bold);
        prefs.put("UserString", userString);
        prefs.putInt("Size", size);
    }
    
    public static void readPreferences(Class c)
    {
        Preferences prefs = Preferences.userNodeForPackage(c);
        showString = prefs.getBoolean("ShowString", showString);
        dateTime = prefs.getBoolean("DateTime", dateTime);
        italics = prefs.getBoolean("Italics", italics);
        bold = prefs.getBoolean("Bold", bold);
        userString = prefs.get("UserString", userString);
        size = prefs.getInt("Size", size);
    }
}
