package cscd211Lab8;

import java.io.*;
import java.util.*;
import cscd211Comparator.*;
import cscd211Inheritance.*;

public class CSCD211Lab8
{
	/**
     * This constructor is NOT REQUIRED. It is only here because it appears
     * automatically when the JavaDoc is generated. If it were not here it would
     * still appear in the JavaDoc.
     */
    public CSCD211Lab8()
    {
        /* empty constructor */
    }// end DVC 
    
    /**
     * The basic main we have always used.  Notice we have an array list
     * of base class references
     * @param args The array of command line arguments
     * @throws Exception -- To shut the compiler up
     */
   public static void main(String [] args)throws Exception
   {
      ArrayList <Student> myList = new ArrayList<Student>(5);
      myList.add(new Undergrad("Zach Burrow", 887766, 2016));
      myList.add(new Undergrad("Steve Steiner", 777, 2018));
      myList.add(new Grad("Stu Steiner", 666));
      myList.add(new Undergrad("Mehdi Ghazlane", 55556, 2017));
      myList.add(new Grad("Charles Zahara", 665533));
      
      
      for(Student s: myList)
      {
         System.out.println(s);
      }// end for
      System.out.println();
      
      Collections.sort(myList);
      for(Student s: myList)
      {
         System.out.println(s);
      }// end for
      System.out.println();
      
      
      Collections.sort(myList, new StudentTypeComparator());
      for(Student s: myList)
      {
         System.out.println(s);
      }// end for
      System.out.println();
      
   }// end main

}// end class