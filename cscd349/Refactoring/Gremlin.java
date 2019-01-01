

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class Gremlin extends Monster
{
	private static final int HITPOINTS = 70;
	private static final int ATTACK_SPEED = 5;
	private static final double CHANCE_TO_HIT = 0.8;
	private static final double CHANCE_TO_HEAL = 0.4;
	private static final int DAMAGE_MIN = 15;
	private static final int DAMAGE_MAX = 30;
	private static final int HEAL_MIN = 20;
	private static final int HEAL_MAX = 40;

    public Gremlin()
	{
		super("Gnarltooth the Gremlin", HITPOINTS, ATTACK_SPEED, CHANCE_TO_HIT, CHANCE_TO_HEAL,
										DAMAGE_MIN, DAMAGE_MAX, HEAL_MIN, HEAL_MAX);

    }//end constructor

	public void attack(DungeonCharacter opponent)
	{
		System.out.println(name + " jabs his kris at " +
							opponent.getName() + ":");
		super.attack(opponent);

	}//end override of attack


}//end class Gremlin