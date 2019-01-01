package guitar;

public class GuitarTelecaster extends Guitar implements GuitarBehavior
{
	public GuitarTelecaster()
	{
		this.make = "Fender";
		this.model = "Telecaster";
	}
	
	@Override
	public String playGuitar() { return "jamming with a " + this.make + " " + this.model + "."; }
}
