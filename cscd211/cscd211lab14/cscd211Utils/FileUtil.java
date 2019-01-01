package cscd211Utils;

import java.io.File;
import java.util.Scanner;

public class FileUtil
{
	public static int countRecords(final Scanner fin, final int linesPer)
	{
		if (fin == null)
			throw new IllegalArgumentException("Invalid file Scanner.");
		if (linesPer < 1)
			throw new IllegalArgumentException("Invalid record size.");
		
		int count = 0;
		while (fin.hasNext())
		{
			count++;
			fin.nextLine();
		}
		
		if (count == 0)
			throw new IllegalArgumentException("File does not include any full records.");
		if (count % linesPer != 0)
			throw new IllegalArgumentException("Invalid number of lines in file.");
		
		return count / linesPer;
	}
	
	public static File openInputFile(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		File file;
		String filename;
		do
		{
			System.out.println("ENTER INPUT FILENAME: ");
			filename = kin.nextLine();
			file = new File(filename);
			
			if (!file.canRead())
				System.out.println("Invalid input File.");
		} while (!file.canRead());
		
		return file;
	}
}
