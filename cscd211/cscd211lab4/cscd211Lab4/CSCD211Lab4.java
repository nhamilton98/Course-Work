package cscd211Lab4;

import java.util.*;
import cscd211Methods.*;

/**
 * The methods class for the CSCD211Lab4 that contains main<br>
 * The purpose of this main is to get practice working with an ArrayList.
 * An ArrayList can hold any kind of Object type (not primitives). In our case
 * the ArrayList will be of type Double.  Notice how the ArrayList type is specified at
 * declaration and in the method headers of Lab4Methods.
 */
public class CSCD211Lab4
{
	/**
	 * The simple main for testing.  NOTE: there is no throws Exception on main because
	 * we don't need it we are not working with any File objects for this lab.
	 * @param args Representing the command line arguments
	 */
   public static void main(String [] args)
   {
      int num, choice;
      Scanner kb = new Scanner(System.in);
      ArrayList<Double> myArrayList = null;
      double mean, median, midpoint, stdDev;
      
      num = Lab4Methods.readNum(kb);
      myArrayList = new ArrayList<Double>(num);
      Lab4Methods.fillArrayList(num, myArrayList);
                  
      do
      {
         choice = Lab4Methods.menu(kb);
         
         switch(choice)
         {
            case 1:  Lab4Methods.addNum(myArrayList, kb);
                     break;
                     
            case 2:  Lab4Methods.deleteValue(myArrayList, kb);
                     break;
                     
            case 3:  Lab4Methods.deleteValueByIndex(myArrayList, kb);
                     break;
                     
            case 4:  Lab4Methods.printArrayList(myArrayList);
                     break;
                     
            case 5:  mean = Lab4Methods.computeMean(myArrayList);
                     Lab4Methods.printResults("Mean", mean);
                     break;
                       
            case 6:  median = Lab4Methods.computeMedian(myArrayList);
                     Lab4Methods.printResults("Median", median);
                     break;

            case 7:  midpoint = Lab4Methods.computeMidpoint(myArrayList);
                     Lab4Methods.printResults("Midpoint", midpoint);
                     break;
                     
            case 8:  stdDev = Lab4Methods.computeStandardDeviation(myArrayList);
            		 Lab4Methods.printResults("Standard Deviation", stdDev);
            break;
                              
         }// end switch
      
      }while(choice != 9);
   
   }// end main   
   
   /**
   * You WILL NOT WRITE this constructor it is provided by Java. <br>
   * DO NOT WRITE!
   */
  public CSCD211Lab4()
  {
      /* empty constructor */
  }
 

}// end class