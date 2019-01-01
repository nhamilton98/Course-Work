package character;

import guitar.Guitar;
import solo.Solo;

public class Character 
{
	protected Guitar guitar;
	protected Solo solo;
	
	public Character()
	{
		this.guitar = null;
		this.solo = null;
	}
	
	public void setGuitar(final Guitar newGuitar) { this.guitar = newGuitar; }
	
	public void setSolo(final Solo newSolo) { this.solo = newSolo; }
}
