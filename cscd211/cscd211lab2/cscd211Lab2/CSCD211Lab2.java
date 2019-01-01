package cscd211Lab2;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintStream;
import cscd211Comparators.*;
import cscd211Utils.FileUtils;
import cscd211Classes.Vehicle;
import cscd211Utils.ArrayUtils;
import cscd211Methods.Lab2Methods;

/**
 * This is Lab2 which contains the main method for testing your code. This java
 * file can't be modified in any way. <br>
 * The flow of the program is as follows:<br>
 * 
 * <br>
 * 1) The user is prompted for the name of an input file -- this resides in the
 * FileUtils class -- The method will ensure that a open File object that is
 * readable is returned.<br>
 * 
 * <br>
 * 2) That File object from number 1 is returned and wrapped in a Scanner object
 * so information can be read from the file.<br>
 * 
 * <br>
 * 3) The countRecords method is called on the file and it returns the number of
 * records from the file.
 * 
 * <br>
 * 4) The fillArray method that resides in the Lab2Methods class is called. This
 * method is passed the Scanner object and the total from #3. The method will return a fully filled
 * array of type Vehicle.<br>
 * 
 * <br>
 * 5) The file for reading is closed and a menu is displayed.<br>
 * 
 * <br>
 * 6) The menu choices are executed until the user chooses to quit. 
 * 
 * <br>
 * Use this main to test your solution.
 */
public class CSCD211Lab2
{
	/**
     * This constructor is NOT REQUIRED. It is only here because it appears
     * automatically when the JavaDoc is generated. If it were not here it would
     * still appear in the JavaDoc.
     */
    public CSCD211Lab2()
    {
        /* empty constructor */
    }// end DVC

    /**
     * The main method. The information about this method is describe above.
     * 
     * @param args
     *            Any command line parameters that are passed in. If there are
     *            any they will be ignored by the program
     * @throws Exception
     *             To shut the compiler up for the FileNotFoundException that
     *             Scanner object whines about.
     */
	public static void main(String[] args) throws Exception
	{
		int total;
		int choice;
		File inf = null;
		Scanner fin = null;
		Vehicle [] array = null;
		Scanner kb = new Scanner(System.in);
		
		inf = FileUtils.openFile("input", kb);
        fin = new Scanner(inf);
        total = FileUtils.countRecords(fin, 4);
        fin.close();

        fin = new Scanner(inf);
        array = Lab2Methods.fillArray(fin, total);
        fin.close();
		
		do
        {
            choice = Lab2Methods.menu(kb);

            if (choice == 1)
            {
                System.out.println();
                ArrayUtils.printArray(array);

            } // end if choice == 1

            else if (choice == 2)
                ArrayUtils.selectionSort(array);

            else if (choice == 3)
            	Arrays.sort(array, new ManufacturerComparator());
            
            else if (choice == 4)
            	Arrays.sort(array, new ModelComparator());

            else if (choice == 5)
            {
                System.out.print("Please enter the name of the output file ");
                String outFile = kb.nextLine();
                PrintStream fout = new PrintStream(outFile);
                ArrayUtils.printArray(array, fout);
            } // end choice == 4

            System.out.println();

        } while (choice != 6);

	}// end main

}// end class
