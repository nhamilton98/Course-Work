package guitar;

public class GuitarFlyingV extends Guitar implements GuitarBehavior
{
	public GuitarFlyingV()
	{
		this.make = "Gibson";
		this.model = "Flying V";
	}
	
	@Override
	public String playGuitar() { return "shredding with a " + this.make + " " + this.model + "."; }
}
