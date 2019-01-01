package cscd211Lab14;

import java.io.*;
import cscd211Utils.*;
import java.util.Scanner;



public class CSCD211Lab14
{
	/**
     * This constructor is NOT REQUIRED. It is only here because it appears
     * automatically when the JavaDoc is generated. If it were not here it would
     * still appear in the JavaDoc.
     */
    public CSCD211Lab14()
    {
        /* empty constructor */
    }// end DVC

    /**
     * Standard provided main
     * @param args representing the arguments passed in
     * @throws Exception To handle the FileNotFoundException or to shut the compiler up
     */
   public static void main(String [] args)throws Exception
   {
      int choice;
      File inf = null;
      Scanner kb = new Scanner(System.in), fin = null;

      do
      {
         choice = Lab14Methods.menu(kb);

         switch(choice)
         {

            case 1:  String str = Lab14Methods.readString(kb);
                     String newStr = Lab14Methods.reverseString(str);
                     System.out.println("The String:\n" + str + "\n");
                     System.out.println("In reverse is:\n" + newStr + "\n");
                     break;


            case 2:  inf = FileUtil.openInputFile(kb);
                     fin = new Scanner(inf);
                     Lab14Methods.reverseFile(fin);
                     fin.close();
                     System.out.println();
                     break;

            case 3:  inf = FileUtil.openInputFile(kb);
                     fin = new Scanner(inf);
                     Lab14Methods.doBoth(fin);
                     fin.close();
                     System.out.println();
                     break;

            default: System.out.println("EXITING");

         }// end switch

      }while(choice != 4);

   }// end main
}// end class