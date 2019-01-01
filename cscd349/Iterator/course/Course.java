package course;

import java.util.Iterator;

public class Course implements Iterable<Student>
{
	private class CourseIterator implements Iterator<Student>
	{
		private Student[] mList;
		private int mIndex;
		
		public CourseIterator(Student[] list)
		{
			mList = list;
			mIndex = 0;
		}
		
		public boolean hasNext()
		{
			if (mIndex >= mList.length || mList[mIndex] == null)
				return false;
			
			return true;
		}
		
		public Student next()
		{
			Student student = mList[mIndex];
			mIndex++;
			
			return student;
		}
	}
	
	private String mCourseName;
	private String mCourseCode;
	private Student[] mStudentList;
	private int mNextOpenSpot = 0;
	
	private static final int MAX_STUDENTS = 100;
	
	public Course(String courseName, String courseCode)
	{
		initializeCourse(courseName, courseCode);
		mStudentList = new Student[MAX_STUDENTS];
	}
	
	public Course(String courseName, String courseCode, int maxStudents)
	{
		initializeCourse(courseName, courseCode);
		mStudentList = new Student[maxStudents];
	}
	
	private void initializeCourse(String courseName, String courseCode)
	{
		mCourseName = courseName;
		mCourseCode = courseCode;
	}
	
	public String getCourseName() { return mCourseName; }

	public String getCourseCode() { return mCourseCode; }
	
	public Student[] getRoster() { return mStudentList; }
	
	public void addStudent(String lastName, String firstName, int ID)
	{
		if (!(mNextOpenSpot >= mStudentList.length - 1))
		{
			mStudentList[mNextOpenSpot] = new Student(lastName, firstName, ID);
			mNextOpenSpot++;
		}
		else
			System.out.println("The course is full.");
	}

	@Override
	public Iterator<Student> iterator() { return new CourseIterator(mStudentList); }
}
