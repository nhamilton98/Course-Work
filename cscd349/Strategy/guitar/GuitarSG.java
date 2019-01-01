package guitar;

public class GuitarSG extends Guitar implements GuitarBehavior
{
	public GuitarSG()
	{
		this.make = "Gibson";
		this.model = "SG";
	}
	
	@Override
	public String playGuitar() { return "rocking out with a " + this.make + " " + this.model + "."; }
}
