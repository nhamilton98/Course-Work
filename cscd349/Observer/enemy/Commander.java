package enemy;

import java.util.Observable;
import java.util.Observer;

public class Commander implements Observer
{
	private EyeOfSauron eye;
	private String name;
	
	public Commander(EyeOfSauron eye, String name)
	{
		this.eye = eye;
		this.name = name;
		
		this.eye.addObserver(this);
	}
	
	public String getName() { return this.name; }
	
	public void defeated() { this.eye.deleteObserver(this); }
	
	@Override
	public void update(Observable eye, Object o) { System.out.println(this.name + " has been informed, by the Eye of Sauron, about an army of " + this.eye.toString()); }
}
