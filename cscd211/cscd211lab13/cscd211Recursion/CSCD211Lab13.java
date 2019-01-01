package cscd211Recursion;
/** 
 * Write the recursive methods as specified below
 * <br> Submit a zip appropriately named
 */
public class CSCD211Lab13
{
   public static void main(String [] args)
   {
      int [] values = {8, 818, 8818, 8088, 123, 81238, 88788, 8234, 2348, 23884, 0, 1818188, 8818181, 1080, 188, 88888, 9898, 78};
      for(int x = 0; x < values.length ; x++)
         System.out.printf("count8(%d) -> %d\n", values[x], count8(values[x]));      
            
   }// end main
   
   
   /**
    * Given a non-negative int n, compute recursively (no loops) the count of the occurrences of 8 as a digit,
    * Note that mod (%) by 10 yields the rightmost digit (126 % 10 is 6), while divide (/) by 10 removes the rightmost digit (126 / 10 is 12).
    * <br>
    * <br>count8(8) -> 1
    * <br>count8(818) -> 2
    * <br>count8(8818) -> 3
    *
    * @param row The number to be checked for the eights
    * @return int - the number of eights in the number
    */
    public static int count8(int n)
    {
    	if (n < 8)
    		return 0;
    	if (n % 10 == 8)
    		return 1 + count8(n / 10);
    	return count8(n / 10);
    }// end method
}// end class

/*
count8(8) -> 1
count8(818) -> 2
count8(8818) -> 3
count8(8088) -> 3
count8(123) -> 0
count8(81238) -> 2
count8(88788) -> 4
count8(8234) -> 1
count8(2348) -> 1
count8(23884) -> 2
count8(0) -> 0
count8(1818188) -> 4
count8(8818181) -> 4
count8(1080) -> 1
count8(188) -> 2
count8(88888) -> 5
count8(9898) -> 2
count8(78) -> 1
*/
 
