package solo;

public class SoloJump extends Solo implements SoloBehavior
{
	public SoloJump() { this.action = "jump"; }
	
	@Override
	public String performSolo() { return "leaps into the crowd!"; }
}
