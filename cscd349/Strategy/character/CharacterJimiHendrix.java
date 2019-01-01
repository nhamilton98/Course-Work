package character;

import guitar.GuitarFlyingV;
import guitar.GuitarSG;
import guitar.GuitarTelecaster;
import solo.SoloFire;
import solo.SoloJump;
import solo.SoloSmash;

public class CharacterJimiHendrix extends Character
{
	private String name;
	
	public CharacterJimiHendrix() { this.name = "Jimi Hendrix"; }
	
	public void play()
	{
		if (this.guitar.getModel().equalsIgnoreCase("Flying V"))
			System.out.println(this.name + " is " + ((GuitarFlyingV) this.guitar).playGuitar());
		else if (this.guitar.getModel().equalsIgnoreCase("SG"))
			System.out.println(this.name + " is " + ((GuitarSG) this.guitar).playGuitar());
		else if (this.guitar.getModel().equalsIgnoreCase("Telecaster"))
			System.out.println(this.name + " is " + ((GuitarTelecaster) this.guitar).playGuitar());
	}
	
	public void perform()
	{
		if (this.solo.getAction().equalsIgnoreCase("fire"))
			System.out.println(this.name + "'s " + ((SoloFire) this.solo).performSolo());
		else if (this.solo.getAction().equalsIgnoreCase("jump"))
			System.out.println(this.name + " " + ((SoloJump) this.solo).performSolo());
		else if(this.solo.getAction().equalsIgnoreCase("smash"))
			System.out.println(this.name + " " + ((SoloSmash) this.solo).performSolo());
	}
}
