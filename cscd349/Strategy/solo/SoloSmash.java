package solo;

public class SoloSmash extends Solo implements SoloBehavior
{
	public SoloSmash() { this.action = "smash"; }
	
	@Override
	public String performSolo() { return "smashes his guitar on the stage!"; }
}
