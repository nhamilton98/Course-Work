package character;

import guitar.*;
import solo.*;

public class CharacterAngusYoung extends Character
{
	private String name;
	
	public CharacterAngusYoung() { this.name = "Angus Young"; }
	
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
