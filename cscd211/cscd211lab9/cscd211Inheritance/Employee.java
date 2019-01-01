package cscd211Inheritance;

public abstract class Employee implements Comparable<Employee>
{
	private final double BASE;
	
	private String name;
	protected double salary;
	
	protected Employee(final String name, final double basePay, final double addPay)
	{
		if (name == null || name.equals(""))
			throw new IllegalArgumentException("Invalid name.");
		if (basePay < 0)
			throw new IllegalArgumentException("Invalid base payrate.");
		if (addPay < 0)
			throw new IllegalArgumentException("Invalid additional payrate.");
		
		this.BASE = basePay;
		
		this.name = name;
		this.salary = this.BASE + addPay;
	}
	
	public double getSalary() { return this.salary; }
	
	public double getBaseSalary() { return this.BASE; }
	
	public String getName() { return this.name; }
	
	public String getType() { return this.getClass().toString(); }
	
	public void setSalary(final double addPay)
	{
		if (addPay < 0)
			throw new IllegalArgumentException("Invalid additional payrate.");
		
		this.salary = this.BASE + addPay;
	}
	
	public void setName(final String name)
	{
		if (name == null || name.equals(""))
			throw new IllegalArgumentException("Invalid name.");
		
		this.name = name;
	}
	
	public abstract void report();
	
	@Override
	public int compareTo(final Employee anotherEmployee)
	{
		if (this.getType().compareTo(anotherEmployee.getType()) > 0)
			return 1;
		else if (this.getType().compareTo(anotherEmployee.getType()) < 0)
			return -1;
		else
		{
			if (Double.valueOf(this.salary).compareTo(Double.valueOf(anotherEmployee.salary)) > 0)
				return 1;
			else if (Double.valueOf(this.salary).compareTo(Double.valueOf(anotherEmployee.salary)) < 0)
				return -1;
			else
				return 0;
		}
	}
	
	@Override
	public String toString() { return this.name; }
}
