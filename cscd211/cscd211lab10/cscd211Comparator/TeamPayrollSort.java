package cscd211Comparator;

import java.util.Comparator;

import cscd211Inheritance.Team.Team;

public class TeamPayrollSort implements Comparator<Team>
{
	@Override
	public int compare(Team t1, Team t2)
	{
		if (t1 == null || t2 == null)
			throw new IllegalArgumentException("Cannot compare to null.");
		
		if (Integer.valueOf(t1.getPayroll()).compareTo(Integer.valueOf(t2.getPayroll())) != 0)
			return Integer.valueOf(t1.getPayroll()).compareTo(Integer.valueOf(t2.getPayroll()));
		else
			return Integer.valueOf(t2.getPayroll()).compareTo(Integer.valueOf(t1.getPayroll()));
	}
}
