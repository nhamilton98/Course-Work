package cscd211Classes;


/**
 * Basic BoxCar class for use in the linked list world<br>
 * You may not modify/change/add/remove anything from this class<br>
 * I could have given it to you as a jar
 */
public class BoxCar implements Comparable<BoxCar>
{
   private String contents;
   
   /**
    * EVC takes a String because a BoxCar only contains a String
    *
    * @param contents The String in the BoxCar
    *
    * @throws IllegalArgumentException if the contents are null or empty
    */
   public BoxCar(final String contents)
   {
	   if(contents == null)
		   throw new IllegalArgumentException("Contents are null");
	   
	   if(contents.isEmpty())
		   throw new IllegalArgumentException("Contents are empty");
	   
      this.contents = contents;
   }
   
   /**
    * toString returns the contents of the BoxCar
    *
    * @return String the BoxCar contents
    */
   @Override
   public String toString()
   {
      return this.contents;
   }
   
   /**
    * compareTo BoxCar contents
    *
    * @param anotherBoxCar The BoxCar passed in
    * @return int Representing order
    * 
    @throws IllegalArgumentException if the passed in object is null
    */
   @Override
   public int compareTo(final BoxCar anotherBoxCar)
   {
	   if(anotherBoxCar == null)
		   throw new IllegalArgumentException("anotherBoxCar is null");
	   
      return this.contents.compareTo(anotherBoxCar.contents);
   }
   
   /**
    * equals checks to see if the BoxCar contents are equal to the
    * BoxCar passed in
    *
    * @param obj The Object being checked 
    * @return boolean true if the contents match false otherwise
    */
   @Override
   public boolean equals(Object obj)
   {
	   if(obj == null) return false;
	   if(this == obj) return true;
	   
	   if( !(obj instanceof BoxCar) ) return false;
	   
      BoxCar pi = (BoxCar)obj;
      return this.contents.equals(pi.contents);
   }
   
   /**
    * hashCode returns the hashCode from String for the contents
    *
    * @return int The hashCode for contents from String
    */
   @Override
   public int hashCode()
   {
      return this.contents.hashCode();
   }
}// end Boxcar