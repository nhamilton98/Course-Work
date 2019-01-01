package enemy;

import java.util.Observable;

public class EyeOfSauron extends Observable
{
	private int numHobbits;
	private int numElves;
	private int numDwarves;
	private int numHumans;
	
	public EyeOfSauron()
	{
		this.numHobbits = 0;
		this.numElves = 0;
		this.numDwarves = 0;
		this.numHumans = 0;
	}
	
	public void scout(int hobbits, int elves, int dwarves, int humans)
	{
		this.numHobbits = hobbits;
		this.numElves = elves;
		this.numDwarves = dwarves;
		this.numHumans = humans;
		
		this.setChanged();
		this.notifyObservers();
	}
	
	@Override
	public String toString()
	{
		StringBuilder ret = new StringBuilder();
		
		ret.append(this.numHobbits);
		if (this.numHobbits == 1)
			ret.append(" hobbit, ");
		else
			ret.append(" hobbits, ");
		
		ret.append(this.numElves);
		if (this.numElves == 1)
			ret.append(" elf, ");
		else
			ret.append(" elves, ");
		
		ret.append(this.numDwarves);
		if (this.numDwarves == 1)
			ret.append(" dwarf, and ");
		else
			ret.append(" dwarves, and ");
		
		ret.append(this.numHumans);
		if (this.numHumans == 1)
			ret.append(" human.");
		else
			ret.append(" humans.");
		
		return ret.toString();
	}
}
