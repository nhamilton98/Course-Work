package cscd211Lab10;

import java.io.*;
import java.util.*;
import cscd211Utils.*;
import cscd211Methods.*;
import cscd211Comparator.*;
import cscd211Inheritance.Team.Team;


public class CSCD211Lab10
{
	/**
     * This constructor is NOT REQUIRED. It is only here because it appears
     * automatically when the JavaDoc is generated. If it were not here it would
     * still appear in the JavaDoc.
     */
    public CSCD211Lab10()
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
   public static void main(String [] args)throws Exception
   {
      File inf = null;
      int choice;
      ArrayList<Team> myTeams = new ArrayList<Team>();
      Scanner fin = null, kb = new Scanner(System.in);
      
      inf = FileUtils.openFile("input", kb);
      fin = new Scanner(inf);
      Lab10Methods.fill(fin, myTeams);
      fin.close();
      
      do
      {
          choice = Lab10Methods.menu(kb);
          switch(choice)
          {
            case 1:  System.out.println();
            		 System.out.println(myTeams + "\n");
                     break;
                     
            case 2:  for(Team t: myTeams)
                        Collections.sort(t.getPlayers());
                        
            case 3:  Collections.sort(myTeams);
                     break;
                     
            case 4:  Collections.sort(myTeams, new TeamPayrollSort());
                     break;
                     
            default: System.out.println("Exiting");
                     
          }// end switch
          
      }while(choice != 5); 

   }// end main
   
}// end class