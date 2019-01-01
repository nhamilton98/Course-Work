package cscd211Lab6;

import java.util.Scanner;

import cscd211LinkedList.*;
import cscd211Classes.BoxCar;
import cscd211Methods.LinkedListMethods;

/**
 * The class that contains main and is used for testing our LinkedList code
 */
public class CSCD211Lab6
{
	/**
     * This constructor is NOT REQUIRED. It is only here because it appears
     * automatically when the JavaDoc is generated. If it were not here it would
     * still appear in the JavaDoc.
     */
    public CSCD211Lab6()
    {
        /* empty constructor */
    }// end DVC

    /**
     * The main method. The information about this method is describe above.
     * 
     * @param args
     *            Any command line parameters that are passed in. If there are
     *            any they will be ignored by the program
     */
   public static void main(String [] args)
   {
      BoxCar aCar = null;
      boolean res =  false;
      Comparable item = null;
      int choice = 0, result = 0, index = 0;
      Scanner kb = new Scanner(System.in);
      
      LinkedList myList = new LinkedList();
      
      do
      {
         choice = LinkedListMethods.menu(kb);
         
         switch(choice)
         {
            case 1:  System.out.println(myList);
                     break;
                     
            case 2:  aCar = LinkedListMethods.createBoxCar(kb);
                     myList.addFirst(aCar);
                     break;
            
            case 3:  aCar = LinkedListMethods.createBoxCar(kb);
                     myList.addLast(aCar);
                     break;
            
            case 4:  aCar = LinkedListMethods.createBoxCar(kb);
                     index = LinkedListMethods.readIndex(kb);
                     myList.add(index, aCar);
                     break;

            case 5:  aCar = LinkedListMethods.createBoxCar(kb);
                     res = myList.contains(aCar);
                     System.out.println("The results of contains " + res);
                     break;

            case 6:  aCar = LinkedListMethods.createBoxCar(kb);
                     result = myList.indexOf(aCar);
                     System.out.println("The results of indexOf " + result);
                     break;

            case 7:  item = myList.removeFirst();
                     System.out.println("The results of remove " + item);
                     break;

            case 8:  item = myList.removeLast();
                     System.out.println("The results of remove " + item);
                     break;

            case 9:  index = LinkedListMethods.readIndex(kb);
                     item = myList.remove(index);
                     System.out.println("The results of remove " + item);
                     break;

            case 10: aCar = LinkedListMethods.createBoxCar(kb);
                     res = myList.removeFirstOccurrence(aCar);
                     System.out.println("The results of contains " + res);
                     break;
                     
            case 11: aCar = LinkedListMethods.createBoxCar(kb);
                     res = myList.removeLastOccurrence(aCar);
                     System.out.println("The results of contains " + res);
                     break;
                     
            case 12: myList.clear();
                     break;
                     
            case 13: System.out.println("List size: " + myList.size());
                     break;            

            case 14: System.out.println("Program ending");
            		 break;
            		 
            default: throw new IllegalArgumentException("Main Range outside 1 - 14");
         
         }// end switch
      
      }while(choice != 14);
   
   }// end main

}// end class