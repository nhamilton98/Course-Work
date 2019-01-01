package cscd211Utils;

import java.io.File;
import java.util.Scanner;

public class FileUtils
{
	public static File openFile(final String type, final Scanner kin)
	{
		if (type == null || type.equals(""))
			throw new IllegalArgumentException("Invalid file type.");
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		File file;
		String filename;
		do
		{
			System.out.println("ENTER " + type.toUpperCase() + " FILENAME: ");
			filename = kin.nextLine();
			file = new File(filename);
			
			if (!file.canRead())
				System.out.println("Invalid " + type + " File.");
		} while (!file.canRead());
		
		return file;
	}
}
