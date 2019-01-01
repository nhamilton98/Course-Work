import java.util.Iterator;

import course.Course;
import course.Student;

public class CourseIteratorTester
{
	public static void main(String[] args)
	{
		Course course = new Course("Design Patterns", "CSCD 349-01", 60);
		
		course.addStudent("Hamilton", "Nathan", 753941);
		course.addStudent("Cronk", "Kayla", 738874);
		course.addStudent("Hopkins", "Matthew", 764459);
		course.addStudent("Combs", "Andrew", 729180);
		course.addStudent("Dupler", "Matthew", 755616);
		course.addStudent("Wallace", "Forrest", 728915);
		course.addStudent("Burgi", "Kyle", 738812);
		course.addStudent("Zachman", "Steaven", 719563);
		
		System.out.println(course.getCourseCode() + " " + course.getCourseName() + " (For-each):");
		for (Student student: course.getRoster())
		{
			if (student != null)
				System.out.println(student);
		}
		
		Iterator<Student> iterator = course.iterator();
		System.out.println("\n" + course.getCourseCode() + " - " + course.getCourseName() + " (Iterator):");
		while (iterator.hasNext())
			System.out.println(iterator.next());
	}
}