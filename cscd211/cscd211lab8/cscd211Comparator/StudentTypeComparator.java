package cscd211Comparator;

import java.util.Comparator;

import cscd211Inheritance.Student;

public class StudentTypeComparator implements Comparator<Student>
{
	@Override
	public int compare(Student s1, Student s2)
	{
		if (s1.getType().compareTo(s2.getType()) != 0)
			return s1.getType().compareTo(s2.getType());
		else
		{
			if (Integer.valueOf(s1.getID()).compareTo(Integer.valueOf(s2.getID())) != 0)
				return Integer.valueOf(s1.getID()).compareTo(Integer.valueOf(s2.getID()));
			else
				return Integer.valueOf(s2.getID()).compareTo(Integer.valueOf(s1.getID()));
		}
	}
}
