package cscd211Recursion;
/** 
 * Write the recursive methods as specified below
 * <br> Submit a zip appropriately named
 */
public class CSCD211Lab12
{
   public static final int MAX = 10;
   
   public static void main(String [] args)
   {
      for(int x = 0; x <= MAX ; x++)
         System.out.printf("bunnyEars2(%d) -> %d\n", x, bunnyEars2(x));      
            
   }// end main
   
   
   /**
    * We have bunnies standing in a line, numbered 1, 2, ... 
    * The odd bunnies (1, 3, ..) have the normal 2 ears.
    * The even bunnies (2, 4, ..) are mutants and have 3 ears, because they each have a raised foot.
    * <br> Recursively return the number of "ears" in the bunny line 1, 2, ... n (without loops or multiplication).<br>
    *
    * @param row The number of bunnies in line
    * @return int - the number of total bunny ears for the stated problem
    */
    public static int bunnyEars2(int bunnies)
    {
    	if (bunnies == 0)
    		return 0;
    	else if (bunnies % 2 == 1)
    		return 2 + bunnyEars2(bunnies - 1);
    	else
    		return 3 + bunnyEars2(bunnies - 1);
    }// end method      
}// end class

/*
bunnyEars2(0) -> 0
bunnyEars2(1) -> 2
bunnyEars2(2) -> 5
bunnyEars2(3) -> 7
bunnyEars2(4) -> 10
bunnyEars2(5) -> 12
bunnyEars2(6) -> 15
bunnyEars2(7) -> 17
bunnyEars2(8) -> 20
bunnyEars2(9) -> 22
bunnyEars2(10) -> 25
*/
 
