package cscd211Lab1;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import cscd211Classes.Sandwich;
import cscd211Comparators.SandwichCaloriesComparator;
import cscd211Methods.Lab1Methods;
import cscd211Utils.ArrayUtils;
import cscd211Utils.FileUtils;

/**
 * This is Lab1 which contains the main method for testing your code. This java
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
 * 3) The fillArray method that resides in the Lab1Methods class is called. This
 * method is passed the Scanner object. The method will return a fully filled
 * array of type Sandwich.<br>
 * 
 * <br>
 * 4) The file for reading is closed and a menu is displayed.<br>
 * 
 * <br>
 * 5) The menu choices are executed until the user chooses to quit. Choices
 * are:<br>
 * <br>
 * &nbsp; &nbsp; 1) Print the array to the screen (System.out) by calling the
 * printArray method in the ArrayUtils class <br>
 * &nbsp; &nbsp; 2) Sort the array based on the natural order by calling the
 * selectionSort method in ArrayUtils class <br>
 * &nbsp; &nbsp; 3) Sort the array by imposing a total order by calling the
 * compare method in the SandwichCaloriesSort class <br>
 * &nbsp; &nbsp; 4) Print the array to a file that is specified by the user
 * after prompting for a filename. This printArray method that resides in the
 * ArrayUtils class uses a PrintStream object to print to the file. The
 * PrintStream object is closed after printing. <br>
 * &nbsp; &nbsp; 5) Quit the program<br>
 * 
 * <br>
 * Use this main to test your solution.
 */
public class CSCD211W17Lab1
{
    /**
     * This constructor is NOT REQUIRED. It is only here because it appears
     * automatically when the JavaDoc is generated. If it were not here it would
     * still appear in the JavaDoc.
     */
    public CSCD211W17Lab1()
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
        int choice;
        File inf = null;
        Scanner fin = null;
        Sandwich[] array = null;
        Scanner kb = new Scanner(System.in);

        inf = FileUtils.openFile("input", kb);
        fin = new Scanner(inf);

        array = Lab1Methods.fillArray(fin);
        fin.close();

        do
        {
            choice = Lab1Methods.menu(kb);

            if (choice == 1)
            {
                System.out.println();
                ArrayUtils.printArray(array);

            } // end if choice == 1

            else if (choice == 2)
                ArrayUtils.selectionSort(array);

            else if (choice == 3)
                Arrays.sort(array, new SandwichCaloriesComparator());

            else if (choice == 4)
            {
                System.out.print("Please enter the name of the output file ");
                String outFile = kb.nextLine();
                PrintStream fout = new PrintStream(outFile);
                ArrayUtils.printArray(array, fout);
            } // end choice == 4

            System.out.println();

        } while (choice != 5);

    }// end main

}// end class
