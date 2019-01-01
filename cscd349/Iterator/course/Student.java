package course;

public class Student
{
	private String mLastName;
	private String mFirstName;
	private int mID;
	
	public Student(String lastName, String firstName, int ID)
	{
		mLastName = lastName;
		mFirstName = firstName;
		mID = ID;
	}
	
	public String getLastName() { return mLastName; }
	
	public String getFirstName() { return mFirstName; }
	
	public int getID() { return mID; }
	
	public void setLastName(String lastName) { mLastName = lastName; }
	
	public void setFirstName(String firstName) { mFirstName = firstName; }
	
	@Override
	public String toString() { return "Name: " + mLastName + ", " + mFirstName + "     ID: " + mID; }
}
