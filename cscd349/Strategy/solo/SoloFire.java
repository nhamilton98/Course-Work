package solo;

public class SoloFire extends Solo implements SoloBehavior
{
	public SoloFire() { this.action = "fire"; }
	
	@Override
	public String performSolo() { return "guitar bursts into flames!"; }
}
